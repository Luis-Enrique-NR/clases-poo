package com.mycompany.sgcv;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

public class SGCV {

    // Puerto arbitrario para controlar la instancia única
    private static final int PUERTO_CONTROL = 9999;
    private static ServerSocket socketControl;

    public static void main(String[] args) {
        try {
            // Intentar abrir el puerto local
            socketControl = new ServerSocket(PUERTO_CONTROL);
            
            // Si el puerto está libre, se inicia la aplicación (pantalla de inicio de sesión)
            java.awt.EventQueue.invokeLater(() -> {
                InicioSesion login = new InicioSesion();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            });

        } catch (IOException e) {
            // Si el puerto está ocupado, significa que la aplicación ya está abierta
            JOptionPane.showMessageDialog(
                null, 
                "El programa ya se encuentra en ejecución.", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE
            );
            System.exit(0); // Cerrar la segunda instancia inmediatamente
        }
    }
}