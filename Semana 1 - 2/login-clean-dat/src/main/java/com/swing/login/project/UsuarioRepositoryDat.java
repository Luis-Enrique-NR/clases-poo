package com.swing.login.project;

import java.io.RandomAccessFile;
import java.io.IOException;

public class UsuarioRepositoryDat implements UsuarioRepository {

    private String FILENAME = "Usuarios.dat";
    private int ancho_registro = 130; // Tamaño fijo de cada registro

    // Convertir el arreglo de bytes a string
    public String BufferToString(byte[] BUFFER) {
        String CADENA = "";
        int L = BUFFER.length;
        for (int i = 0; i <= L - 1; i++) {
            CADENA = CADENA + (char) BUFFER[i];
        }
        return CADENA;
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        long peso_total_bytes, total_registros;
        byte[] BUFFER = new byte[ancho_registro];
        String CADENA = "";
        Usuario USUARIO_ENCONTRADO = null;

        try {
            RandomAccessFile RAF = new RandomAccessFile(FILENAME, "r");
            peso_total_bytes = RAF.length();
            total_registros = peso_total_bytes / ancho_registro; // Calcula la cantidad total de registros almacenados

            for (int k = 1; k <= total_registros; k++) {
                RAF.seek((k - 1) * ancho_registro); // Salta directamente al byte del registro k
                RAF.read(BUFFER);
                CADENA = BufferToString(BUFFER);

                // Verifica que el registro no esté borrado lógicamente ('*')
                if (CADENA.charAt(0) != '*') {
                    // Extrae las columnas según las posiciones exactas fijadas por el profesor
                    String USER_FILE = CADENA.substring(53, 68).trim();

                    if (USER_FILE.equalsIgnoreCase(username.trim())) {
                        String PASS_FILE = CADENA.substring(69, 79).trim();
                        boolean ESTADO = (CADENA.charAt(80) == '1');

                        USUARIO_ENCONTRADO = new Usuario(USER_FILE, PASS_FILE, ESTADO);
                        break; // Usuario encontrado, termina el bucle
                    }
                }
            }
            RAF.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo DAT: " + e.getMessage());
        }

        return USUARIO_ENCONTRADO;
    }
}