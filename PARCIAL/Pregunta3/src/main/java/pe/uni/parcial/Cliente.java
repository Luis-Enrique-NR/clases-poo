package pe.uni.parcial;

public class Cliente {
    private String id;
    private String razonSocial;
    private String ruc;

    public Cliente(String id, String razonSocial, String ruc) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
    }

    public String getId() { return id; }
    public String getRazonSocial() { return razonSocial; }
    public String getRuc() { return ruc; }

    @Override
    public String toString() {
        return "Cliente{id='" + id + "', razonSocial='" + razonSocial + "', ruc='" + ruc + "'}";
    }
}