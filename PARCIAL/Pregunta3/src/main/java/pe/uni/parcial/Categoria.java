package pe.uni.parcial;

public class Categoria {
    private String id;
    private String descripcion;

    public Categoria(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return "Categoria{id='" + id + "', descripcion='" + descripcion + "'}";
    }
}