package com.swing.test.login;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;


public class LogService {
    private String nombreLog = "Log.txt";
    
    public void registrarIntento(String usuario, boolean exito) {
                
        try (FileWriter fw = new FileWriter(nombreLog, true)) { // true -> Modo append | false -> Sobreescribe y borra lo anterior

            String estado;
            
            if (exito) {
                estado = "INGRESO EXITOSO";
                
            } else {
                estado = "ERROR DE ACCESO";
            }
            
            String registro_test = LocalDateTime.now() + " | " + usuario + " | " + estado + "\n"; 
            
            //String registro = String.format("%s | Usuario: %s | Estado: %s\n", 
            //                                LocalDateTime.now(), usuario, estado);
            
            // REGISTRO -->   17/08/2026 | RIVALDO | ERROR DE ACCESO

            fw.write(registro_test);
            
        } catch (IOException e) {
            System.err.println("Error al escribir LOG.TXT: " + e.getMessage());
        }
    }
}
