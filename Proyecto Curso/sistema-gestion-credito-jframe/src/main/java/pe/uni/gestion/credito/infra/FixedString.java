package pe.uni.gestion.credito.infra;

import java.io.IOException;
import java.io.RandomAccessFile;

public final class FixedString {

    private FixedString() {
    }

    /**
     * Escribe una cadena de texto de longitud fija en un archivo de acceso aleatorio.
     * Si la cadena es más corta que el espacio asignado, se rellena con espacios a la derecha.
     */
    public static void writeFixed(RandomAccessFile raf, String value, int length) throws IOException {
        // 1. Asegura que no sea nulo asignando una cadena vacía si es necesario
        String v = value == null ? "" : value;
        
        // 2. Itera exactamente sobre la longitud fija estipulada
        for (int i = 0; i < length; i++) {
            // Si la posición está dentro del texto, toma el carácter; si no, rellena con un espacio (' ')
            char c = i < v.length() ? v.charAt(i) : ' ';
            
            // Escribe el carácter en el archivo (cada char en Java usa 2 bytes con writeChar)
            raf.writeChar(c);
        }
    }

    /**
     * Lee una cadena de texto de longitud fija desde un archivo de acceso aleatorio.
     */
    public static String readFixed(RandomAccessFile raf, int length) throws IOException {
        StringBuilder sb = new StringBuilder(length);
        
        // 1. Lee exactamente la cantidad de caracteres que define la longitud fija
        for (int i = 0; i < length; i++) {
            sb.append(raf.readChar());
        }
        
        // 2. Convierte el resultado a String y elimina los espacios de relleno sobrantes
        return sb.toString().trim();
    }
}
