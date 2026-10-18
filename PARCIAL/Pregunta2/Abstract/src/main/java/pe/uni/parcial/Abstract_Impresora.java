package pe.uni.parcial;

public class Abstract_Impresora extends Abstract_Equipo_Computo {
    private String tipo; // laser, inkjet
    private int ppm; // páginas por minuto
    private boolean duplex;

    public Abstract_Impresora(String marca, String modelo, double precio, String numeroSerie,
                              String tipo, int ppm, boolean duplex) {
        super(marca, modelo, precio, numeroSerie);
        this.tipo = tipo;
        this.ppm = ppm;
        this.duplex = duplex;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPpm() {
        return ppm;
    }

    public void setPpm(int ppm) {
        this.ppm = ppm;
    }

    public boolean isDuplex() {
        return duplex;
    }

    public void setDuplex(boolean duplex) {
        this.duplex = duplex;
    }

    @Override
    public void mostrarInfo() {
        mostrarInfoBase();
        System.out.println("--- IMPRESORA ---");
        System.out.println("Tipo: " + tipo);
        System.out.println("PPM: " + ppm);
        System.out.println("Duplex: " + (duplex ? "Sí" : "No"));
        System.out.println("=========================");
    }
    
    @Override
    protected void mostrarInfoBase() {
        System.out.println("=== EQUIPO DE CÓMPUTO ===");
        System.out.println("HOLA MUNDO");
    }
}