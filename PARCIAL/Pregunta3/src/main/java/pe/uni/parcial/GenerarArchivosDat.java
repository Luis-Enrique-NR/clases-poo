package pe.uni.parcial;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
interface RafWriter {
    void write(RandomAccessFile raf) throws IOException;
}

/**
 * Genera los archivos .DAT con registros de longitud fija exacta
 * según las especificaciones del examen.
 */
public class GenerarArchivosDat {

    // Longitudes de registro
    private static final int LEN_CLIENTES = 32;
    private static final int LEN_EMPLEADO = 19;
    private static final int LEN_CATEGORIAS = 33;
    private static final int LEN_PRODUCTOS = 37;
    private static final int LEN_CABECERA = 41;
    private static final int LEN_DETALLE = 15;

    public static void main(String[] args) throws IOException {
        System.out.println("=== Generando archivos .DAT ===\n");

        generarClientes();
        generarEmpleados();
        generarCategorias();
        generarProductos();
        generarCabeceraVentas();
        generarDetalleVentas();

        System.out.println("\n=== Archivos generados correctamente ===");
        verificarTamanios();
    }

    private static void escribirArchivo(String nombreArchivo, RafWriter escritor) throws IOException {
        // Eliminar archivo anterior si existe para evitar bytes residuales
        Path path = Path.of(nombreArchivo);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            escritor.write(raf);
        }
    }

    private static void generarClientes() throws IOException {
        // Id(4), RazonSocial(17), RUC(11) = 32
        String[][] datos = {
            {"C001", "Pedro Torres", "12345678901"},
            {"C002", "Imprenta ALFA", "11223344556"},
            {"C003", "Jose Alvarado", "20504060809"},
            {"C004", "Farmacia BETA", "99988877766"},
            {"C005", "Alejandro Diaz", "10020030012"},
            {"C006", "Maria Zavaleta", "65488812399"},
            {"C007", "Jorge Lozano", "44400033312"},
            {"C008", "Mecanica SIGMA", "99911100085"}
        };

        escribirArchivo("CLIENTES.DAT", raf -> {
            for (String[] d : datos) {
                String registro = String.format("%-4s%-17s%-11s", d[0], d[1], d[2]);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        } );
        System.out.println("CLIENTES.DAT: " + datos.length + " registros × " + LEN_CLIENTES + " bytes");
    }

    private static void generarEmpleados() throws IOException {
        // Id(3), NomApe(16) = 19
        String[][] datos = {
            {"E01", "Luis Arias"},
            {"E02", "Eva Calderon"},
            {"E03", "Rosa Campos"},
            {"E04", "Juan Soto"}
        };

        escribirArchivo("EMPLEADO.DAT", raf -> {
            for (String[] d : datos) {
                String registro = String.format("%-3s%-16s", d[0], d[1]);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        });
        System.out.println("EMPLEADO.DAT: " + datos.length + " registros × " + LEN_EMPLEADO + " bytes");
    }

    private static void generarCategorias() throws IOException {
        // Id(2), Descripcion(31) = 33
        String[][] datos = {
            {"1", "Bebidas y Gaseosas"},
            {"2", "Articulos de Limpieza"},
            {"3", "Equipos y Accesorios de Computo"},
            {"4", "Accesorios de Oficina"}
        };

        escribirArchivo("CATEGORIAS.DAT", raf -> {
            for (String[] d : datos) {
                String registro = String.format("%2s%-31s", d[0], d[1]);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        });
        System.out.println("CATEGORIAS.DAT: " + datos.length + " registros × " + LEN_CATEGORIAS + " bytes");
    }

    private static void generarProductos() throws IOException {
        // Id(4), Nombre(20), IdCategoria(1), Precio(11), IdMoneda(1) = 37
        String[][] datos = {
            {"P125", "Memoria USB 8Gb", "3", "70.00", "2"},
            {"P248", "Disco Externo 1 TB", "3", "500.00", "2"},
            {"P324", "Impresora Laser 2400", "3", "1000.00", "2"},
            {"P405", "Papel Bond A4 x 500", "4", "25.00", "1"},
            {"P510", "Energizante 1 Lt", "1", "20.00", "1"},
            {"P542", "Koca Kola 3 Lt", "1", "15.00", "1"},
            {"P630", "Inka Kola 1 Lt", "1", "5.00", "1"},
            {"P678", "Nectar Natura 2 Lt", "1", "12.00", "1"},
            {"P691", "Nectar ABC 1 Lt", "1", "8.00", "1"},
            {"P750", "Agua Mineral 5 Lt", "1", "12.00", "1"},
            {"P841", "Cera en Pasta Clean", "2", "30.00", "1"},
            {"P887", "Lejia CloroMAX 1 Lt", "2", "12.00", "1"},
            {"P905", "Detergente CloroMAX", "2", "4.00", "1"},
            {"P957", "Jabon Liquido Clean", "2", "25.00", "1"}
        };

        escribirArchivo("PRODUCTOS.DAT", raf -> {
            for (String[] d : datos) {
                String precio = String.format("%11s", d[3]);
                String registro = String.format("%-4s%-20s%-1s%-11s%-1s", d[0], d[1], d[2], precio, d[4]);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        });
        System.out.println("PRODUCTOS.DAT: " + datos.length + " registros × " + LEN_PRODUCTOS + " bytes");
    }

    private static void generarCabeceraVentas() throws IOException {
        // Id(4), Fecha(8), Hora(6), IdCliente(4), TipoPago(1), Monto(14), IdMoneda(1), IdEmpleado(3) = 41
        String[][] datos = {
            {"0001", "20250519", "091251", "C001", "1", "60.00", "1", "E01"},
            {"0002", "20250519", "103507", "C002", "2", "1350.00", "2", "E02"},
            {"0003", "20250519", "115528", "C003", "1", "140.00", "2", "E03"},
            {"0004", "20250519", "123042", "C004", "2", "800.00", "1", "E04"},
            {"0005", "20250520", "094508", "C007", "1", "20.00", "1", "E01"},
            {"0006", "20250520", "103532", "C002", "2", "350.00", "1", "E02"},
            {"0007", "20250520", "112941", "C001", "1", "105.00", "1", "E03"},
            {"0008", "20250520", "124252", "C008", "2", "780.00", "2", "E04"}
        };

        escribirArchivo("REGISTRO_VENTAS_CABECERA.DAT", raf -> {
            for (String[] d : datos) {
                String monto = String.format("%14s", d[5]);
                String registro = String.format("%-4s%-8s%-6s%-4s%-1s%-14s%-1s%-3s",
                    d[0], d[1], d[2], d[3], d[4], monto, d[6], d[7]);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        });
        System.out.println("REGISTRO_VENTAS_CABECERA.DAT: " + datos.length + " registros × " + LEN_CABECERA + " bytes");
    }

    private static void generarDetalleVentas() throws IOException {
        // Id(4), Secuencia(2), IdProducto(4), Cantidad(5) = 15
        String[][] datos = {
            {"0001", "01", "P750", "2"},
            {"0001", "02", "P691", "2"},
            {"0001", "03", "P630", "4"},
            {"0002", "01", "P324", "1"},
            {"0002", "02", "P125", "5"},
            {"0003", "01", "P125", "2"},
            {"0004", "01", "P405", "10"},
            {"0004", "02", "P125", "4"},
            {"0004", "03", "P957", "4"},
            {"0004", "04", "P750", "5"},
            {"0004", "05", "P542", "2"},
            {"0004", "06", "P510", "4"},
            {"0005", "01", "P750", "1"},
            {"0005", "02", "P691", "1"},
            {"0006", "01", "P405", "4"},
            {"0006", "02", "P125", "3"},
            {"0006", "03", "P510", "2"},
            {"0007", "01", "P905", "5"},
            {"0007", "02", "P887", "2"},
            {"0007", "03", "P691", "3"},
            {"0007", "04", "P678", "1"},
            {"0007", "05", "P630", "5"},
            {"0008", "01", "P248", "2"},
            {"0008", "02", "P125", "4"}
        };

        escribirArchivo("REGISTRO_VENTAS_DETALLE.DAT", raf -> {
            for (String[] d : datos) {
                String cantidad = String.format("%5s", d[3]);
                String registro = String.format("%-4s%-2s%-4s%-5s", d[0], d[1], d[2], cantidad);
                raf.write(registro.getBytes(StandardCharsets.ISO_8859_1));
            }
        });
        System.out.println("REGISTRO_VENTAS_DETALLE.DAT: " + datos.length + " registros × " + LEN_DETALLE + " bytes");
    }

    private static void verificarTamanios() throws IOException {
        java.io.File[] archivos = {
            new java.io.File("CLIENTES.DAT"),
            new java.io.File("EMPLEADO.DAT"),
            new java.io.File("CATEGORIAS.DAT"),
            new java.io.File("PRODUCTOS.DAT"),
            new java.io.File("REGISTRO_VENTAS_CABECERA.DAT"),
            new java.io.File("REGISTRO_VENTAS_DETALLE.DAT")
        };
        int[] esperados = {256, 76, 132, 518, 328, 360}; // 24 registros × 15 = 360

        for (int i = 0; i < archivos.length; i++) {
            long real = archivos[i].length();
            String ok = (real == esperados[i]) ? "✓" : "✗ ESPERADO: " + esperados[i];
            System.out.printf("  %s: %d bytes %s%n", archivos[i].getName(), real, ok);
        }
    }
}