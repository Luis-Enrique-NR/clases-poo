package pe.uni.parcial;

public interface Interface_Equipo_Computo {
    // Constantes (implícitamente public static final)
    String TIPO_BASE = "Equipo de Cómputo";

    // Métodos abstractos (implícitamente public abstract)
    void mostrarInfo();

    String getMarca();
    void setMarca(String marca);

    String getModelo();
    void setModelo(String modelo);

    double getPrecio();
    void setPrecio(double precio);

    String getNumeroSerie();
    void setNumeroSerie(String numeroSerie);

    // Default method (Java 8+) - implementación compartida opcional
    default void mostrarInfoBase() {
        System.out.println("=== " + TIPO_BASE + " ===");
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Precio: S/ " + getPrecio());
        System.out.println("N° Serie: " + getNumeroSerie());
    }
    
    default int getSalario() {
        return 1130;
    }
}