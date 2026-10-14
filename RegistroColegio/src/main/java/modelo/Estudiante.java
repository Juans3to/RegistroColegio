/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */

public class Estudiante extends Persona {

    private String codigo;
    private String grado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || !codigo.trim().matches("\\d{4,12}")) {
            throw new IllegalArgumentException("El código debe tener solo números (4 a 12 dígitos).");
        }
        this.codigo = codigo.trim();
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        if (grado == null || grado.trim().isEmpty()) {
            throw new IllegalArgumentException("El grado no puede estar vacío.");
        }
        this.grado = grado.trim();
    }

    // Sobreescritura
    @Override
    public String getDescripcion() {
        return "Estudiante: " + getNombre() + " | Código: " + codigo + " | Grado: " + grado;
    }
}

