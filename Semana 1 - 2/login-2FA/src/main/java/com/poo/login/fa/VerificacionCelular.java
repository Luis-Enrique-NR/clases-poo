package com.poo.login.fa;

import java.util.Scanner;

public class VerificacionCelular implements FactorAutenticacion{
    
    @Override
    public boolean autenticar(Usuario usuario) {
        if (usuario.getPhone() == null || usuario.getPhone().trim().isEmpty()) {
            System.out.println("[ERROR 2FA] El usuario no tiene un dispositivo móvil vinculado.");
            return false;
        }

        // 1. Simular la notificación emergente de Google
        System.out.println("\n=====================================================================");
        System.out.println("\n--- SIMULANDO UN MÓVIL / CELULAR ---");
        System.out.println("\n=====================================================================");
        System.out.println("\n--- CONFIRMACIÓN EN DISPOSITIVO MÓVIL ---");
        System.out.println("Notificación enviada al dispositivo vinculado con " + usuario.getPhone());
        System.out.println("¿Estás intentando acceder desde otro dispositivo?");
        System.out.print("Presione [S] para 'Sí, soy yo' / [N] para 'No, no soy yo': ");
        
        System.out.println("\n=====================================================================");

        // 2. Leer respuesta
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().trim();

        // 3. Validar si aceptó la notificación
        return respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("Y");
    }
}
