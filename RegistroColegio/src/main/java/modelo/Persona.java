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
import java.time.Period;

public class Persona {

    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || !nombre.trim().matches("[\\p{L} ]+")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
        this.nombre = nombre.trim();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.trim().matches("\\d{7,10}")) {
            throw new IllegalArgumentException("El teléfono debe tener solo números (7 a 10 dígitos).");
        }
        this.telefono = telefono.trim();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    // Sobrecarga: edad a la fecha de hoy
    public int calcularEdad() {
        return calcularEdad(LocalDate.now());
    }

    // Sobrecarga: edad a una fecha de referencia
    public int calcularEdad(LocalDate fechaReferencia) {
        return Period.between(fechaNacimiento, fechaReferencia).getYears();
    }

    public String getDescripcion() {
        return "Persona: " + nombre + " | Tel: " + telefono;
    }
}
