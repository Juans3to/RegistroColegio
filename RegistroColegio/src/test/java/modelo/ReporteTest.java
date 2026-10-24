package modelo;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReporteTest {

    private Profesor crearProfesor(String nombre, String cedula, double salarioHora, int horas) {
        return new Profesor(nombre, "Calle 10 #5-20", "3001234567",
                LocalDate.of(1985, 5, 10), cedula, "Matematicas", salarioHora, horas);
    }

    private Estudiante crearEstudiante(String nombre, String codigo, int edad) {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setDireccion("Carrera 5 #8-12");
        e.setTelefono("3109876543");
        e.setFechaNacimiento(LocalDate.now().minusYears(edad));
        e.setCodigo(codigo);
        e.setGrado("10A");
        return e;
    }

    // ---------- Reporte de estudiantes ----------
    @Test
    public void testReporteEstudiantesVacio_IndicaQueNoHay() {
        Colegio colegio = new Colegio();
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteEstudiantes(colegio);
        assertTrue(resultado.contains("No hay estudiantes registrados."));
        assertFalse(resultado.contains("Total de estudiantes"));
    }

    @Test
    public void testReporteEstudiantes_IncluyeTituloConNombreDelColegio() {
        Colegio colegio = new Colegio();
        colegio.setNombre("Colegio San Jose");
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteEstudiantes(colegio);
        assertTrue(resultado.startsWith("REPORTE DE ESTUDIANTES - Colegio San Jose"));
    }

    @Test
    public void testReporteEstudiantes_IncluyeDatosDelEstudiante() {
        Colegio colegio = new Colegio();
        colegio.agregarEstudiante(crearEstudiante("Ana Torres", "20240001", 15));
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteEstudiantes(colegio);
        assertTrue(resultado.contains("1. Ana Torres"));
        assertTrue(resultado.contains("Código: 20240001"));
        assertTrue(resultado.contains("Grado: 10A"));
        assertTrue(resultado.contains("Edad: 15"));
        assertTrue(resultado.contains("Tel: 3109876543"));
    }

    @Test
    public void testReporteEstudiantes_TotalCorrecto() {
        Colegio colegio = new Colegio();
        colegio.agregarEstudiante(crearEstudiante("Ana Torres", "20240001", 15));
        colegio.agregarEstudiante(crearEstudiante("Luis Mora", "20240002", 16));
        colegio.agregarEstudiante(crearEstudiante("Sara Ruiz", "20240003", 14));
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteEstudiantes(colegio);
        assertTrue(resultado.contains("Total de estudiantes: 3"));
    }

    @Test
    public void testReporteEstudiantes_NumeraEnOrdenDeRegistro() {
        Colegio colegio = new Colegio();
        colegio.agregarEstudiante(crearEstudiante("Ana Torres", "20240001", 15));
        colegio.agregarEstudiante(crearEstudiante("Luis Mora", "20240002", 16));
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteEstudiantes(colegio);
        assertTrue(resultado.contains("1. Ana Torres"));
        assertTrue(resultado.contains("2. Luis Mora"));
        assertTrue(resultado.indexOf("Ana Torres") < resultado.indexOf("Luis Mora"));
    }

    // ---------- Reporte de profesores por salario ----------
    @Test
    public void testReporteProfesoresVacio_IndicaQueNoHay() {
        Colegio colegio = new Colegio();
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);
        assertTrue(resultado.contains("No hay profesores registrados."));
        assertFalse(resultado.contains("TOTAL SALARIOS"));
    }

    @Test
    public void testReporteProfesores_IncluyeTituloConNombreDelColegio() {
        Colegio colegio = new Colegio();
        colegio.setNombre("Colegio San Jose");
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);
        assertTrue(resultado.contains("Colegio San Jose"));
        assertTrue(resultado.startsWith("REPORTE DE PROFESORES POR SALARIO TOTAL"));
    }

    @Test
    public void testReporteProfesores_OrdenaDeMayorAMenorSalario() {
        Colegio colegio = new Colegio();
        // Se registran a proposito desordenados: medio, menor, mayor
        colegio.agregarProfesor(crearProfesor("Medio Perez", "111111", 10000, 100));  // 1.300.000
        colegio.agregarProfesor(crearProfesor("Menor Gomez", "222222", 5000, 50));    //   325.000
        colegio.agregarProfesor(crearProfesor("Mayor Lopez", "333333", 20000, 150));  // 3.900.000
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);

        int posMayor = resultado.indexOf("Mayor Lopez");
        int posMedio = resultado.indexOf("Medio Perez");
        int posMenor = resultado.indexOf("Menor Gomez");
        assertTrue(posMayor < posMedio);
        assertTrue(posMedio < posMenor);
    }

    @Test
    public void testReporteProfesores_NoModificaElOrdenOriginalDelColegio() {
        Colegio colegio = new Colegio();
        colegio.agregarProfesor(crearProfesor("Menor Gomez", "222222", 5000, 50));
        colegio.agregarProfesor(crearProfesor("Mayor Lopez", "333333", 20000, 150));
        Reporte reporte = new Reporte();
        reporte.reporteProfesoresPorSalario(colegio);
        // El reporte ordena una copia: la lista del colegio queda igual
        assertEquals("Menor Gomez", colegio.getProfesores().get(0).getNombre());
        assertEquals("Mayor Lopez", colegio.getProfesores().get(1).getNombre());
    }

    @Test
    public void testReporteProfesores_IncluyeSalarioYPrestacionesDeCadaProfesor() {
        Colegio colegio = new Colegio();
        Profesor p = crearProfesor("Carlos Perez", "123456789", 10000, 100);
        colegio.agregarProfesor(p);
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);

        // Se arma el texto esperado con el mismo formato (1.300.000 y 247.000)
        String esperado = String.format("Salario total: $%,.2f | Prestaciones (19%%): $%,.2f",
                1300000.0, 247000.0);
        assertTrue(resultado.contains("1. Carlos Perez"));
        assertTrue(resultado.contains("Cédula: 123456789"));
        assertTrue(resultado.contains("Horas: 100"));
        assertTrue(resultado.contains(esperado));
    }

    @Test
    public void testReporteProfesores_TotalesSumanTodosLosProfesores() {
        Colegio colegio = new Colegio();
        colegio.agregarProfesor(crearProfesor("Medio Perez", "111111", 10000, 100));  // 1.300.000
        colegio.agregarProfesor(crearProfesor("Mayor Lopez", "333333", 20000, 150));  // 3.900.000
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);

        // Total salarios = 5.200.000 ; prestaciones = 19% = 988.000
        String totalSalarios = String.format("TOTAL SALARIOS: $%,.2f", 5200000.0);
        String totalPrestaciones = String.format("TOTAL PRESTACIONES SOCIALES: $%,.2f", 988000.0);
        assertTrue(resultado.contains(totalSalarios));
        assertTrue(resultado.contains(totalPrestaciones));
    }

    @Test
    public void testReporteProfesores_UnSoloProfesor_TotalIgualAlSalario() {
        Colegio colegio = new Colegio();
        colegio.agregarProfesor(crearProfesor("Carlos Perez", "123456789", 10000, 100));
        Reporte reporte = new Reporte();
        String resultado = reporte.reporteProfesoresPorSalario(colegio);
        assertTrue(resultado.contains(String.format("TOTAL SALARIOS: $%,.2f", 1300000.0)));
    }
}
