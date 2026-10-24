package modelo;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonaTest {

    // ---------- Nombre ----------
    @Test
    public void testSetNombreValido_Base() {
        Persona p = new Persona();
        p.setNombre("Maria Lopez");
        assertEquals("Maria Lopez", p.getNombre());
    }

    @Test
    public void testSetNombreConEspaciosExtremos_HaceTrim() {
        Persona p = new Persona();
        p.setNombre("   Maria Lopez   ");
        assertEquals("Maria Lopez", p.getNombre());
    }

    @Test
    public void testSetNombreConTildesYEnie_Valido() {
        Persona p = new Persona();
        p.setNombre("José Ñandú");
        assertEquals("José Ñandú", p.getNombre());
    }

    @Test
    public void testSetNombreConNumeros_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setNombre("Maria123"));
    }

    @Test
    public void testSetNombreVacio_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setNombre("   "));
    }

    @Test
    public void testSetNombreNull_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setNombre(null));
    }

    // ---------- Direccion ----------
    @Test
    public void testSetDireccionValida_Base() {
        Persona p = new Persona();
        p.setDireccion("  Calle 10 #5-20  ");
        assertEquals("Calle 10 #5-20", p.getDireccion());
    }

    @Test
    public void testSetDireccionVacia_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setDireccion(""));
    }

    @Test
    public void testSetDireccionNull_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setDireccion(null));
    }

    // ---------- Telefono ----------
    @Test
    public void testSetTelefonoValido10Digitos_Base() {
        Persona p = new Persona();
        p.setTelefono("3001234567");
        assertEquals("3001234567", p.getTelefono());
    }

    @Test
    public void testSetTelefonoLimiteInferior7Digitos_Valido() {
        Persona p = new Persona();
        p.setTelefono("1234567");
        assertEquals("1234567", p.getTelefono());
    }

    @Test
    public void testSetTelefonoMuyCorto6Digitos_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setTelefono("123456"));
    }

    @Test
    public void testSetTelefonoMuyLargo11Digitos_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setTelefono("12345678901"));
    }

    @Test
    public void testSetTelefonoConLetras_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setTelefono("300abc4567"));
    }

    @Test
    public void testSetTelefonoNull_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setTelefono(null));
    }

    // ---------- Fecha de nacimiento ----------
    @Test
    public void testSetFechaNacimientoPasada_Base() {
        Persona p = new Persona();
        LocalDate fecha = LocalDate.of(2000, 1, 15);
        p.setFechaNacimiento(fecha);
        assertEquals(fecha, p.getFechaNacimiento());
    }

    @Test
    public void testSetFechaNacimientoHoy_Valida() {
        Persona p = new Persona();
        LocalDate hoy = LocalDate.now();
        p.setFechaNacimiento(hoy);
        assertEquals(hoy, p.getFechaNacimiento());
    }

    @Test
    public void testSetFechaNacimientoFutura_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class,
                () -> p.setFechaNacimiento(LocalDate.now().plusDays(1)));
    }

    @Test
    public void testSetFechaNacimientoNull_LanzaExcepcion() {
        Persona p = new Persona();
        assertThrows(IllegalArgumentException.class, () -> p.setFechaNacimiento(null));
    }

    // ---------- Edad (sobrecarga) ----------
    @Test
    public void testCalcularEdadConFechaReferencia_Base() {
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.of(2000, 6, 15));
        // Cumple 25 el 15/06/2025
        assertEquals(25, p.calcularEdad(LocalDate.of(2025, 6, 15)));
    }

    @Test
    public void testCalcularEdadUnDiaAntesDelCumple() {
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.of(2000, 6, 15));
        assertEquals(24, p.calcularEdad(LocalDate.of(2025, 6, 14)));
    }

    @Test
    public void testCalcularEdadMismoDiaDeNacimiento_EsCero() {
        Persona p = new Persona();
        LocalDate fecha = LocalDate.of(2020, 3, 10);
        p.setFechaNacimiento(fecha);
        assertEquals(0, p.calcularEdad(fecha));
    }

    @Test
    public void testCalcularEdadSinParametros_UsaFechaDeHoy() {
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.now().minusYears(20));
        assertEquals(20, p.calcularEdad());
    }

    @Test
    public void testCalcularEdadSinParametros_IgualQueConHoy() {
        Persona p = new Persona();
        p.setFechaNacimiento(LocalDate.of(1990, 12, 25));
        assertEquals(p.calcularEdad(LocalDate.now()), p.calcularEdad());
    }

    // ---------- Descripcion ----------
    @Test
    public void testGetDescripcion_Base() {
        Persona p = new Persona();
        p.setNombre("Maria Lopez");
        p.setTelefono("3001234567");
        assertEquals("Persona: Maria Lopez | Tel: 3001234567", p.getDescripcion());
    }
}
