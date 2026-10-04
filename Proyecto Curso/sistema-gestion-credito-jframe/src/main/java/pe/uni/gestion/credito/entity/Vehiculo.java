package pe.uni.gestion.credito.entity;

public class Vehiculo {

    private int idVehiculo;
    private int tipo;
    private String idMarcaModelo;
    private int color;
    private int anio;
    private String condicion;
    private String nroChasis;
    private String nroMotor;
    private double precio;
    private int idConcesionario;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getIdMarcaModelo() {
        return idMarcaModelo;
    }

    public void setIdMarcaModelo(String idMarcaModelo) {
        this.idMarcaModelo = idMarcaModelo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public String getNroMotor() {
        return nroMotor;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(int idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    @Override
    public String toString() {
        return idVehiculo + " - " + idMarcaModelo + " " + anio + " (" + nroChasis + ") S/" + precio;
    }
}
