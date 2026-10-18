package pe.uni.parcial;

public class VentaCabecera {
    private String id;
    private String fecha;
    private String hora;
    private String idCliente;
    private String tipoPago;
    private double monto;
    private String idMoneda;
    private String idEmpleado;

    public VentaCabecera(String id, String fecha, String hora, String idCliente,
                         String tipoPago, double monto, String idMoneda, String idEmpleado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idCliente = idCliente;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.idMoneda = idMoneda;
        this.idEmpleado = idEmpleado;
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getIdCliente() { return idCliente; }
    public String getTipoPago() { return tipoPago; }
    public double getMonto() { return monto; }
    public String getIdMoneda() { return idMoneda; }
    public String getIdEmpleado() { return idEmpleado; }

    @Override
    public String toString() {
        return "VentaCabecera{id='" + id + "', fecha='" + fecha + "', idCliente='" + idCliente + "', monto=" + monto + ", idEmpleado='" + idEmpleado + "'}";
    }
}