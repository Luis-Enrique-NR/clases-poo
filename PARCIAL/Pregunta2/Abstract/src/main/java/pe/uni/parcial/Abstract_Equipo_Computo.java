package pe.uni.parcial;

public abstract class Abstract_Equipo_Computo {
    protected String marca;
    protected String modelo;
    protected double precio;
    protected String numeroSerie;

    public Abstract_Equipo_Computo(String marca, String modelo, double precio, String numeroSerie) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    // Método abstracto: obliga a las subclases a implementarlo
    public abstract void mostrarInfo();
    
    // Método concreto compartido (opcional, para mostrar datos base)
    protected void mostrarInfoBase() {
        System.out.println("=== EQUIPO DE CÓMPUTO ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Precio: S/ " + precio);
        System.out.println("N° Serie: " + numeroSerie);
    }
}