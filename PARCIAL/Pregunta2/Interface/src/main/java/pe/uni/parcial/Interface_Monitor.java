package pe.uni.parcial;

public class Interface_Monitor implements Interface_Equipo_Computo {
    private String marca;
    private String modelo;
    private double precio;
    private String numeroSerie;
    private double pulgadas;
    private String resolucion;
    private String tipoPanel;

    public Interface_Monitor(String marca, String modelo, double precio, String numeroSerie,
                             double pulgadas, String resolucion, String tipoPanel) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.numeroSerie = numeroSerie;
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.tipoPanel = tipoPanel;
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

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTipoPanel() {
        return tipoPanel;
    }

    public void setTipoPanel(String tipoPanel) {
        this.tipoPanel = tipoPanel;
    }

    @Override
    public void mostrarInfo() {
        mostrarInfoBase(); // default method de la interfaz
        System.out.println("--- MONITOR ---");
        System.out.println("Pulgadas: " + pulgadas + "\"");
        System.out.println("Resolución: " + resolucion);
        System.out.println("Tipo Panel: " + tipoPanel);
        System.out.println("=========================");
    }
}