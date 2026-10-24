package modelo;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {

    // ---------- Codigo ----------
    @Test
    public void testSetCodigoValido_Base() {
        Estudiante e = new Estudiante();
        e.setCodigo("20240001");
        assertEquals("20240001", e.getCodigo());
    }

    @Test
    public void testSetCodigoLimiteInferior4Digitos_Valido() {
        Estudiante e = new Estudiante();
        e.setCodigo("1234");
        assertEquals("1234", e.getCodigo());
    }

    @Test
    public void testSetCodigoLimiteSuperior12Digitos_Valido() {
        Estudiante e = new Estudiante();
        e.setCodigo("123456789012");
        assertEquals("123456789012", e.getCodigo());
    }

    @Test
    public void testSetCodigoConEspacios_HaceTrim() {
        Estudiante e = new Estudiante();
        e.setCodigo("  1234  ");
        assertEquals("1234", e.getCodigo());
    }

    @Test
    public void testSetCodigoMuyCorto3Digitos_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setCodigo("123"));
    }

    @Test
    public void testSetCodigoMuyLargo13Digitos_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setCodigo("1234567890123"));
    }

    @Test
    public void testSetCodigoConLetras_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setCodigo("12ab34"));
    }

    @Test
    public void testSetCodigoNull_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setCodigo(null));
    }

    // ---------- Grado ----------
    @Test
    public void testSetGradoValido_Base() {
        Estudiante e = new Estudiante();
        e.setGrado("10A");
        assertEquals("10A", e.getGrado());
    }

    @Test
    public void testSetGradoConEspacios_HaceTrim() {
        Estudiante e = new Estudiante();
        e.setGrado("  Quinto  ");
        assertEquals("Quinto", e.getGrado());
    }

    @Test
    public void testSetGradoVacio_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setGrado("   "));
    }

    @Test
    public void testSetGradoNull_LanzaExcepcion() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setGrado(null));
    }

    // ---------- Herencia de Persona ----------
    @Test
    public void testEstudianteHeredaValidacionDeNombre() {
        Estudiante e = new Estudiante();
        assertThrows(IllegalArgumentException.class, () -> e.setNombre("Juan99"));
    }

    @Test
    public void testEstudianteHeredaCalculoDeEdad() {
        Estudiante e = new Estudiante();
        e.setFechaNacimiento(LocalDate.of(2010, 8, 20));
        assertEquals(15, e.calcularEdad(LocalDate.of(2025, 8, 20)));
    }

    // ---------- Descripcion (sobreescritura) ----------
    @Test
    public void testGetDescripcion_Base() {
        Estudiante e = new Estudiante();
        e.setNombre("Ana Torres");
        e.setCodigo("20240001");
        e.setGrado("10A");
        assertEquals("Estudiante: Ana Torres | Código: 20240001 | Grado: 10A", e.getDescripcion());
    }

    @Test
    public void testGetDescripcionPolimorfica_UsaLaVersionDeEstudiante() {
        Estudiante e = new Estudiante();
        e.setNombre("Ana Torres");
        e.setCodigo("20240001");
        e.setGrado("10A");
        Persona p = e; // referencia de tipo Persona
        assertTrue(p.getDescripcion().startsWith("Estudiante:"));
    }
}
