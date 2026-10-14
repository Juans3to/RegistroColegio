/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.registrocolegio;

import controlador.ColegioControlador;
import modelo.Colegio;
import vista.VentanaPrincipal;

/**
 *
 * @author juans
 */

public class RegistroColegio {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // 1. Crear colegio con constructor vacío
            Colegio colegio = new Colegio();
            colegio.setNombre("Colegio Alemán Cali");

            // 2. Crear controlador pasándole el colegio
            ColegioControlador controlador = new ColegioControlador(colegio);

            // 3. Crear vista pasándole el controlador
            VentanaPrincipal vista = new VentanaPrincipal(controlador);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}
