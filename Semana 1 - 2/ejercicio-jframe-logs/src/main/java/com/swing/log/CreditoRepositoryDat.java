package com.swing.log;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;

public class CreditoRepositoryDat {
    
    private static String SolicitudCreditosBD = "Solicitud.dat";
    private static int LONGITUD_REGISTRO = 36;
    
    public void registrarSolicitudCredito(SolicitudCredito solicitudCredito) {
        
        try (RandomAccessFile raf = new RandomAccessFile(SolicitudCreditosBD, "rw")) {
            
            raf.seek(raf.length());
            
            byte[] registroBytes = convertirStringABytes(solicitudCredito);
            
            raf.write(registroBytes);
            
            raf.close();
        } catch (IOException e) {
            System.out.println("ERROR DURANTE EL REGISTRO DE UN CREDITO");
        }
    }
    
    public byte[] convertirStringABytes(SolicitudCredito solicitudCredito) {
        String dni = solicitudCredito.getCliente().getDni();
            int numeroCuotas = Integer.parseInt(solicitudCredito.getPlazoMeses().substring(0, 2));
            Double pagoCuota = solicitudCredito.getPagoCuota();
            
            LocalDateTime marcaTiempo = LocalDateTime.now();
            
            String registro = String.format("%s | DNI: %s | NUMERO CUOTAS: %d | PAGO POR CUOTA: %.2f%n", 
                    marcaTiempo, dni, numeroCuotas, pagoCuota);
            
        return registro.getBytes();
    }
}

