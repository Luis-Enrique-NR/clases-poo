package pe.uni.parcial;

public class Abstract_Monitor extends Abstract_Equipo_Computo {
    private double pulgadas;
    private String resolucion;
    private String tipoPanel;

    public Abstract_Monitor(String marca, String modelo, double precio, String numeroSerie,
                            double pulgadas, String resolucion, String tipoPanel) {
        super(marca, modelo, precio, numeroSerie);
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
        this.tipoPanel = tipoPanel;
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
        mostrarInfoBase();
        System.out.println("--- MONITOR ---");
        System.out.println("Pulgadas: " + pulgadas + "\"");
        System.out.println("Resolución: " + resolucion);
        System.out.println("Tipo Panel: " + tipoPanel);
        System.out.println("=========================");
    }
    
}