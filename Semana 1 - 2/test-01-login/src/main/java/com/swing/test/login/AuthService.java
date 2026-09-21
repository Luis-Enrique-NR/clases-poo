package com.swing.test.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuthService {

    private String atributo;
    
    private String archivo = "Usuarios.txt";
    
    public boolean validarCredenciales(String username, String password) {  // INPUTS
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|"); // Regex
                // split("|") significa OR, por lo que separaria caracter por caracter
                
                if (datos.length >= 5) {
                    String userFile = datos[2].trim();
                    String passFile = datos[3].trim();
                    String estado = datos[4].trim();

                    // Datos coinciden y estado activo
                    if (userFile.equalsIgnoreCase(username) && passFile.equals(password)) {
                        return estado.equals("1");
                    }
                }
            }  
            
        } catch (IOException e) {
            System.err.println("Error al leer Usuarios.txt: " + e.getMessage());
        }
       
        return false;
    }
}
