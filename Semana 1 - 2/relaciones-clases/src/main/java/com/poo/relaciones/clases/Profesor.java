package com.poo.relaciones.clases;

public class Profesor extends Persona{
    private boolean es_nombrado;

    public Profesor(String dni, String nombre, String apellido, boolean es_nombrado) {
        super(dni, nombre, apellido);
        this.es_nombrado = es_nombrado;
    }
    
    public void enseñar() {
        System.out.println("Dictando curso...");
    }
} 
