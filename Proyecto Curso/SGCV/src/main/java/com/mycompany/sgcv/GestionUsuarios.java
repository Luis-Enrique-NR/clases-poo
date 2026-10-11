package com.mycompany.sgcv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestionUsuarios {

    private static final String RUTA_ARCHIVO = "USUARIOS.txt";

    public String obtenerLineaUsuario(String usr) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {

                if (linea.length() >= 30) {
                    String usuarioEnArchivo = linea.substring(0, 20).trim();
                    if (usuarioEnArchivo.equalsIgnoreCase(usr.trim())) {
                        return linea;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de usuarios.");
        }
        return null;
    }
}