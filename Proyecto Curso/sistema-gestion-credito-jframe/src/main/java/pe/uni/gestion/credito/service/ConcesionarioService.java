package pe.uni.gestion.credito.service;

import java.util.List;
import pe.uni.gestion.credito.entity.Concesionario;
import pe.uni.gestion.credito.repository.ConcesionarioRepository;

/**
 * Business rules for CONCESIONARIO.
 */
public class ConcesionarioService {

    private final ConcesionarioRepository repository;

    public ConcesionarioService() throws Exception {
        this.repository = new ConcesionarioRepository();
    }

    ConcesionarioService(ConcesionarioRepository repository) {
        this.repository = repository;
    }

    public Concesionario registrar(Concesionario c) throws Exception {
        if (c.getRuc() == null || c.getRuc().trim().length() != 11) {
            throw new IllegalArgumentException("RUC must be 11 digits");
        }
        if (c.getRazonSocial() == null || c.getRazonSocial().trim().isEmpty()) {
            throw new IllegalArgumentException("RazonSocial is required");
        }
        for (Concesionario other : repository.findAll()) {
            if (other.getRuc().equalsIgnoreCase(c.getRuc().trim())) {
                throw new IllegalArgumentException("RUC already registered: " + c.getRuc());
            }
        }
        c.setIdConcesionario(repository.nextId());
        repository.insert(c);
        return c;
    }

    public List<Concesionario> listar() throws Exception {
        return repository.findAll();
    }
}
