package pe.uni.gestion.credito.entity;

public class Solicitud {

    private int idSolicitud;
    private int idCliente;
    private int idVehiculo;
    private int idEmpleado;
    private long fechaSolicitud;
    private int moneda;
    private double precioVehiculo;
    private double cuotaInicial;
    private double montoSolicitado;
    private int plazo;
    private int estado;

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(long fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getMoneda() {
        return moneda;
    }

    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }

    public double getPrecioVehiculo() {
        return precioVehiculo;
    }

    public void setPrecioVehiculo(double precioVehiculo) {
        this.precioVehiculo = precioVehiculo;
    }

    public double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return idSolicitud + " - cli:" + idCliente + " veh:" + idVehiculo
                + " monto:" + montoSolicitado + " plazo:" + plazo + " est:" + estado;
    }
}
