package pe.uni.gestion.credito.service;

import java.util.List;
import pe.uni.gestion.credito.entity.Empleado;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.repository.CatalogRepository;
import pe.uni.gestion.credito.repository.EmpleadoRepository;

/**
 * Business rules for EMPLEADO.
 */
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService() throws Exception {
        this.repository = new EmpleadoRepository();
    }

    EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado registrar(Empleado e) throws Exception {
        if (e.getDni() == null || e.getDni().trim().length() != 8) {
            throw new IllegalArgumentException("DNI must be 8 digits");
        }
        if (e.getNombres() == null || e.getNombres().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombres is required");
        }
        if (e.getApellidos() == null || e.getApellidos().trim().isEmpty()) {
            throw new IllegalArgumentException("Apellidos is required");
        }
        CatalogRepository sexo = new CatalogRepository(FilePaths.file("sexo.dat"), 15);
        if (sexo.findByCodigo(e.getSexo()) == null) {
            throw new IllegalArgumentException("Sexo code does not exist: " + e.getSexo());
        }
        CatalogRepository cargo = new CatalogRepository(FilePaths.file("cargo.dat"), 20);
        if (cargo.findByCodigo(e.getCargo()) == null) {
            throw new IllegalArgumentException("Cargo code does not exist: " + e.getCargo());
        }
        for (Empleado other : repository.findAll()) {
            if (other.getDni().equalsIgnoreCase(e.getDni().trim())) {
                throw new IllegalArgumentException("DNI already registered: " + e.getDni());
            }
        }
        e.setIdEmpleado(repository.nextId());
        repository.insert(e);
        return e;
    }

    public List<Empleado> listar() throws Exception {
        return repository.findAll();
    }
}
