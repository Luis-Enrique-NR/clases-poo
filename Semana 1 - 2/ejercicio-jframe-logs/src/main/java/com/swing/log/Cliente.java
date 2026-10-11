/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.log;

/**
 *
 * @author LUIS
 */
public class Cliente {
    private String dni;
    private Double ingreso;
    private String nombreCompleto;

    public Cliente(String dni, Double ingreso, String nombreCompleto) {
        this.dni = dni;
        this.ingreso = ingreso;
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
}
