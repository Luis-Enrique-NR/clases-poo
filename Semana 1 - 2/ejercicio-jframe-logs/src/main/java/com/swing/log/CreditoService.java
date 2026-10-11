/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swing.log;

public class CreditoService {
    
    private CreditoRepositoryDat repository;

    public CreditoService(CreditoRepositoryDat repository) {
        this.repository = repository;
    }
    
    private static Double tasaInteres = 0.15;
    
    public void registrarCredito(SolicitudCredito solicitudCredito, Vehiculo vehiculo) {
        Double montoFinanciar = vehiculo.getPrecio() - solicitudCredito.getCuotaInicial();
       
        int plazo = Integer.parseInt(solicitudCredito.getPlazoMeses().substring(0, 2));
        
        Double cuota = (montoFinanciar/plazo) + (montoFinanciar*tasaInteres);
        
        solicitudCredito.setPagoCuota(cuota);
        
        repository.registrarSolicitudCredito(solicitudCredito);
    }
    
    public boolean sePuedeEndeudar(Double precioVehiculo, Double ingresoTotal) {
        return (precioVehiculo <= ingresoTotal * 12);
    }
    
    public boolean ingresoApto(Double ingreso) {
        return ingreso >= 2000;
    }
    
    public boolean minimoCuotaInicial(Double cuotaInicial, Double ingreso) {
        return cuotaInicial <= 0.3 * ingreso;
    }
}