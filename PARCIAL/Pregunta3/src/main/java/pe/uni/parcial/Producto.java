package pe.uni.parcial;

public class Producto {
    private String id;
    private String nombre;
    private String idCategoria;
    private double precio;
    private String idMoneda;

    public Producto(String id, String nombre, String idCategoria, double precio, String idMoneda) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
        this.precio = precio;
        this.idMoneda = idMoneda;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getIdCategoria() { return idCategoria; }
    public double getPrecio() { return precio; }
    public String getIdMoneda() { return idMoneda; }

    @Override
    public String toString() {
        return "Producto{id='" + id + "', nombre='" + nombre + "', idCategoria=" + idCategoria + ", precio=" + precio + ", idMoneda=" + idMoneda + "}";
    }
}