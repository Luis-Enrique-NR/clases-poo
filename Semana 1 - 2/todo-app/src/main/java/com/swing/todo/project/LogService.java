package com.swing.todo.project;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogService {
    private String rutaLog = "LOG.TXT";

    public void registrarIntento(String usuario, boolean exito) {
        try (FileWriter fw = new FileWriter(rutaLog, true)) { // true -> Modo append | false -> Sobreescribe y borra lo anterior
            String estado = exito ? "INGRESO EXITOSO" : "ERROR DE ACCESO";
            String registro = String.format("%s | Usuario: %s | Estado: %s\n", 
                                            LocalDateTime.now(), usuario, estado);
            fw.write(registro);
        } catch (IOException e) {
            System.err.println("Error al escribir LOG.TXT: " + e.getMessage());
        }
    }
}