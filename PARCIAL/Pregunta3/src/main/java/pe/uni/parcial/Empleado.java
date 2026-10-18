package pe.uni.parcial;

public class Empleado {
    private String id;
    private String nomApe;

    public Empleado(String id, String nomApe) {
        this.id = id;
        this.nomApe = nomApe;
    }

    public String getId() { return id; }
    public String getNomApe() { return nomApe; }

    @Override
    public String toString() {
        return "Empleado{id='" + id + "', nomApe='" + nomApe + "'}";
    }
}