package pe.uni.parcial;

public class VentaDetalle {
    private String id;
    private String secuencia;
    private String idProducto;
    private int cantidad;

    public VentaDetalle(String id, String secuencia, String idProducto, int cantidad) {
        this.id = id;
        this.secuencia = secuencia;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public String getId() { return id; }
    public String getSecuencia() { return secuencia; }
    public String getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "VentaDetalle{id='" + id + "', secuencia='" + secuencia + "', idProducto='" + idProducto + "', cantidad=" + cantidad + "}";
    }
}