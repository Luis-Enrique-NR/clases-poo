package com.swing.todo.project.repository;

import com.swing.todo.project.entity.Tarea;
import java.util.List;

public interface TareaRepository {
    List<Tarea> listarPorPersona(String idPersona);
    boolean agregarTarea(Tarea tarea);
    boolean actualizarEstado(String idTarea, String nuevoEstado);
}