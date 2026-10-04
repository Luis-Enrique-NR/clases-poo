package com.poo.login.fa;

import java.util.Scanner;


public class Login2FA {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        Usuario estudiante01 = new Usuario("fernanda.perez.ram", "123fjwiuf", "fer.perez@gmail.com");
        
        Usuario estudiante02 = new Usuario("mayra.gonzales.dan", "fjo3igoi3", "mayra.gonz@gmail.com", "912134232");
        
           
        Usuario usuarioActual = estudiante01;
        
        System.out.println("==========================================");
        System.out.println("   SISTEMA DE AUTENTICACIÓN DE 2 FACTORES   ");
        System.out.println("==========================================");
        System.out.println("Credenciales aceptadas para: " + usuarioActual.getUsername());
        System.out.println("Seleccione el método para confirmar su identidad:");
        System.out.println("1. Mensaje de Texto (SMS)");
        System.out.println("2. Correo Electrónico (Email)");
        System.out.println("3. Confirmación en Celular (Notificación Push)");
        System.out.print("Ingrese una opción (1-3): ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea del scanner

        // Variable del tipo de la INTERFAZ (Abstracción)
        FactorAutenticacion factorSeleccionado = null;
        
        // 2. Selección dinámica de la implementación según la opción
        switch (opcion) {
            case 1:
                // VerificacionSMS verificacionSMS = new VerificacionSMS();
                
                // accesoConcedido = verificacionSMS.autenticar(usuarioActual);
                
                factorSeleccionado = new VerificacionSMS();
                break;
            case 2:
                // VerificacionEmail verificacionEmail = new VerificacionEmail();
                
                // accesoConcedido = verificacionEmail.autenticar(usuarioActual);
                
                factorSeleccionado = new VerificacionEmail();
                break;
            case 3:
                //VerificacionCelular verificacionCelular = new VerificacionCelular();
                
                // accesoConcedido = verificacionCelular.autenticar(usuarioActual);
                
                factorSeleccionado = new VerificacionCelular();
                break;
                
            
            case 4:
                //VerificacionLlamada verificacionLlamada = new VerificacionLlamada();
                
                //accesoConcedido = verificacionLlamada.autenticar(usuarioActual);
                
                factorSeleccionado = new VerificacionLlamada();
                
            default:
                System.out.println("\n[ERROR] Opción no válida. Proceso cancelado.");
                return;
        }

        // 3. Ejecución polimórfica del método definido en el contrato
        boolean accesoConcedido = factorSeleccionado.autenticar(usuarioActual);

        // 4. Resultado final
        System.out.println("------------------------------------------");
        if (accesoConcedido) {
            System.out.println("SUCCESS: ¡Autenticación 2FA exitosa! Bienvenido al sistema.");
        } else {
            System.out.println("DENIED: Código o confirmación incorrecta. Acceso denegado.");
        }
        System.out.println("------------------------------------------");
    }
}
