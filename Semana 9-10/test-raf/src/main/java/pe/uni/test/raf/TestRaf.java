/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.uni.test.raf;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static pe.uni.test.raf.ServicioCreditos.aprobarCredito;
import static pe.uni.test.raf.ServicioCreditos.consultarEstado;
import static pe.uni.test.raf.ServicioCreditos.crearEstructuraInicial;
import static pe.uni.test.raf.ServicioCreditos.historialGeneral;
import static pe.uni.test.raf.ServicioCreditos.listarSolicitudesCliente;
import static pe.uni.test.raf.ServicioCreditos.rechazarCredito;
import static pe.uni.test.raf.ServicioCreditos.solicitarCredito;

/**
 *
 * @author LUIS
 */
public class TestRaf {

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        
        
        System.out.println("=== SERVICIO CRÉDITOS VEHICULARES ===");
        System.out.println("Tamaño registro: " + ServicioCreditos.TAMANO_REGISTRO + " bytes\n");

        
        /*
        // T1
        System.out.println("--- T1: Estructura Inicial ---");
        System.out.println("Resultado: " + crearEstructuraInicial() + "\n");
        */
        
        
        // T2
        System.out.println("--- T2: Solicitar Crédito ---");
        try {
            String codigo = solicitarCredito("12345678", "ABC123", 5000.00);
            System.out.println("Código generado: " + codigo + "\n");
        } catch (IllegalArgumentException e) {
            System.err.println("Validación: " + e.getMessage() + "\n");
        }

        
        // T3
        System.out.println("--- T3: Consultar Estado ---");
        String consulta = consultarEstado("CR-001");
        System.out.println(consulta + "\n");

        
        /*
        // T4
        System.out.println("--- T4: Listar Cliente ---");
        String listado = listarSolicitudesCliente("12345678");
        System.out.println(listado + "\n");

        // T5
        System.out.println("--- T5: Aprobar ---");
        boolean aprobado = aprobarCredito("CR-001");
        System.out.println("Aprobado: " + aprobado + "\n");

        // Ver después de aprobar
        System.out.println("--- Consultar CR-001 después de aprobar ---");
        String consulta2 = consultarEstado("CR-001");
        System.out.println(consulta2 + "\n");

        // T6 (necesita otro PENDIENTE)
        System.out.println("--- T6: Rechazar (otro crédito PENDIENTE) ---");
        try {
            String cod2 = solicitarCredito("87654321", "XYZ123", 3000.00);
            System.out.println("Crédito extra: " + cod2 + "\n");
            boolean rechazado = rechazarCredito(cod2);
            System.out.println("Rechazado: " + rechazado + "\n");
        } catch (IllegalArgumentException e) {
            System.err.println("Validación: " + e.getMessage() + "\n");
        }

        // T7
        System.out.println("--- T7: Historial General ---");
        String historial = historialGeneral();
        System.out.println(historial + "\n");


        */ 
        
        
    }
    
    
    /*
    public static void methodTest(String S, int size) throws UnsupportedEncodingException {
        byte[] bytes = S.getBytes("UTF-8");
        System.out.println("Cadena de bytes original:");
        System.out.println(Arrays.toString(bytes));
        
        String cadenaOriginal = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("Impreso como String:");
        System.out.println(cadenaOriginal);
        
       
        if (bytes.length >= size) {
            System.out.println("Escribir en el raf");
        } else {
            byte[] completo = new byte[size];
            System.arraycopy(bytes, 0, completo, 0, bytes.length);
            
            System.out.println("Cadena de bytes copiados:");
            System.out.println(Arrays.toString(completo));
            
            String cadenaCopiada01 = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("Impreso como String:");
            System.out.println("[" + cadenaCopiada01 + "]");
        
            
            for (int i = bytes.length; i < size; i++) {
                
                completo[i] = (byte) ' ';
                System.out.println("Byte de Posicion (" + i + "): " + completo[i]);
                
                
            }
            
            System.out.println("Nueva cadena de bytes total:");
            System.out.println(Arrays.toString(completo));
            
            String cadenaCopiada02 = new String(completo, StandardCharsets.UTF_8);
            System.out.println("Impreso como String:");
            System.out.println("[" + cadenaCopiada02 + "]");
        
            
            
        }
    }

    */
}
