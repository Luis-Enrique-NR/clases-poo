package com.poo.login.fa;

import java.util.Random;
import java.util.Scanner;

public class VerificacionEmail implements FactorAutenticacion{
    
    @Override
    public boolean autenticar(Usuario usuario) {
        // 1. Generar código alfanumérico de 6 caracteres
        String codigoGenerado = generarCodigoEmail();

        // 2. Simular envío a Gmail
        System.out.println("\n=====================================================================");
        System.out.println("\n--- SIMULANDO LA BANDEJA DE GMAIL ---");
        System.out.println("\n=====================================================================");
        System.out.println("\n--- AUTENTICACIÓN POR EMAIL ---");
        System.out.println("[Correo enviado a " + usuario.getEmail() + "]: Tu código es " + codigoGenerado);
        System.out.println("\n=====================================================================");

        // 3. Solicitar entrada al usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresar código de verificación: ");
        String codigoIngresado = scanner.nextLine().trim();

        // 4. Validar resultado (sin diferenciar mayúsculas de minúsculas)
        return codigoGenerado.equalsIgnoreCase(codigoIngresado);
    }
    
    public static String generarCodigoEmail() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(indiceAleatorio));
        }

        return sb.toString(); // Retorna una cadena de 6 caracteres (ej: "A8K39Z")
    }
}
