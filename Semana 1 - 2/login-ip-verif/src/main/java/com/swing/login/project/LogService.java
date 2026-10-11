package com.swing.login.project;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class LogService {
    private String rutaLog = "LOG.TXT";

    public void registrarIntento(String usuario, boolean exito) {
        try (FileWriter fw = new FileWriter(rutaLog, true)) { // true -> trueModo append | false -> Sobreescribe y borra lo anterior
            
            
            String estado = exito ? "INGRESO EXITOSO" : "ERROR DE ACCESO";
            String registro = String.format("%s | Usuario: %s | Estado: %s | IP: %s%n",
                                            LocalDateTime.now(), limpiarParaLog(usuario), estado, obtenerIpLocal());
                        
            fw.write(registro);
        } catch (IOException e) {
            System.err.println("Error al escribir LOG.TXT: " + e.getMessage());
        }
    }

    // Obtiene la IP local de la maquina donde corre el programa.
    // Si la red falla, nunca rompe el log: devuelve una marca.
    private String obtenerIpLocal() {
        try {
            InetAddress equipo = InetAddress.getLocalHost();
            String ip = equipo.getHostAddress();
            if (ip == null) {
                return "IP_DESCONOCIDA";
            } else {
                return ip;
            }
        } catch (UnknownHostException e) {
            return "IP_DESCONOCIDA";
        }
    }

    // Limpia saltos de linea y pipes para que nadie forje lineas en el cuaderno.
    // Recorre char por char con for + charAt + if/else, sin regex.
    private String limpiarParaLog(String texto) {
        if (texto == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i = i + 1) {
            char c = texto.charAt(i);
            if (c == '\r') {
                sb.append('_');
            } else {
                if (c == '\n') {
                    sb.append('_');
                } else {
                    if (c == '|') {
                        sb.append('_');
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}