package pe.uni.parcial;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivos {

    // Longitudes de registro fijas
    private static final int LEN_CLIENTES = 32;
    private static final int LEN_EMPLEADO = 19;
    private static final int LEN_CATEGORIAS = 33;
    private static final int LEN_PRODUCTOS = 37;
    private static final int LEN_CABECERA = 41;
    private static final int LEN_DETALLE = 15;

    public List<Cliente> leerClientes() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("CLIENTES.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_CLIENTES);
            // Buffer exacto para un registro
            byte[] bytes = new byte[LEN_CLIENTES];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CLIENTES);
                
                raf.read(bytes);  // Leer registro completo (32 bytes)
                
                String linea = new String(bytes, 0, LEN_CLIENTES);
                
                // substring(inicio, fin) - fin es EXCLUSIVO
                String id = linea.substring(0, 4).trim();      // 0-3   (4 chars)
                String razonSocial = linea.substring(4, 21).trim(); // 4-20  (17 chars)
                String ruc = linea.substring(21, 32).trim();   // 21-31 (11 chars)
                lista.add(new Cliente(id, razonSocial, ruc));
            }
        }
        return lista;
    }

    public List<Empleado> leerEmpleados() throws IOException {
        List<Empleado> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("EMPLEADO.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_EMPLEADO);
            byte[] bytes = new byte[LEN_EMPLEADO];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_EMPLEADO);
                raf.read(bytes);  // Leer registro completo (19 bytes)
                String linea = new String(bytes, 0, LEN_EMPLEADO);
                String id = linea.substring(0, 3).trim();      // 0-2   (3 chars)
                String nomApe = linea.substring(3, 19).trim(); // 3-18  (16 chars)
                lista.add(new Empleado(id, nomApe));
            }
        }
        return lista;
    }

    public List<Categoria> leerCategorias() throws IOException {
        List<Categoria> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("CATEGORIAS.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_CATEGORIAS);
            byte[] bytes = new byte[LEN_CATEGORIAS];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CATEGORIAS);
                raf.read(bytes);  // Leer registro completo (33 bytes)
                String linea = new String(bytes, 0, LEN_CATEGORIAS);
                String id = linea.substring(0, 2).trim();       // 0-1   (2 chars)
                String descripcion = linea.substring(2, 33).trim(); // 2-32  (31 chars)
                lista.add(new Categoria(id, descripcion));
            }
        }
        return lista;
    }

    public List<Producto> leerProductos() throws IOException {
        List<Producto> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("PRODUCTOS.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_PRODUCTOS);
            byte[] bytes = new byte[LEN_PRODUCTOS];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_PRODUCTOS);
                raf.read(bytes);  // Leer registro completo (37 bytes)
                String linea = new String(bytes, 0, LEN_PRODUCTOS);
                String id = linea.substring(0, 4).trim();        // 0-3   (4 chars)
                String nombre = linea.substring(4, 24).trim();   // 4-23  (20 chars)
                String idCategoria = linea.substring(24, 25).trim(); // 24    (1 char)
                String precioStr = linea.substring(25, 36).trim();   // 25-35 (11 chars)
                String idMoneda = linea.substring(36, 37).trim();    // 36    (1 char)
                double precio = Double.parseDouble(precioStr);
                lista.add(new Producto(id, nombre, idCategoria, precio, idMoneda));
            }
        }
        return lista;
    }

    public List<VentaCabecera> leerCabeceraVentas() throws IOException {
        List<VentaCabecera> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("REGISTRO_VENTAS_CABECERA.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_CABECERA);
            byte[] bytes = new byte[LEN_CABECERA];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CABECERA);
                raf.read(bytes);  // Leer registro completo (41 bytes)
                String linea = new String(bytes, 0, LEN_CABECERA);
                String id = linea.substring(0, 4).trim();        // 0-3   (4 chars)
                String fecha = linea.substring(4, 12).trim();    // 4-11  (8 chars)
                String hora = linea.substring(12, 18).trim();    // 12-17 (6 chars)
                String idCliente = linea.substring(18, 22).trim(); // 18-21 (4 chars)
                String tipoPago = linea.substring(22, 23).trim();  // 22    (1 char)
                String montoStr = linea.substring(23, 37).trim();  // 23-36 (14 chars)
                String idMoneda = linea.substring(37, 38).trim();  // 37    (1 char)
                String idEmpleado = linea.substring(38, 41).trim(); // 38-40 (3 chars)
                double monto = Double.parseDouble(montoStr);
                lista.add(new VentaCabecera(id, fecha, hora, idCliente, tipoPago, monto, idMoneda, idEmpleado));
            }
        }
        return lista;
    }

    public List<VentaDetalle> leerDetalleVentas() throws IOException {
        List<VentaDetalle> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("REGISTRO_VENTAS_DETALLE.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_DETALLE);
            byte[] bytes = new byte[LEN_DETALLE];

            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_DETALLE);
                raf.read(bytes);  // Leer registro completo (15 bytes)
                String linea = new String(bytes, 0, LEN_DETALLE);
                String id = linea.substring(0, 4).trim();       // 0-3   (4 chars)
                String secuencia = linea.substring(4, 6).trim(); // 4-5   (2 chars)
                String idProducto = linea.substring(6, 10).trim(); // 6-9   (4 chars)
                String cantidadStr = linea.substring(10, 15).trim(); // 10-14 (5 chars)
                int cantidad = Integer.parseInt(cantidadStr);
                lista.add(new VentaDetalle(id, secuencia, idProducto, cantidad));
            }
        }
        return lista;
    }
}