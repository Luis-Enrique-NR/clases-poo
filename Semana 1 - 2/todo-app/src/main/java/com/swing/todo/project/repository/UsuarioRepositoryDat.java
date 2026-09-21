package com.swing.todo.project.repository;

import com.swing.todo.project.entity.Usuario;
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
            total_registros = peso_total_bytes / ancho_registro; 

            for (int k = 1; k <= total_registros; k++) {
                RAF.seek((k - 1) * ancho_registro);
                RAF.read(BUFFER);
                CADENA = BufferToString(BUFFER);

                // Verifica que el registro no esté borrado lógicamente ('*')
                if (CADENA.charAt(0) != '*') {
                    
                    // 1. Extraer ID (posiciones 1 a 3)
                    String ID_FILE = CADENA.substring(1, 4).trim();
                    
                    // 2. Extraer Username (posiciones 53 a 67)
                    String USER_FILE = CADENA.substring(53, 68).trim();

                    if (USER_FILE.equalsIgnoreCase(username.trim())) {
                        String PASS_FILE = CADENA.substring(69, 79).trim();
                        boolean ESTADO = (CADENA.charAt(80) == '1');

                        // Instanciar con el nuevo ID
                        USUARIO_ENCONTRADO = new Usuario(ID_FILE, USER_FILE, PASS_FILE, ESTADO);
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