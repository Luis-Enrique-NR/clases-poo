package pe.uni.parcial;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== PROCESAMIENTO DE ARCHIVOS - ACCESO DIRECTO ===\n");

        try {
            LectorArchivos lector = new LectorArchivos();

            // Leer todos los archivos
            List<Producto> productos = lector.leerProductos();
            List<Cliente> clientes = lector.leerClientes();
            List<Empleado> empleados = lector.leerEmpleados();
            List<VentaCabecera> cabeceras = lector.leerCabeceraVentas();
            List<VentaDetalle> detalles = lector.leerDetalleVentas();

            System.out.println("Archivos leidos:");
            System.out.println("  Productos: " + productos.size());
            System.out.println("  Clientes: " + clientes.size());
            System.out.println("  Empleados: " + empleados.size());
            System.out.println("  Cabeceras de venta: " + cabeceras.size());
            System.out.println("  Detalles de venta: " + detalles.size());
            System.out.println();

            // Reporte 1: Producto con mayor cantidad de ventas
            reporteProductoMasVendido(productos, detalles);

            // Reporte 2: Cliente con mas frecuencia
            reporteClienteMasFrecuente(clientes, cabeceras);

            // Reporte 3: Empleado con mayor cantidad de operaciones
            reporteEmpleadoMasOperaciones(empleados, cabeceras);

        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void reporteProductoMasVendido(List<Producto> productos, List<VentaDetalle> detalles) {
        System.out.println("=== REPORTE 1: PRODUCTO CON MAYOR CANTIDAD DE VENTAS ===");

        // Sumar cantidades por idProducto usando HashMap
        Map<String, Integer> ventasPorProducto = new HashMap<>();

        for (int i = 0; i < detalles.size(); i++) {
            VentaDetalle d = detalles.get(i);
            String idProducto = d.getIdProducto();
            int cantidad = d.getCantidad();

            // Si ya existe, sumar; si no, crear con la cantidad
            if (ventasPorProducto.containsKey(idProducto)) {
                int actual = ventasPorProducto.get(idProducto);
                ventasPorProducto.put(idProducto, actual + cantidad);
            } else {
                ventasPorProducto.put(idProducto, cantidad);
            }
        }

        // Encontrar el producto con mayor cantidad (recorrer las CLAVES del mapa)
        String idProdMax = null;
        int cantidadMax = -1;

        for (String id : ventasPorProducto.keySet()) {
            int total = ventasPorProducto.get(id);
            if (total > cantidadMax) {
                cantidadMax = total;
                idProdMax = id;
            }
        }

        // Buscar nombre del producto por su ID
        String nombreProd = "DESCONOCIDO";
        if (idProdMax != null) {
            for (int i = 0; i < productos.size(); i++) {
                Producto p = productos.get(i);
                if (p.getId().equals(idProdMax)) {
                    nombreProd = p.getNombre();
                    break; // encontrado, salimos del bucle
                }
            }
        }

        System.out.println("Producto: " + nombreProd + " (ID: " + idProdMax + ")");
        System.out.println("Cantidad total vendida: " + cantidadMax);
        System.out.println();
    }

    private static void reporteClienteMasFrecuente(List<Cliente> clientes, List<VentaCabecera> cabeceras) {
        System.out.println("=== REPORTE 2: CLIENTE CON MAS FRECUENCIA ===");

        // Contar ocurrencias por idCliente
        Map<String, Integer> freqPorCliente = new HashMap<>();
        for (int i = 0; i < cabeceras.size(); i++) {
            VentaCabecera c = cabeceras.get(i);
            String idCliente = c.getIdCliente();

            if (freqPorCliente.containsKey(idCliente)) {
                int actual = freqPorCliente.get(idCliente);
                freqPorCliente.put(idCliente, actual + 1);
            } else {
                freqPorCliente.put(idCliente, 1);
            }
        }

        // Encontrar el cliente con mayor frecuencia
        String idCliMax = null;
        int freqMax = -1;

        for (String id : freqPorCliente.keySet()) {
            int freq = freqPorCliente.get(id);
            if (freq > freqMax) {
                freqMax = freq;
                idCliMax = id;
            }
        }

        // Buscar razon social del cliente
        String razonSocial = "DESCONOCIDO";
        if (idCliMax != null) {
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                if (c.getId().equals(idCliMax)) {
                    razonSocial = c.getRazonSocial();
                    break;
                }
            }
        }

        System.out.println("Cliente: " + razonSocial + " (ID: " + idCliMax + ")");
        System.out.println("Frecuencia de compras: " + freqMax);
        System.out.println();
    }

    private static void reporteEmpleadoMasOperaciones(List<Empleado> empleados, List<VentaCabecera> cabeceras) {
        System.out.println("=== REPORTE 3: EMPLEADO CON MAYOR CANTIDAD DE OPERACIONES ===");

        // Contar ocurrencias por idEmpleado
        Map<String, Integer> opsPorEmpleado = new HashMap<>();
        for (int i = 0; i < cabeceras.size(); i++) {
            VentaCabecera c = cabeceras.get(i);
            String idEmpleado = c.getIdEmpleado();

            if (opsPorEmpleado.containsKey(idEmpleado)) {
                int actual = opsPorEmpleado.get(idEmpleado);
                opsPorEmpleado.put(idEmpleado, actual + 1);
            } else {
                opsPorEmpleado.put(idEmpleado, 1);
            }
        }

        // Encontrar el empleado con mayor cantidad de operaciones
        String idEmpMax = null;
        int opsMax = -1;

        for (String id : opsPorEmpleado.keySet()) {
            int ops = opsPorEmpleado.get(id);
            if (ops > opsMax) {
                opsMax = ops;
                idEmpMax = id;
            }
        }

        // Buscar nombre del empleado
        String nomApe = "DESCONOCIDO";
        if (idEmpMax != null) {
            for (int i = 0; i < empleados.size(); i++) {
                Empleado e = empleados.get(i);
                if (e.getId().equals(idEmpMax)) {
                    nomApe = e.getNomApe();
                    break;
                }
            }
        }

        System.out.println("Empleado: " + nomApe + " (ID: " + idEmpMax + ")");
        System.out.println("Operaciones de venta: " + opsMax);
        System.out.println();
    }
}