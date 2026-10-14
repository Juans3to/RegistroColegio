/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author juans
 */

import java.time.LocalDate;
import modelo.Colegio;
import modelo.Estudiante;
import modelo.Profesor;
import modelo.Reporte;

/**
 * Controlador de la aplicación siguiendo la arquitectura MVC.
 * Se encarga de intermediar entre la Vista (VentanaPrincipal) y el Modelo (Colegio, Reporte, etc.).
 */
public class ColegioControlador {

    private final Colegio colegio;
    private final Reporte reporte;

    public ColegioControlador(Colegio colegio) {
        this.colegio = colegio;
        this.reporte = new Reporte();
    }

    /**
     * Registra un profesor delegando la creación y almacenamiento al modelo.
     */
    public void agregarProfesor(String nombre, String direccion, String telefono,
                                LocalDate fechaNacimiento, String cedula,
                                String area, double salarioHora, int horasDictadas) {
        Profesor p = new Profesor(nombre, direccion, telefono, fechaNacimiento, cedula, area, salarioHora, horasDictadas);
        colegio.agregarProfesor(p);
    }

    /**
     * Registra un estudiante delegando la configuración y almacenamiento al modelo.
     */
    public void agregarEstudiante(String nombre, String direccion, String telefono,
                                  LocalDate fechaNacimiento, String codigo, String grado) {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setDireccion(direccion);
        e.setTelefono(telefono);
        e.setFechaNacimiento(fechaNacimiento);
        e.setCodigo(codigo);
        e.setGrado(grado);
        colegio.agregarEstudiante(e);
    }

    /**
     * Obtiene el reporte de estudiantes en formato texto.
     */
    public String obtenerReporteEstudiantes() {
        return reporte.reporteEstudiantes(colegio);
    }

    /**
     * Obtiene el reporte de profesores ordenados por salario.
     */
    public String obtenerReporteProfesores() {
        return reporte.reporteProfesoresPorSalario(colegio);
    }

    public Colegio getColegio() {
        return colegio;
    }
}