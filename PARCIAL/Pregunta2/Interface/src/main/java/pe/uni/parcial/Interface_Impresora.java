package pe.uni.parcial;

public class Interface_Impresora implements Interface_Equipo_Computo {
    private String marca;
    private String modelo;
    private double precio;
    private String numeroSerie;
    private String tipo; // laser, inkjet
    private int ppm; // páginas por minuto
    private boolean duplex;

    public Interface_Impresora(String marca, String modelo, double precio, String numeroSerie,
                               String tipo, int ppm, boolean duplex) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.numeroSerie = numeroSerie;
        this.tipo = tipo;
        this.ppm = ppm;
        this.duplex = duplex;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String getNumeroSerie() {
        return numeroSerie;
    }

    @Override
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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
        mostrarInfoBase(); // default method de la interfaz
        System.out.println("--- IMPRESORA ---");
        System.out.println("Tipo: " + tipo);
        System.out.println("PPM: " + ppm);
        System.out.println("Duplex: " + (duplex ? "Sí" : "No"));
        System.out.println("=========================");
    }
}