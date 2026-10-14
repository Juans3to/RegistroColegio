/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juans
 */

import java.util.ArrayList;

public class Colegio {

    private String nombre = "Colegio";
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void agregarProfesor(Profesor p) {
        for (Profesor existente : profesores) {
            if (existente.getCedula().equals(p.getCedula())) {
                throw new IllegalArgumentException("Ya existe un profesor con esa cédula.");
            }
        }
        profesores.add(p);
    }

    public void agregarEstudiante(Estudiante e) {
        for (Estudiante existente : estudiantes) {
            if (existente.getCodigo().equals(e.getCodigo())) {
                throw new IllegalArgumentException("Ya existe un estudiante con ese código.");
            }
        }
        estudiantes.add(e);
    }

    // Polimorfismo: una sola lista de Persona con profesores y estudiantes
    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.addAll(profesores);
        personas.addAll(estudiantes);
        return personas;
    }
}
