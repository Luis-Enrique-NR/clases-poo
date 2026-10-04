package pe.uni.gestion.credito.service;

import java.time.LocalDate;
import java.util.List;
import pe.uni.gestion.credito.entity.Vehiculo;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.repository.CatalogRepository;
import pe.uni.gestion.credito.repository.ConcesionarioRepository;
import pe.uni.gestion.credito.repository.MarcaModeloRepository;
import pe.uni.gestion.credito.repository.VehiculoRepository;

/**
 * Business rules for VEHICULO.
 */
public class VehiculoService {

    private final VehiculoRepository repository;

    public VehiculoService() throws Exception {
        this.repository = new VehiculoRepository();
    }

    VehiculoService(VehiculoRepository repository) {
        this.repository = repository;
    }

    public Vehiculo registrar(Vehiculo v) throws Exception {
        CatalogRepository tipo = new CatalogRepository(FilePaths.file("tipovehiculo.dat"), 20);
        if (tipo.findByCodigo(v.getTipo()) == null) {
            throw new IllegalArgumentException("Tipo code does not exist: " + v.getTipo());
        }
        Marcas.check(v.getIdMarcaModelo());
        CatalogRepository color = new CatalogRepository(FilePaths.file("color.dat"), 20);
        if (color.findByCodigo(v.getColor()) == null) {
            throw new IllegalArgumentException("Color code does not exist: " + v.getColor());
        }
        int year = LocalDate.now().getYear() + 1;
        if (v.getAnio() < 2000 || v.getAnio() > year) {
            throw new IllegalArgumentException("Anio must be between 2000 and " + year);
        }
        if (!"Nuevo".equalsIgnoreCase(v.getCondicion()) && !"Usado".equalsIgnoreCase(v.getCondicion())) {
            throw new IllegalArgumentException("Condicion must be Nuevo or Usado");
        }
        if (v.getPrecio() <= 0) {
            throw new IllegalArgumentException("Precio must be greater than zero");
        }
        ConcesionarioRepository conc = new ConcesionarioRepository();
        if (conc.findById(v.getIdConcesionario()) == null) {
            throw new IllegalArgumentException("Concesionario does not exist: " + v.getIdConcesionario());
        }
        for (Vehiculo other : repository.findAll()) {
            if (other.getNroChasis().equalsIgnoreCase(v.getNroChasis().trim())) {
                throw new IllegalArgumentException("Chasis already registered: " + v.getNroChasis());
            }
        }
        v.setIdVehiculo(repository.nextId());
        repository.insert(v);
        return v;
    }

    public List<Vehiculo> listar() throws Exception {
        return repository.findAll();
    }

    private static final class Marcas {
        static void check(String codigo) throws Exception {
            MarcaModeloRepository repo = new MarcaModeloRepository(FilePaths.file("marcamodelo.dat"));
            if (codigo == null || repo.findByCodigo(codigo.trim()) == null) {
                throw new IllegalArgumentException("MarcaModelo code does not exist: " + codigo);
            }
        }
    }
}
