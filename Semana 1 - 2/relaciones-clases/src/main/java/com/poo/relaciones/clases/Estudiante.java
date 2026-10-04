package com.poo.relaciones.clases;

public class Estudiante extends Persona{
    private String codigo;
    private String promocion;

    public Estudiante(String dni, String nombre, String apellido, String cod, String promocion) {
        super(dni, nombre, apellido);
        this.codigo = cod;
        this.promocion = promocion;
    }
    
    public void matricularse() {
        System.out.println("Procede la matricula");
    }
}
