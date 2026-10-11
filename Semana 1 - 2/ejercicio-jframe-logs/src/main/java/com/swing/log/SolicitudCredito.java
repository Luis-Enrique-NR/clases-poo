/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.log;

/**
 *
 * @author LUIS
 */
public class SolicitudCredito {
    
    private Cliente cliente; // ---> SOLO DNI: 8 DIGITOS
    
    private Double cuotaInicial; 
    private String plazoMeses; // 8 CARACTERES
    
    private Double pagoCuota;

    public SolicitudCredito(Cliente cliente, Double cuotaInicial, String plazoMeses) {
        this.cliente = cliente;
        this.cuotaInicial = cuotaInicial;
        this.plazoMeses = plazoMeses;
    }
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(Double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public String getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoInicial(String plazoInicial) {
        this.plazoMeses = plazoInicial;
    }


    public Double getPagoCuota() {
        return pagoCuota;
    }

    public void setPagoCuota(Double pagoCuota) {
        this.pagoCuota = pagoCuota;
    }
    
    
}
