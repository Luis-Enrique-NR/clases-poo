package com.swing.todo.project.entity;

public class Tarea {
    private String idTarea;
    private String idPersona;
    private String tarea;
    private String prioridad; // "Rutinario" | "Urgente"
    private String estado;    // "Pendiente" | "Completado"

    public Tarea(String idTarea, String idPersona, String tarea, String prioridad, String estado) {
        this.idTarea = idTarea;
        this.idPersona = idPersona;
        this.tarea = tarea;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdTarea() { return idTarea; }
    public String getIdPersona() { return idPersona; }
    public String getTarea() { return tarea; }
    public String getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
    
    // Método utilitario para saber si está completada de forma rápida
    public boolean isCompletada() {
        return "Completado".equalsIgnoreCase(this.estado);
    }
}