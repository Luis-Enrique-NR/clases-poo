package pe.uni.gestion.credito.service;

import java.util.List;
import pe.uni.gestion.credito.entity.Solicitud;
import pe.uni.gestion.credito.repository.SolicitudRepository;

/**
 * Business rules for SOLICITUD_CREDITO.
 * - Precio is copied from Vehiculo at register time.
 * - Monto is always computed: precio - inicial (never typed).
 * - Plazo must be one of 12/24/36/48/60.
 * - Initial estado is 1 (Registrada).
 *
 * MODO ENSENANZA: la implementacion real esta comentada abajo.
 * En vivo, descomentar cada bloque REAL y borrar el stub.
 */
public class SolicitudService {

    private final SolicitudRepository repository;

    public SolicitudService() throws Exception {
        this.repository = new SolicitudRepository();
    }

    SolicitudService(SolicitudRepository repository) {
        this.repository = repository;
    }

    /* REAL registrar: valida FK, copia precio, valida inicial, calcula monto,
       pone fecha automatica, estado 1 y correlativo.
    public Solicitud registrar(Solicitud s) throws Exception {
        pe.uni.gestion.credito.repository.ClienteRepository clientes =
                new pe.uni.gestion.credito.repository.ClienteRepository();
        pe.uni.gestion.credito.entity.Cliente cli = clientes.findById(s.getIdCliente());
        if (cli == null) {
            throw new IllegalArgumentException("Cliente does not exist: " + s.getIdCliente());
        }
        pe.uni.gestion.credito.repository.VehiculoRepository vehiculos =
                new pe.uni.gestion.credito.repository.VehiculoRepository();
        pe.uni.gestion.credito.entity.Vehiculo veh = vehiculos.findById(s.getIdVehiculo());
        if (veh == null) {
            throw new IllegalArgumentException("Vehiculo does not exist: " + s.getIdVehiculo());
        }
        pe.uni.gestion.credito.repository.EmpleadoRepository empleados =
                new pe.uni.gestion.credito.repository.EmpleadoRepository();
        pe.uni.gestion.credito.entity.Empleado asesor = empleados.findById(s.getIdEmpleado());
        if (asesor == null) {
            throw new IllegalArgumentException("Empleado does not exist: " + s.getIdEmpleado());
        }
        if (asesor.getCargo() != 1) {
            throw new IllegalArgumentException("Empleado must be Asesor (cargo 1)");
        }
        pe.uni.gestion.credito.repository.CatalogRepository moneda =
                new pe.uni.gestion.credito.repository.CatalogRepository(
                        pe.uni.gestion.credito.infra.FilePaths.file("moneda.dat"), 15);
        if (moneda.findByCodigo(s.getMoneda()) == null) {
            throw new IllegalArgumentException("Moneda code does not exist: " + s.getMoneda());
        }
        if (s.getPlazo() != 12 && s.getPlazo() != 24 && s.getPlazo() != 36
                && s.getPlazo() != 48 && s.getPlazo() != 60) {
            throw new IllegalArgumentException("Plazo must be 12/24/36/48/60");
        }
        s.setPrecioVehiculo(veh.getPrecio());
        if (s.getCuotaInicial() <= 0 || s.getCuotaInicial() >= s.getPrecioVehiculo()) {
            throw new IllegalArgumentException("CuotaInicial must be > 0 and < precio");
        }
        s.setMontoSolicitado(s.getPrecioVehiculo() - s.getCuotaInicial());
        java.time.LocalDate today = java.time.LocalDate.now();
        s.setFechaSolicitud(today.getYear() * 10000L + today.getMonthValue() * 100L + today.getDayOfMonth());
        s.setEstado(1);
        s.setIdSolicitud(repository.nextId());
        repository.insert(s);
        return s;
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public Solicitud registrar(Solicitud s) throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar registrar");
    }

    /* REAL listar: delega al repositorio.
    public List<Solicitud> listar() throws Exception {
        return repository.findAll();
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public List<Solicitud> listar() throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar listar");
    }

    /* REAL buscarPorId: delega al repositorio.
    public Solicitud buscarPorId(int id) throws Exception {
        return repository.findById(id);
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public Solicitud buscarPorId(int id) throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar buscarPorId");
    }
}
