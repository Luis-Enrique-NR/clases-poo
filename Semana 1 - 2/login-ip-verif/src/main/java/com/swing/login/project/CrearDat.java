package com.swing.login.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.io.IOException;

public class CrearDat {

    private static final int W = 130; // Ancho de registro estandarizado por el profesor
    private static final String TXT_FILE = "Usuarios.txt";
    private static final String DAT_FILE = "Usuarios.dat";

    public static void main(String[] args) {
        System.out.println("Iniciando migración de " + TXT_FILE + " a " + DAT_FILE + "...");

        try (BufferedReader br = new BufferedReader(new FileReader(TXT_FILE));
             RandomAccessFile raf = new RandomAccessFile(DAT_FILE, "rw")) {

            // Sobrescribe/Limpia el archivo .dat previo para no duplicar datos
            raf.setLength(0);

            String linea;
            int contador = 0;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split("\\|");
                if (datos.length >= 5) {
                    String id = datos[0].trim();
                    String nombre = datos[1].trim();
                    String usuario = datos[2].trim();
                    String password = datos[3].trim();
                    String estado = datos[4].trim();

                    // Formatea los datos a los offsets exactos del profesor
                    String registroFormateado = formatearRegistro(id, nombre, usuario, password, estado);

                    // Convierte la cadena formateada a buffer de bytes
                    byte[] buffer = stringToBuffer(registroFormateado);

                    // Escribe directamente en el archivo binario
                    raf.write(buffer);
                    contador++;
                }
            }

            System.out.println("¡Migración exitosa! Se generó " + DAT_FILE + " con " + contador + " registros.");

        } catch (IOException e) {
            System.err.println("Error durante la creación del archivo .dat: " + e.getMessage());
        }
    }

    /**
     * Construye la cadena de 130 caracteres garantizando los offsets de lectura:
     * - Posición 0: Marca de borrado (' ' para activo, '*' para eliminado)
     * - Posición 53 a 67: Usuario (15 caracteres)
     * - Posición 69 a 78: Contraseña (10 caracteres)
     * - Posición 80: Estado ('1' activo, '0' inactivo)
     */
    private static String formatearRegistro(String id, String nombre, String usuario, String password, String estado) {
        StringBuilder sb = new StringBuilder();

        sb.append(" "); // Posición 0: Control de borrado lógico

        // Posiciones 1-3: ID (3 caracteres)
        sb.append(padRight(id, 3));

        // Posiciones 4-52: Nombre completo (49 caracteres)
        sb.append(padRight(nombre, 49));

        // Posiciones 53-67: Usuario (15 caracteres exactos)
        sb.append(padRight(usuario, 15));

        sb.append(" "); // Posición 68: Separador

        // Posiciones 69-78: Contraseña (10 caracteres exactos)
        sb.append(padRight(password, 10));

        sb.append(" "); // Posición 79: Separador

        // Posición 80: Estado ('1' o '0')
        sb.append(estado.length() > 0 ? estado.charAt(0) : '0');

        // Completa con espacios hasta llenar exactamente los 130 bytes
        while (sb.length() < W) {
            sb.append(" ");
        }

        return sb.toString();
    }

    // Método utilitario para rellenar espacios a la derecha hasta una longitud fija
    private static String padRight(String texto, int longitud) {
        if (texto.length() >= longitud) {
            return texto.substring(0, longitud);
        }
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < longitud) {
            sb.append(" ");
        }
        return sb.toString();
    }

    // Convierte String a arreglo de bytes de tamaño W (Estilo TLib del profesor)
    private static byte[] stringToBuffer(String cadena) {
        byte[] buffer = new byte[W];
        for (int i = 0; i < W; i++) {
            if (i < cadena.length()) {
                buffer[i] = (byte) cadena.charAt(i);
            } else {
                buffer[i] = (byte) ' ';
            }
        }
        return buffer;
    }
}