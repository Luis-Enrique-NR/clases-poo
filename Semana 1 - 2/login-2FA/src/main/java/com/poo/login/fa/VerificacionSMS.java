package com.poo.login.fa;

import java.util.Random;
import java.util.Scanner;

public class VerificacionSMS implements FactorAutenticacion{
    
    @Override
    public boolean autenticar(Usuario usuario) {
        if (usuario.getPhone() == null || usuario.getPhone().trim().isEmpty()) {
            System.out.println("[ERROR 2FA] El usuario no tiene un número telefónico asociado.");
            return false;
        }

        // 1. Generar código numérico de 6 dígitos
        String codigoGenerado = generarCodigoSMS();
        
        // 2. Simular envío por SMS
        System.out.println("\n=====================================================================");
        System.out.println("\n--- SIMULANDO UN MÓVIL / CELULAR ---");
        System.out.println("\n=====================================================================");
        System.out.println("\n--- AUTENTICACIÓN POR SMS ---");
        System.out.println("[SMS enviado a " + usuario.getPhone() + "]: Tu código es " + codigoGenerado);
        System.out.println("\n=====================================================================");
        
        // 3. Solicitar entrada al usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresar código de verificación: ");
        String codigoIngresado = scanner.nextLine().trim();

        // 4. Validar resultado
        return codigoGenerado.equals(codigoIngresado);
    }
    
    public static String generarCodigoSMS() {
        Random random = new Random();
        int numero = random.nextInt(1000000); // Genera desde 0 hasta 999999
        return String.format("%06d", numero); // Formatea a exactamente 6 dígitos (ej: "048219")
    }
}

