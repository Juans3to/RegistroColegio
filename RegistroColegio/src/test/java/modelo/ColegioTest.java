package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColegioTest {

    private Profesor crearProfesor(String nombre, String cedula) {
        return new Profesor(nombre, "Calle 10 #5-20", "3001234567",
                LocalDate.of(1985, 5, 10), cedula, "Matematicas", 10000, 100);
    }

    private Estudiante crearEstudiante(String nombre, String codigo) {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setDireccion("Carrera 5 #8-12");
        e.setTelefono("3109876543");
        e.setFechaNacimiento(LocalDate.of(2010, 3, 15));
        e.setCodigo(codigo);
        e.setGrado("10A");
        return e;
    }

    // ---------- Estado inicial ----------
    @Test
    public void testColegioNuevo_NombrePorDefecto() {
        Colegio c = new Colegio();
        assertEquals("Colegio", c.getNombre());
    }

    @Test
    public void testColegioNuevo_ListasVacias() {
        Colegio c = new Colegio();
        assertTrue(c.getProfesores().isEmpty());
        assertTrue(c.getEstudiantes().isEmpty());
        assertTrue(c.getPersonas().isEmpty());
    }

    @Test
    public void testSetNombre_Base() {
        Colegio c = new Colegio();
        c.setNombre("Colegio San Jose");
        assertEquals("Colegio San Jose", c.getNombre());
    }

    // ---------- Agregar profesor ----------
    @Test
    public void testAgregarProfesor_Base() {
        Colegio c = new Colegio();
        Profesor p = crearProfesor("Carlos Perez", "123456789");
        c.agregarProfesor(p);
        assertEquals(1, c.getProfesores().size());
        assertSame(p, c.getProfesores().get(0));
    }

    @Test
    public void testAgregarVariosProfesoresConCedulasDistintas() {
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        c.agregarProfesor(crearProfesor("Laura Gomez", "987654321"));
        assertEquals(2, c.getProfesores().size());
    }

    @Test
    public void testAgregarProfesorCedulaRepetida_LanzaExcepcion() {
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        Profesor repetido = crearProfesor("Laura Gomez", "123456789");
        assertThrows(IllegalArgumentException.class, () -> c.agregarProfesor(repetido));
    }

    @Test
    public void testAgregarProfesorCedulaRepetida_NoSeAgrega() {
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        try {
            c.agregarProfesor(crearProfesor("Laura Gomez", "123456789"));
        } catch (IllegalArgumentException ex) {
            // se espera la excepcion
        }
        assertEquals(1, c.getProfesores().size());
    }

    // ---------- Agregar estudiante ----------
    @Test
    public void testAgregarEstudiante_Base() {
        Colegio c = new Colegio();
        Estudiante e = crearEstudiante("Ana Torres", "20240001");
        c.agregarEstudiante(e);
        assertEquals(1, c.getEstudiantes().size());
        assertSame(e, c.getEstudiantes().get(0));
    }

    @Test
    public void testAgregarVariosEstudiantesConCodigosDistintos() {
        Colegio c = new Colegio();
        c.agregarEstudiante(crearEstudiante("Ana Torres", "20240001"));
        c.agregarEstudiante(crearEstudiante("Luis Mora", "20240002"));
        assertEquals(2, c.getEstudiantes().size());
    }

    @Test
    public void testAgregarEstudianteCodigoRepetido_LanzaExcepcion() {
        Colegio c = new Colegio();
        c.agregarEstudiante(crearEstudiante("Ana Torres", "20240001"));
        Estudiante repetido = crearEstudiante("Luis Mora", "20240001");
        assertThrows(IllegalArgumentException.class, () -> c.agregarEstudiante(repetido));
    }

    @Test
    public void testAgregarEstudianteCodigoRepetido_NoSeAgrega() {
        Colegio c = new Colegio();
        c.agregarEstudiante(crearEstudiante("Ana Torres", "20240001"));
        try {
            c.agregarEstudiante(crearEstudiante("Luis Mora", "20240001"));
        } catch (IllegalArgumentException ex) {
            // se espera la excepcion
        }
        assertEquals(1, c.getEstudiantes().size());
    }

    @Test
    public void testProfesorYEstudianteConMismoNumero_NoSeConsideranRepetidos() {
        // La cedula solo se compara con profesores y el codigo solo con estudiantes
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456"));
        c.agregarEstudiante(crearEstudiante("Ana Torres", "123456"));
        assertEquals(1, c.getProfesores().size());
        assertEquals(1, c.getEstudiantes().size());
    }

    // ---------- Polimorfismo: getPersonas ----------
    @Test
    public void testGetPersonas_JuntaProfesoresYEstudiantes() {
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        c.agregarEstudiante(crearEstudiante("Ana Torres", "20240001"));
        c.agregarEstudiante(crearEstudiante("Luis Mora", "20240002"));
        ArrayList<Persona> personas = c.getPersonas();
        assertEquals(3, personas.size());
    }

    @Test
    public void testGetPersonas_ProfesoresPrimeroLuegoEstudiantes() {
        Colegio c = new Colegio();
        c.agregarEstudiante(crearEstudiante("Ana Torres", "20240001"));
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        ArrayList<Persona> personas = c.getPersonas();
        assertTrue(personas.get(0) instanceof Profesor);
        assertTrue(personas.get(1) instanceof Estudiante);
    }

    @Test
    public void testGetPersonas_DevuelveCopia_NoAfectaLasListasOriginales() {
        Colegio c = new Colegio();
        c.agregarProfesor(crearProfesor("Carlos Perez", "123456789"));
        c.getPersonas().clear();
        assertEquals(1, c.getProfesores().size());
    }

    // ---------- Setters de listas ----------
    @Test
    public void testSetProfesores_ReemplazaLaLista() {
        Colegio c = new Colegio();
        ArrayList<Profesor> nueva = new ArrayList<>();
        nueva.add(crearProfesor("Carlos Perez", "123456789"));
        c.setProfesores(nueva);
        assertEquals(1, c.getProfesores().size());
    }

    @Test
    public void testSetEstudiantes_ReemplazaLaLista() {
        Colegio c = new Colegio();
        ArrayList<Estudiante> nueva = new ArrayList<>();
        nueva.add(crearEstudiante("Ana Torres", "20240001"));
        c.setEstudiantes(nueva);
        assertEquals(1, c.getEstudiantes().size());
    }
}
