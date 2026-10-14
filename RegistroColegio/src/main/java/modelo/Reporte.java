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

public class Reporte {

    // Reporte 1: lista de estudiantes
    public String reporteEstudiantes(Colegio colegio) {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE DE ESTUDIANTES - ").append(colegio.getNombre()).append("\n");
        sb.append("==================================================\n");
        ArrayList<Estudiante> lista = colegio.getEstudiantes();
        if (lista.isEmpty()) {
            sb.append("No hay estudiantes registrados.\n");
            return sb.toString();
        }
        int n = 1;
        for (Estudiante e : lista) {
            sb.append(String.format("%d. %s | Código: %s | Grado: %s | Edad: %d | Tel: %s\n",
                    n++, e.getNombre(), e.getCodigo(), e.getGrado(), e.calcularEdad(), e.getTelefono()));
        }
        sb.append("--------------------------------------------------\n");
        sb.append("Total de estudiantes: ").append(lista.size()).append("\n");
        return sb.toString();
    }

    // Reporte 2: profesores ordenados por salario total (mayor a menor) con prestaciones
    public String reporteProfesoresPorSalario(Colegio colegio) {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE DE PROFESORES POR SALARIO TOTAL (MAYOR A MENOR) - ")
                .append(colegio.getNombre()).append("\n");
        sb.append("==================================================\n");
        if (colegio.getProfesores().isEmpty()) {
            sb.append("No hay profesores registrados.\n");
            return sb.toString();
        }
        ArrayList<Profesor> ordenados = new ArrayList<>(colegio.getProfesores());
        // Ordenamiento por selección: de mayor a menor salario total
        for (int i = 0; i < ordenados.size() - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < ordenados.size(); j++) {
                if (ordenados.get(j).calcularPago() > ordenados.get(mayor).calcularPago()) {
                    mayor = j;
                }
            }
            Profesor temporal = ordenados.get(i);
            ordenados.set(i, ordenados.get(mayor));
            ordenados.set(mayor, temporal);
        }

        double totalPagos = 0;
        double totalPrestaciones = 0;
        int n = 1;
        for (Profesor p : ordenados) {
            sb.append(String.format("%d. %s | Cédula: %s | Área: %s | Horas: %d\n",
                    n++, p.getNombre(), p.getCedula(), p.getArea(), p.getHorasDictadas()));
            sb.append(String.format("   Salario total: $%,.2f | Prestaciones (19%%): $%,.2f\n",
                    p.calcularPago(), p.calcularPrestaciones()));
            totalPagos += p.calcularPago();
            totalPrestaciones += p.calcularPrestaciones();
        }
        sb.append("--------------------------------------------------\n");
        sb.append(String.format("TOTAL SALARIOS: $%,.2f\n", totalPagos));
        sb.append(String.format("TOTAL PRESTACIONES SOCIALES: $%,.2f\n", totalPrestaciones));
        return sb.toString();
    }
}
