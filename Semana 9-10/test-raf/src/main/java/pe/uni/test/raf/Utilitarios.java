package pe.uni.test.raf;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Utilitarios de bajo nivel para operaciones de RandomAccessFile.
 * Encapsula toda la técnica de conversión de tipos, formato de bytes fijos
 * y cálculo de offsets para que ServicioCreditos.java se mantenga limpio
 * y focuse en la lógica de negocio (créditos vehiculares).
 *
 * Principio: un solo lugar donde cambiar el formato de registro (tamaños de campos,
 * endianness, tipos de datos) sin tener que tocar la lógica de negocio.
 */
public class Utilitarios {

    // ---------------------- TAMAÑOS DE CAMPO (configuración de registro) ----------------------
    public static final int TAMANIO_DNI = 8;
    public static final int TAMANIO_PLACA = 6;
    public static final int TAMANIO_COD_CREDITO = 6;
    public static final int TAMANIO_MONTO = 8;
    public static final int TAMANIO_ESTADO = 4;
    public static final int TAMANIO_FECHA = 8;
    public static final int TAMANO_REGISTRO = 40; // 8+6+6+8+4+8

    // offsets iniciales de cada campo dentro de un registro de 40 bytes
    public static final int OFFSET_DNI = 0;
    public static final int OFFSET_PLACA = TAMANIO_DNI; // 8
    public static final int OFFSET_COD_CREDITO = 14; // 8+6
    public static final int OFFSET_MONTO = 20; // 8+6+6
    public static final int OFFSET_ESTADO = 28; // 8+6+6+8
    public static final int OFFSET_FECHA = 32; // 8+6+6+8+4

    // ---------------------- ESCRITURA DE STRING FIJO ----------------------

    /**
     * Escribe un String en el RandomAccessFile en la posición actual,
     * rellenando con espacios en blanco a la derecha si el String es más corto
     * que el tamaño máximo.
     * <p>
     * Si el String tiene más caracteres que {@code size}, se truncará
     * silenciosamente (el caller debe asegurar que la longitud ≤ size).
     *
     * @param raf  archivo abierto en modo escritura
     * @param s    cadena a escribir
     * @param size tamaño fijo en bytes (ej. Utilitarios.TAMANIO_DNI)
     * @throws IOException si falla la escritura
     */
    public static void escribirStringFijo(RandomAccessFile raf, String s, int size) throws IOException {
        byte[] bytes = s.getBytes("UTF-8");
        if (bytes.length >= size) {
            raf.write(bytes, 0, size);
        } else {
            byte[] completo = new byte[size];
            System.arraycopy(bytes, 0, completo, 0, bytes.length);
            
            for (int i = bytes.length; i < size; i++) {
                completo[i] = (byte) ' ';
            }
            raf.write(completo);
        }
    }

    /**
     * Lee un campo de String de tamaño fijo desde el RandomAccessFile en la
     * posición actual y devuelve el String con los espacios a la derecha eliminados (trim).
     *
     * @param raf  archivo abierto en modo lectura
     * @param size tamaño fijo en bytes
     * @return String leído sin espacios finales
     * @throws IOException si falla la lectura
     */
    public static String leerStringFijo(RandomAccessFile raf, int size) throws IOException {
        byte[] buf = new byte[size];
        
        raf.read(buf);
        String s = new String(buf, "UTF-8").trim();
        return s;
    }
    
    // ---------------------- ESCRITURA / LECTURA DE DOUBLE (8 bytes) ----------------------

    /**
     * Escribe un double (8 bytes) en el archivo en orden little-endian
     * (byte 0 = byte menos significativo). Este método es el encargado de
     * garantizar que el formato sea consistente con leerDouble().
     * <p>
     * ¡CUIDADO! El cast a byte es necesario porque RandomAccessFile.write(int)
     * acepta un int, pero queremos escribir un byte (0-255).
     *
     * @param raf  archivo abierto en modo escritura
     * @param val valor double a escribir
     * @throws IOException si falla la escritura
     */
    public static void escribirDouble(RandomAccessFile raf, double val) throws IOException {
        raf.writeDouble(val);
    }

    /**
     * Lee un double (8 bytes) que fue escrito con escribirDouble() (little-endian).
     *
     * @param raf archivo abierto en modo lectura
     * @return double leído
     * @throws IOException si falla la lectura
     */
    public static double leerDouble(RandomAccessFile raf) throws IOException {
        return raf.readDouble();
    }

    // ---------------------- ESCRITURA / LECTURA DE INT (4 bytes) ----------------------

    /**
     * Escribe un int (4 bytes) en el archivo en orden little-endian.
     *
     * @param raf archivo abierto en modo escritura
     * @param val valor int a escribir
     * @throws IOException si falla la escritura
     */
    public static void escribirInt(RandomAccessFile raf, int val) throws IOException {
        raf.writeInt(val);
    }

    /**
     * Lee un int (4 bytes) escrito con escribirInt() (little-endian).
     *
     * @param raf archivo abierto en modo lectura
     * @return int leído
     * @throws IOException si falla la lectura
     */
    public static int leerInt(RandomAccessFile raf) throws IOException {
        return raf.readInt();
    }

    // ---------------------- ESCRITURA / LECTURA DE LONG (8 bytes) ----------------------

    /**
     * Escribe un long (8 bytes) en el archivo en orden little-endian.
     * Igual que escribirDouble, escribe byte a byte desde el menos significativo.
     * <p>
     * ¡CUIDADO! El cast a byte es necesario para RandomAccessFile.write(int).
     *
     * @param raf archivo abierto en modo escritura
     * @param val valor long a escribir (ej. epoch milliseconds)
     * @throws IOException si falla la escritura
     */
    public static void escribirLong(RandomAccessFile raf, long val) throws IOException {
        raf.writeLong(val);
        
        /*for (int i = 0; i < 8; i++) {
            // Cast a byte: el valor despues de & 0XFF es un int 0-255, lo casteamos a byte
            raf.write((byte) ((val >> (i * 8)) & 0xFF));
        }*/
    }

    /**
     * Lee un long (8 bytes) escrito con escribirLong() (little-endian).
     *
     * @param raf archivo abierto en modo lectura
     * @return long leído
     * @throws IOException si falla la lectura
     */
    public static long leerLong(RandomAccessFile raf) throws IOException {
        return raf.readLong();
        
        /*long val = 0;
        for (int i = 0; i < 8; i++) {
            val |= ((long) (raf.read() & 0xFF)) << (i * 8);
        }
        return val;
        */
    }

    // ---------------------- MÉTODO DE CONVENIENCIA: OFFSETS ----------------------

    /**
     * Retorna los offsets iniciales de cada campo dentro de un registro de 40 bytes.
     * Útil para seek preciso sin contar manualmente.
     *
     * @return array int de longitud 6: [offset_dni, offset_placa, offset_cod_credito,
     *         offset_monto, offset_estado, offset_fecha]
     */
    public static int[] offsetsRegistro() {
        return new int[]{OFFSET_DNI, OFFSET_PLACA, OFFSET_COD_CREDITO, OFFSET_MONTO, OFFSET_ESTADO, OFFSET_FECHA};
    }

    // ---------------------- VALIDACIÓN DE FORMATO DE PLACA ----------------------

    /**
     * Valida que la placa tenga formato 3 letras + 3 números.
     *
     * @param placa cadena de exactamente 6 caracteres
     * @return true si tiene formato correcto, false en caso contrario
     */
    public static boolean esFormatoPlacaValida(String placa) {
        if (placa == null || placa.length() != TAMANIO_PLACA) return false;
        for (int i = 0; i < 3; i++) if (!java.lang.Character.isLetter(placa.charAt(i))) return false;
        for (int i = 3; i < 6; i++) if (!java.lang.Character.isDigit(placa.charAt(i))) return false;
        return true;
    }

    // Constructor privado para evitar instanciación
    private Utilitarios() {}
}