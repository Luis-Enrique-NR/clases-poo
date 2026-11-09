package pe.uni.test.raf;

import java.io.File;
import java.io.IOException;

/**
 * Servicio para operaciones de créditos vehiculares usando Random Access File.
 * 
 * Cada registro tiene tamaño FIXO de 40 bytes.
 * 
 * NOTA: Los métodos técnicos de conversión String↔Bytes y escritura/lectura
 * de tipos primitivos están encapsulados en la clase Utilitarios.java.
 * Para usarlos, importar pe.uni.test.raf.Utilitarios y llamar a los métodos
 * correspondientes (escribirStringFijo, leerDouble, escribirInt, etc.).
 * 
 * Esta clase se enfoca en la lógica de negocio (créditos vehiculares):
 * solicitar, consultar, aprobar, rechazar, listar y historial general.
 */
public class ServicioCreditos {

    // Tamaño fijo de registro: 40 bytes
    // Distribución: dni(8) + placa(6) + cod_credito(6) + monto(8) + estado(4) + fecha(8)
    public static final int TAMANO_REGISTRO = 40;

    // Posiciones de los campos dentro del registro (offsets)
    private static final int OFFSET_DNI = 0;
    private static final int OFFSET_PLACA = 8;
    private static final int OFFSET_COD_CREDITO = 14;
    private static final int OFFSET_MONTO = 20;
    private static final int OFFSET_ESTADO = 28;
    private static final int OFFSET_FECHA = 32;

    // Nombres de los estados
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_APROBADO = 1;
    public static final int ESTADO_RECHAZADO = 2;
    public static final int ESTADO_PAGADO = 3;
    
    

    // Nombre del archivo
    private static final String NOMBRE_ARCHIVO = "creditos.dat";

    /**
     * T1: Crear estructura inicial.
     */
    public static boolean crearEstructuraInicial() {
        File archivo = new java.io.File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            try {
                java.io.RandomAccessFile raf = new java.io.RandomAccessFile(archivo, "rw");
                raf.close();
                System.out.println("Archivo creditos.dat creado nuevo (vacío).");
                return true;
            } catch (IOException e) {
                System.err.println("Error creando archivo: " + e.getMessage());
                return false;
            }
        } else {
            if (archivo.length() % TAMANO_REGISTRO == 0) {
                System.out.println("Estructura válida: " + (archivo.length() / TAMANO_REGISTRO) + " registros.");
                return true;
            } else if (archivo.length() == 0) {
                System.out.println("Archivo existe pero vacío (0 bytes).");
                return true;
            } else {
                System.err.println("ADVERTENCIA: Archivo longitud " + archivo.length() + " no es múltiplo de " + TAMANO_REGISTRO);
                return false;
            }
        }
    }

    /**
     * T2: Solicitar nuevo crédito.
     */
    public static String solicitarCredito(String dni, String placa, double monto) {
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("DNI debe tener 8 dígitos. Recibido: '" + dni + "'");
        }
        if (placa == null || placa.length() != 6) {
            throw new IllegalArgumentException("Placa debe tener 6 caracteres. Recibido: '" + placa + "'");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto debe ser mayor a 0. Recibido: " + monto);
        }
        if (!crearEstructuraInicial()) return null;

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "rw");
            raf.seek(raf.length());

            // Escribir dni (8 bytes) - rellenando con espacios si es necesario
            byte[] dniBytes = dni.getBytes("UTF-8"); // ---> [2,1,3,1,3,1,3]
            byte[] dniEscritura = new byte[8]; 
            System.arraycopy(dniBytes, 0, dniEscritura, 0, dniBytes.length);
            // FLUJO DEL ARRAYCOPY 
            // PRIMERO
            // TIENES dniEscritura [,,,,,,,]
            // SE TOMA [2,1,3,1,3,1,3] ---> LA COPIA TOMANDO DESDE LA POSICION INICIAL DE dniBytes
            // SEGUNDO
            // [2,1,3,1,3,1,3,-] --> RESULTADO EN dniEscritura
            
            for (int i = dniBytes.length; i < 8; i++) {
                dniEscritura[i] = (byte) ' ';
            }
            raf.write(dniEscritura);
            
            // Escribir placa (6 bytes)
            byte[] placaBytes = placa.getBytes("UTF-8");
            byte[] placaEscritura = new byte[6];
            System.arraycopy(placaBytes, 0, placaEscritura, 0, placaBytes.length);
            for (int i = placaBytes.length; i < 6; i++) {
                placaEscritura[i] = (byte) ' ';
            }
            raf.write(placaEscritura);
            
            // Código CR-001 (simplificado)
            String cod = "CR-001";
            byte[] codBytes = cod.getBytes("UTF-8");
            byte[] codEscritura = new byte[6];
            System.arraycopy(codBytes, 0, codEscritura, 0, codBytes.length);
            for (int i = codBytes.length; i < 6; i++) {
                codEscritura[i] = (byte) ' ';
            }
            raf.write(codEscritura);
            
            // Escribir monto (8 bytes double) - little-endian manual
            raf.writeDouble(monto);
            
            /*
            byte[] montoBytes = new byte[8];
            long longMonto = java.lang.Double.doubleToLongBits(monto);
            for (int i = 0; i < 8; i++) {
                montoBytes[i] = (byte) (longMonto >> (i * 8));
            }
            raf.write(montoBytes);
            */

            // Escribir estado (4 bytes int) = 0 (PENDIENTE)
            raf.writeInt(ESTADO_PENDIENTE);
            
            /*
            byte[] estadoBytes = new byte[4];
            for (int i = 0; i < 4; i++) {
                estadoBytes[i] = (byte) (0 >> (i * 8));
            }
            raf.write(estadoBytes);
            */

            // Escribir fecha epoch (8 bytes long)
            long horaBits = System.currentTimeMillis();
            raf.writeLong(horaBits);
            /*
            byte[] fechaBytes = new byte[8];
            long horaBits = System.currentTimeMillis();
            for (int i = 0; i < 8; i++) {
                fechaBytes[i] = (byte) (horaBits >> (i * 8));
            }
            raf.write(fechaBytes);
            */

            raf.close();

            System.out.println("=== Crédito solicitado ===");
            System.out.println("Código: " + cod);
            System.out.println("DNI: " + dni + " | Placa: " + placa + " | Monto: S/ " + String.format("%.2f", monto));
            System.out.println("Estado: PENDIENTE");
            System.out.println("----------------------------------------");
            return cod;
        } catch (IOException e) {
            System.err.println("Error escribiendo en archivo: " + e.getMessage());
            return null;
        }
    }

    /**
     * T3: Consultar estado de un crédito.
     */
    public static String consultarEstado(String codCredito) {
        if (codCredito == null || !codCredito.matches("CR-\\d{3}")) {
            return "Formato de código inválido. Use CR-XXX (ej: CR-001).";
        }

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "r");
            long largo = raf.length();
            int totalRegistros = (int) (largo / TAMANO_REGISTRO);

            for (int i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO);

                // Leer dni (8 bytes)
                byte[] dniBytes = new byte[8];
                raf.read(dniBytes);
                String dni = new String(dniBytes, "UTF-8").trim();

                // Leer placa (6 bytes)
                byte[] placaBytes = new byte[6];
                raf.read(placaBytes);
                String placa = new String(placaBytes, "UTF-8").trim();

                // Leer cod_credito (6 bytes)
                byte[] codBytes = new byte[6];
                raf.read(codBytes);
                String cod = new String(codBytes, "UTF-8").trim();

                // Leer monto (8 bytes double) 
                double monto = raf.readDouble();
                /*
                byte[] montoBytes = new byte[8];
                raf.read(montoBytes);
                double monto = 0;
                for (int j = 7; j >= 0; j--) {
                    monto = monto * 256 + (montoBytes[j] & 0xFF);
                }
                */

                // Leer estado (4 bytes int)
                int estado = raf.readInt();
                
                /*
                byte[] estadoBytes = new byte[4];
                raf.read(estadoBytes);
                int estado = 0;
                for (int j = 3; j >= 0; j--) {
                    estado = estado * 256 + (estadoBytes[j] & 0xFF);
                }
                */

                // Leer fecha epoch (8 bytes long)
                long fechaEpoch = raf.readLong();
                /*
                byte[] fechaBytes = new byte[8];
                raf.read(fechaBytes);
                long fechaEpoch = 0;
                for (int j = 7; j >= 0; j--) {
                    fechaEpoch = fechaEpoch * 256 + (fechaBytes[j] & 0xFF);
                }
                */

                if (cod.equals(codCredito)) {
                    String nombreEstado;
                    switch (estado) {
                        case ESTADO_PENDIENTE: nombreEstado = "PENDIENTE"; break;
                        case ESTADO_APROBADO: nombreEstado = "APROBADO"; break;
                        case ESTADO_RECHAZADO: nombreEstado = "RECHAZADO"; break;
                        case ESTADO_PAGADO: nombreEstado = "PAGADO"; break;
                        default: nombreEstado = "DESCONOCIDO"; break;
                    }

                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd"); //HH:mm:ss");
                    String fechaLegible = (fechaEpoch > 0) ? sdf.format(new java.util.Date(fechaEpoch)) : "Fecha no disponible";

                    return "Código: " + cod + "\nDNI: " + dni + "\nPlaca: " + placa + "\nMonto: S/ " + String.format("%.2f", monto) + "\nEstado: " + nombreEstado + "\nFecha: " + fechaLegible;
                }
            }
            return "Solicitud " + codCredito + " no encontrada.";
        } catch (IOException e) {
            return "Error leyendo archivo: " + e.getMessage();
        }
    }

    /**
     * T4: Listar solicitudes por cliente (DNI).
     */
    public static String listarSolicitudesCliente(String dni) {
        if (dni == null || dni.length() != 8) {
            return "DNI inválido: debe tener 8 dígitos.";
        }

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "r");
            long largo = raf.length();
            int totalRegistros = (int) (largo / TAMANO_REGISTRO);

            boolean encontro = false;
            StringBuilder reporte = new StringBuilder();
            reporte.append("--- Solicitudes del DNI ").append(dni).append(" ---\n\n");
            
            for (int i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO);

                byte[] dniBytes = new byte[8];
                raf.read(dniBytes);
                String dniActual = new String(dniBytes, "UTF-8").trim();

                if (dniActual.equals(dni)) {
                    encontro = true;
                    byte[] placaBytes = new byte[6];
                    raf.read(placaBytes);
                    String placa = new String(placaBytes, "UTF-8").trim();

                    byte[] codBytes = new byte[6];
                    raf.read(codBytes);
                    String cod = new String(codBytes, "UTF-8").trim();

                    byte[] montoBytes = new byte[8];
                    raf.read(montoBytes);
                    double monto = 0;
                    for (int j = 7; j >= 0; j--) {
                        monto = monto * 256 + (montoBytes[j] & 0xFF); //--> Reemplaza por readDouble
                    }

                    byte[] estadoBytes = new byte[4];
                    raf.read(estadoBytes);
                    int estado = 0;
                    for (int j = 3; j >= 0; j--) {
                        estado = estado * 256 + (estadoBytes[j] & 0xFF); //--> Reemplaza por readInt
                    }

                    byte[] fechaBytes = new byte[8];
                    raf.read(fechaBytes);
                    long fechaEpoch = 0;
                    for (int j = 7; j >= 0; j--) {
                        fechaEpoch = fechaEpoch * 256 + (fechaBytes[j] & 0xFF); //--> Reemplaza por readLong
                    }

                    String nombreEstado;
                    switch (estado) {
                        case ESTADO_PENDIENTE: nombreEstado = "PENDIENTE"; break;
                        case ESTADO_APROBADO: nombreEstado = "APROBADO"; break;
                        case ESTADO_RECHAZADO: nombreEstado = "RECHAZADO"; break;
                        case ESTADO_PAGADO: nombreEstado = "PAGADO"; break;
                        default: nombreEstado = "DESCONOCIDO"; break;
                    }

                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fechaLegible = (fechaEpoch > 0) ? sdf.format(new java.util.Date(fechaEpoch)) : "Fecha no disponible";

                    reporte.append("Código: ").append(cod).append("\n");
                    reporte.append("  Placa: ").append(placa).append("\n");
                    reporte.append("  Monto: S/ ").append(String.format("%.2f", monto)).append("\n");
                    reporte.append("  Estado: ").append(nombreEstado).append("\n");
                    reporte.append("  Fecha: ").append(fechaLegible).append("\n");
                    reporte.append("----------------------------------------\n\n");
                }
            }
            raf.close();
            if (encontro) {
                return reporte.toString();
            } else {
                return "No hay solicitudes para DNI: " + dni;
            }
        } catch (IOException e) {
            return "Error listando solicitudes: " + e.getMessage();
        }
    }

    /**
     * T5: Aprobar crédito.
     */
    public static boolean aprobarCredito(String codCredito) {
        if (codCredito == null || !codCredito.matches("CR-\\d{3}")) return false;

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "rw");
            long largo = raf.length();
            int totalRegistros = (int) (largo / TAMANO_REGISTRO);

            for (int i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO + OFFSET_COD_CREDITO);
                
                byte[] codBytes = new byte[6];
                raf.read(codBytes);
                String codActual = new String(codBytes, "UTF-8").trim();

                if (codActual.equals(codCredito)) {
                    raf.seek(i * TAMANO_REGISTRO + OFFSET_ESTADO);

                    byte[] estadoBytes = new byte[4];
                    raf.read(estadoBytes);
                    int estadoActual = 0;
                    for (int j = 3; j >= 0; j--) {
                        estadoActual = estadoActual * 256 + (estadoBytes[j] & 0xFF); //Reemplazar con readInt
                    }

                    if (estadoActual != ESTADO_PENDIENTE) return false; // Ya estaba aprobado/rechazado

                    // Escribir estado = 1 (APROBADO)
                    byte[] nuevoEstado = new byte[4];
                    for (int j = 0; j < 4; j++) {
                        nuevoEstado[j] = (byte) (1 >> (j * 8)); //Reemplazar con writeInt
                    }
                    raf.seek(i * TAMANO_REGISTRO + OFFSET_ESTADO);
                    raf.write(nuevoEstado);
                    raf.close();
                    return true;
                }
            }
            raf.close();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * T6: Rechazar crédito.
     */
    public static boolean rechazarCredito(String codCredito) {
        if (codCredito == null || !codCredito.matches("CR-\\d{3}")) return false;

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "rw");
            long largo = raf.length();
            int totalRegistros = (int) (largo / TAMANO_REGISTRO);

            for (int i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO + OFFSET_COD_CREDITO);

                byte[] codBytes = new byte[6];
                raf.read(codBytes);
                String codActual = new String(codBytes, "UTF-8").trim();

                if (codActual.equals(codCredito)) {
                    raf.seek(i * TAMANO_REGISTRO + OFFSET_ESTADO);

                    byte[] estadoBytes = new byte[4];
                    raf.read(estadoBytes);
                    int estadoActual = 0;
                    for (int j = 3; j >= 0; j--) {
                        estadoActual = estadoActual * 256 + (estadoBytes[j] & 0xFF);
                    }

                    if (estadoActual != ESTADO_PENDIENTE) return false;

                    // Escribir estado = 2 (RECHAZADO)
                    byte[] nuevoEstado = new byte[4];
                    for (int j = 0; j < 4; j++) {
                        nuevoEstado[j] = (byte) (2 >> (j * 8));
                    }
                    raf.seek(i * TAMANO_REGISTRO + OFFSET_ESTADO);
                    raf.write(nuevoEstado);
                    raf.close();
                    return true;
                }
            }
            raf.close();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * T7: Historial general.
     */
    public static String historialGeneral() {
        try {
            java.io.File archivo = new java.io.File(NOMBRE_ARCHIVO);
            if (archivo.length() == 0) {
                return "No hay solicitudes registradas.";
            }
        } catch (Exception e) {
            return "Error verificando archivo: " + e.getMessage();
        }

        try {
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile(NOMBRE_ARCHIVO, "r");
            long largo = raf.length();
            int totalRegistros = (int) (largo / TAMANO_REGISTRO);

            StringBuilder reporte = new StringBuilder();
            reporte.append("--- HISTORIAL GENERAL DE SOLICITUDES ---\n");
            reporte.append("Total: ").append(totalRegistros).append("\n");
            reporte.append("========================================\n\n");

            if (totalRegistros == 0) {
                reporte.append("No hay solicitudes registradas.\n");
                raf.close();
                return reporte.toString();
            }

            for (int i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO);

                byte[] dniBytes = new byte[8];
                raf.read(dniBytes);
                String dni = new String(dniBytes, "UTF-8").trim();

                byte[] placaBytes = new byte[6];
                raf.read(placaBytes);
                String placa = new String(placaBytes, "UTF-8").trim();

                byte[] codBytes = new byte[6];
                raf.read(codBytes);
                String cod = new String(codBytes, "UTF-8").trim();

                byte[] montoBytes = new byte[8];
                raf.read(montoBytes);
                double monto = 0;
                for (int j = 7; j >= 0; j--) {
                    monto = monto * 256 + (montoBytes[j] & 0xFF);
                }

                byte[] estadoBytes = new byte[4];
                raf.read(estadoBytes);
                int estado = 0;
                for (int j = 3; j >= 0; j--) {
                    estado = estado * 256 + (estadoBytes[j] & 0xFF);
                }

                byte[] fechaBytes = new byte[8];
                raf.read(fechaBytes);
                long fechaEpoch = 0;
                for (int j = 7; j >= 0; j--) {
                    fechaEpoch = fechaEpoch * 256 + (fechaBytes[j] & 0xFF);
                }

                String nombreEstado;
                switch (estado) {
                    case ESTADO_PENDIENTE: nombreEstado = "PENDIENTE"; break;
                    case ESTADO_APROBADO: nombreEstado = "APROBADO"; break;
                    case ESTADO_RECHAZADO: nombreEstado = "RECHAZADO"; break;
                    case ESTADO_PAGADO: nombreEstado = "PAGADO"; break;
                    default: nombreEstado = "DESCONOCIDO"; break;
                }

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaLegible = (fechaEpoch > 0) ? sdf.format(new java.util.Date(fechaEpoch)) : "Fecha no disponible";

                reporte.append("Solicitud ").append(cod).append(":\n");
                reporte.append("  DNI: ").append(dni).append("\n");
                reporte.append("  Placa: ").append(placa).append("\n");
                reporte.append("  Monto: S/ ").append(String.format("%.2f", monto)).append("\n");
                reporte.append("  Estado: ").append(nombreEstado).append("\n");
                reporte.append("  Fecha: ").append(fechaLegible).append("\n");
                reporte.append("----------------------------------------\n");
            }
            raf.close();
            return reporte.toString();
        } catch (IOException e) {
            return "Error generando historial: " + e.getMessage();
        }
    }

    
}