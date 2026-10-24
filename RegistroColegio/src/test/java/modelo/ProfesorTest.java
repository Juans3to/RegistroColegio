package modelo;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfesorTest {

    // Crea un profesor valido: 10.000 por hora x 100 horas
    private Profesor crearProfesor() {
        return new Profesor("Carlos Perez", "Calle 10 #5-20", "3001234567",
                LocalDate.of(1985, 5, 10), "123456789", "Matematicas", 10000, 100);
    }

    // ---------- Constructor ----------
    @Test
    public void testConstructorValido_AsignaTodosLosCampos() {
        Profesor p = crearProfesor();
        assertEquals("Carlos Perez", p.getNombre());
        assertEquals("Calle 10 #5-20", p.getDireccion());
        assertEquals("3001234567", p.getTelefono());
        assertEquals(LocalDate.of(1985, 5, 10), p.getFechaNacimiento());
        assertEquals("123456789", p.getCedula());
        assertEquals("Matematicas", p.getArea());
        assertEquals(10000, p.getSalarioHora(), 0.01);
        assertEquals(100, p.getHorasDictadas());
    }

    @Test
    public void testConstructorMenorDeEdad_LanzaExcepcion() {
        LocalDate hace17Anios = LocalDate.now().minusYears(17);
        assertThrows(IllegalArgumentException.class, () -> new Profesor("Carlos Perez",
                "Calle 10 #5-20", "3001234567", hace17Anios, "123456789", "Matematicas", 10000, 100));
    }

    @Test
    public void testConstructorExactamente18Anios_Valido() {
        LocalDate hace18Anios = LocalDate.now().minusYears(18);
        Profesor p = new Profesor("Carlos Perez", "Calle 10 #5-20", "3001234567",
                hace18Anios, "123456789", "Matematicas", 10000, 100);
        assertEquals(18, p.calcularEdad());
    }

    @Test
    public void testConstructorNombreInvalido_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Profesor("Carlos123",
                "Calle 10 #5-20", "3001234567", LocalDate.of(1985, 5, 10),
                "123456789", "Matematicas", 10000, 100));
    }

    @Test
    public void testConstructorFechaFutura_LanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Profesor("Carlos Perez",
                "Calle 10 #5-20", "3001234567", LocalDate.now().plusYears(1),
                "123456789", "Matematicas", 10000, 100));
    }

    // ---------- Cedula ----------
    @Test
    public void testSetCedulaValida_Base() {
        Profesor p = crearProfesor();
        p.setCedula("987654321");
        assertEquals("987654321", p.getCedula());
    }

    @Test
    public void testSetCedulaLimiteInferior6Digitos_Valida() {
        Profesor p = crearProfesor();
        p.setCedula("123456");
        assertEquals("123456", p.getCedula());
    }

    @Test
    public void testSetCedulaLimiteSuperior10Digitos_Valida() {
        Profesor p = crearProfesor();
        p.setCedula("1234567890");
        assertEquals("1234567890", p.getCedula());
    }

    @Test
    public void testSetCedulaMuyCorta5Digitos_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setCedula("12345"));
    }

    @Test
    public void testSetCedulaMuyLarga11Digitos_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setCedula("12345678901"));
    }

    @Test
    public void testSetCedulaConLetras_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setCedula("12345abc"));
    }

    @Test
    public void testSetCedulaNull_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setCedula(null));
    }

    // ---------- Area ----------
    @Test
    public void testSetAreaValida_Base() {
        Profesor p = crearProfesor();
        p.setArea("  Ciencias  ");
        assertEquals("Ciencias", p.getArea());
    }

    @Test
    public void testSetAreaVacia_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setArea(""));
    }

    @Test
    public void testSetAreaNull_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setArea(null));
    }

    // ---------- Salario por hora ----------
    @Test
    public void testSetSalarioHoraValido_Base() {
        Profesor p = crearProfesor();
        p.setSalarioHora(25000.50);
        assertEquals(25000.50, p.getSalarioHora(), 0.01);
    }

    @Test
    public void testSetSalarioHoraCero_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setSalarioHora(0));
    }

    @Test
    public void testSetSalarioHoraNegativo_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setSalarioHora(-5000));
    }

    // ---------- Horas dictadas ----------
    @Test
    public void testSetHorasDictadasValidas_Base() {
        Profesor p = crearProfesor();
        p.setHorasDictadas(120);
        assertEquals(120, p.getHorasDictadas());
    }

    @Test
    public void testSetHorasDictadasLimiteInferior0_Valido() {
        Profesor p = crearProfesor();
        p.setHorasDictadas(0);
        assertEquals(0, p.getHorasDictadas());
    }

    @Test
    public void testSetHorasDictadasLimiteSuperior250_Valido() {
        Profesor p = crearProfesor();
        p.setHorasDictadas(250);
        assertEquals(250, p.getHorasDictadas());
    }

    @Test
    public void testSetHorasDictadasNegativas_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setHorasDictadas(-1));
    }

    @Test
    public void testSetHorasDictadasMasDe250_LanzaExcepcion() {
        Profesor p = crearProfesor();
        assertThrows(IllegalArgumentException.class, () -> p.setHorasDictadas(251));
    }

    // ---------- Pago ----------
    @Test
    public void testCalcularPago_Base() {
        Profesor p = crearProfesor();
        // 10.000 * 100 = 1.000.000 + 30% de preparacion -> 1.300.000
        assertEquals(1300000, p.calcularPago(), 0.01);
    }

    @Test
    public void testCalcularPagoConCeroHoras_EsCero() {
        Profesor p = crearProfesor();
        p.setHorasDictadas(0);
        assertEquals(0, p.calcularPago(), 0.01);
    }

    @Test
    public void testCalcularPagoConDecimales() {
        Profesor p = crearProfesor();
        p.setSalarioHora(15500.75);
        p.setHorasDictadas(80);
        // 15500.75 * 80 * 1.30 = 1.612.078
        assertEquals(1612078, p.calcularPago(), 0.01);
    }

    @Test
    public void testCalcularPagoConBonificacion_SumaAlPagoBase() {
        Profesor p = crearProfesor();
        // 1.300.000 + 200.000
        assertEquals(1500000, p.calcularPago(200000), 0.01);
    }

    @Test
    public void testCalcularPagoConBonificacionCero_IgualAlPagoBase() {
        Profesor p = crearProfesor();
        assertEquals(p.calcularPago(), p.calcularPago(0), 0.01);
    }

    // ---------- Prestaciones ----------
    @Test
    public void testCalcularPrestaciones_Base() {
        Profesor p = crearProfesor();
        // 19% de 1.300.000 = 247.000
        assertEquals(247000, p.calcularPrestaciones(), 0.01);
    }

    @Test
    public void testCalcularPrestacionesConCeroHoras_EsCero() {
        Profesor p = crearProfesor();
        p.setHorasDictadas(0);
        assertEquals(0, p.calcularPrestaciones(), 0.01);
    }

    // ---------- Constantes ----------
    @Test
    public void testConstantesDePorcentaje() {
        assertEquals(0.30, Profesor.PORC_PREPARACION, 0.0001);
        assertEquals(0.19, Profesor.PORC_PRESTACIONES, 0.0001);
    }

    // ---------- Descripcion (sobreescritura) ----------
    @Test
    public void testGetDescripcion_ContieneDatosPrincipales() {
        Profesor p = crearProfesor();
        String d = p.getDescripcion();
        assertTrue(d.startsWith("Profesor: Carlos Perez"));
        assertTrue(d.contains("Cédula: 123456789"));
        assertTrue(d.contains("Área: Matematicas"));
        assertTrue(d.contains("Pago: $"));
    }

    @Test
    public void testGetDescripcionPolimorfica_UsaLaVersionDeProfesor() {
        Persona p = crearProfesor(); // referencia de tipo Persona
        assertTrue(p.getDescripcion().startsWith("Profesor:"));
    }
}
