/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */

import java.time.LocalDate;

public class Profesor extends Persona {

    public static final double PORC_PREPARACION = 0.30;
    public static final double PORC_PRESTACIONES = 0.19;

    private String cedula;
    private String area;
    private double salarioHora;
    private int horasDictadas;

    public Profesor(String nombre, String direccion, String telefono, LocalDate fechaNacimiento,
            String cedula, String area, double salarioHora, int horasDictadas) {
        setNombre(nombre);
        setDireccion(direccion);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        if (calcularEdad() < 18) {
            throw new IllegalArgumentException("El profesor debe ser mayor de edad.");
        }
        setCedula(cedula);
        setArea(area);
        setSalarioHora(salarioHora);
        setHorasDictadas(horasDictadas);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula == null || !cedula.trim().matches("\\d{6,10}")) {
            throw new IllegalArgumentException("La cédula debe tener solo números (6 a 10 dígitos).");
        }
        this.cedula = cedula.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("El área no puede estar vacía.");
        }
        this.area = area.trim();
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        if (salarioHora <= 0) {
            throw new IllegalArgumentException("El salario por hora debe ser mayor que cero.");
        }
        this.salarioHora = salarioHora;
    }

    public int getHorasDictadas() {
        return horasDictadas;
    }

    public void setHorasDictadas(int horasDictadas) {
        if (horasDictadas < 0 || horasDictadas > 250) {
            throw new IllegalArgumentException("Las horas dictadas deben estar entre 0 y 250 al mes.");
        }
        this.horasDictadas = horasDictadas;
    }

    // Pago mensual = salario por hora * horas dictadas + 30% por preparación de clase
    public double calcularPago() {
        return salarioHora * horasDictadas * (1 + PORC_PREPARACION);
    }

    // Sobrecarga: pago mensual más una bonificación
    public double calcularPago(double bonificacion) {
        return calcularPago() + bonificacion;
    }

    // Prestaciones sociales = 19% del pago total
    public double calcularPrestaciones() {
        return calcularPago() * PORC_PRESTACIONES;
    }

    // Sobreescritura
    @Override
    public String getDescripcion() {
        return "Profesor: " + getNombre() + " | Cédula: " + cedula + " | Área: " + area
                + String.format(" | Pago: $%,.2f", calcularPago());
    }
}

