package com.swing.todo.project.repository;

import com.swing.todo.project.entity.Tarea;
import com.swing.todo.project.repository.TareaRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TareaRepositoryTxt implements TareaRepository {
    private String rutaArchivo = "Tareas.txt";

    @Override
    public List<Tarea> listarPorPersona(String idPersona) {
        List<Tarea> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                
                String[] datos = linea.split("\\|");
                if (datos.length >= 5) {
                    String idTarea = datos[0].trim();
                    String idPer = datos[1].trim();
                    String textoTarea = datos[2].trim();
                    String prioridad = datos[3].trim();
                    String estado = datos[4].trim();

                    // Filtra únicamente las tareas del usuario activo
                    if (idPer.equals(idPersona)) {
                        lista.add(new Tarea(idTarea, idPer, textoTarea, prioridad, estado));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer Tareas.txt: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean agregarTarea(Tarea tarea) {
        try (FileWriter fw = new FileWriter(rutaArchivo, true)) {
            String linea = String.format("%s|%s|%s|%s|%s\n",
                    tarea.getIdTarea(),
                    tarea.getIdPersona(),
                    tarea.getTarea(),
                    tarea.getPrioridad(),
                    tarea.getEstado());
            fw.write(linea);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en Tareas.txt: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean actualizarEstado(String idTarea, String nuevoEstado) {
        java.io.File archivoOriginal = new java.io.File("Tareas.txt");
        java.io.File archivoTemporal = new java.io.File("Tareas_temp.txt");

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(archivoOriginal));
             java.io.FileWriter fw = new java.io.FileWriter(archivoTemporal)) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split("\\|");
                if (datos.length >= 5 && datos[0].trim().equals(idTarea)) {
                    // Reemplaza el estado (posición 4) por el nuevo valor
                    linea = String.format("%s|%s|%s|%s|%s", datos[0], datos[1], datos[2], datos[3], nuevoEstado);
                }
                fw.write(linea + "\n");
            }
        } catch (java.io.IOException e) {
            System.err.println("Error al actualizar Tareas.txt: " + e.getMessage());
            return false;
        }

        // Reemplaza el archivo original por el modificado
        if (archivoOriginal.delete()) {
            return archivoTemporal.renameTo(archivoOriginal);
        }
        return false;
    }
}