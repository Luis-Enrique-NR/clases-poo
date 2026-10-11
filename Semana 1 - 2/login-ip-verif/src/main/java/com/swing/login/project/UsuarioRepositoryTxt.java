package com.swing.login.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsuarioRepositoryTxt implements UsuarioRepository {
    private String rutaArchivo = "Usuarios.txt";

    @Override
    public Usuario buscarPorUsername(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 5) {
                    String userFile = datos[2].trim();
                    if (userFile.equalsIgnoreCase(username)) {
                        String passFile = datos[3].trim();
                        boolean activo = datos[4].trim().equals("1");
                        return new Usuario(userFile, passFile, activo);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer TXT: " + e.getMessage());
        }
        return null; // Retorna null si no lo encuentra
    }
}
