package pe.uni.parcial;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
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

    private static final byte[] BUFFER = new byte[Math.max(Math.max(Math.max(Math.max(Math.max(
        LEN_CLIENTES, LEN_EMPLEADO), LEN_CATEGORIAS), LEN_PRODUCTOS), LEN_CABECERA), LEN_DETALLE)];

    public List<Cliente> leerClientes() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile("CLIENTES.DAT", "r")) {
            long total = raf.length();
            int n = (int) (total / LEN_CLIENTES);
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CLIENTES);
                raf.readFully(BUFFER, 0, LEN_CLIENTES);
                String linea = new String(BUFFER, 0, LEN_CLIENTES, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 4).trim();
                String razonSocial = linea.substring(4, 21).trim();
                String ruc = linea.substring(21, 32).trim();
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
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_EMPLEADO);
                raf.readFully(BUFFER, 0, LEN_EMPLEADO);
                String linea = new String(BUFFER, 0, LEN_EMPLEADO, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 3).trim();
                String nomApe = linea.substring(3, 19).trim();
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
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CATEGORIAS);
                raf.readFully(BUFFER, 0, LEN_CATEGORIAS);
                String linea = new String(BUFFER, 0, LEN_CATEGORIAS, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 2).trim();
                String descripcion = linea.substring(2, 33).trim();
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
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_PRODUCTOS);
                raf.readFully(BUFFER, 0, LEN_PRODUCTOS);
                String linea = new String(BUFFER, 0, LEN_PRODUCTOS, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 4).trim();
                String nombre = linea.substring(4, 24).trim();
                String idCategoria = linea.substring(24, 25).trim();
                String precioStr = linea.substring(25, 36).trim();
                String idMoneda = linea.substring(36, 37).trim();
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
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_CABECERA);
                raf.readFully(BUFFER, 0, LEN_CABECERA);
                String linea = new String(BUFFER, 0, LEN_CABECERA, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 4).trim();
                String fecha = linea.substring(4, 12).trim();
                String hora = linea.substring(12, 18).trim();
                String idCliente = linea.substring(18, 22).trim();
                String tipoPago = linea.substring(22, 23).trim();
                String montoStr = linea.substring(23, 37).trim();
                String idMoneda = linea.substring(37, 38).trim();
                String idEmpleado = linea.substring(38, 41).trim();
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
            for (int i = 0; i < n; i++) {
                raf.seek(i * LEN_DETALLE);
                raf.readFully(BUFFER, 0, LEN_DETALLE);
                String linea = new String(BUFFER, 0, LEN_DETALLE, StandardCharsets.ISO_8859_1);
                String id = linea.substring(0, 4).trim();
                String secuencia = linea.substring(4, 6).trim();
                String idProducto = linea.substring(6, 10).trim();
                String cantidadStr = linea.substring(10, 15).trim();
                int cantidad = Integer.parseInt(cantidadStr);
                lista.add(new VentaDetalle(id, secuencia, idProducto, cantidad));
            }
        }
        return lista;
    }
}