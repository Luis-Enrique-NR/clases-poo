package com.poo.relaciones.clases;

public abstract class Persona {
    private String dni;
    private String nombre;
    private String apellido;

    public Persona(String dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public void getFullName(){
        System.out.println(this.nombre + " " + this.apellido);
    }
    
    public String getDNI() {
        return this.dni;
    }
}
