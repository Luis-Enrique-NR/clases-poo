package pe.uni.gestion.credito.service;

import java.util.List;
import pe.uni.gestion.credito.entity.Cliente;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.repository.CatalogRepository;
import pe.uni.gestion.credito.repository.ClienteRepository;

/**
 * Business rules for CLIENTE.
 */
public class ClienteService {

    private final ClienteRepository repository;
    
    
    public ClienteService() throws Exception {
        this.repository = new ClienteRepository();
    }

    ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    
    
    
    public void registrar(Cliente c) throws Exception {
        validateRequired(c);
        
        validateFk(c);
        
        if (repository.findByDocumento(c.getNroDocumento()) != null) {
            throw new IllegalArgumentException("Documento already registered: " + c.getNroDocumento());
        }
        c.setIdCliente(repository.nextId());
        repository.insert(c);
        //return c;
    }
    
    public List<Cliente> listar() throws Exception {
        return repository.findAll();
    }

    public Cliente buscarPorId(int id) throws Exception {
        return repository.findById(id);
    }

    private static void validateRequired(Cliente c) {
        require(c.getNroDocumento(), "NroDocumento");
        require(c.getNombres(), "Nombres");
        require(c.getApellidos(), "Apellidos");
        
        if (c.getIngresoMensual() < 0) {
            throw new IllegalArgumentException("EL INGRESE DEBE SER MAYOR A 0");
        }
        
        if (c.getNroDocumento().trim().length() > 15) {
            throw new IllegalArgumentException("NroDocumento max 15");
        }
        
        if (c.getTipoDoc() == 1 && c.getNroDocumento().trim().length() != 8) {
            throw new IllegalArgumentException("DNI must be 8 digits");
        }
        
        if (c.getCorreo() != null && !c.getCorreo().trim().isEmpty() && !c.getCorreo().contains("@")) {
            throw new IllegalArgumentException("Correo must contain @");
        }
    }
    
    private static void validateFk(Cliente c) throws Exception {
        exists("nacionalidad.dat", 20, c.getNacionalidad(), "Nacionalidad");
        exists("tipodoc.dat", 25, c.getTipoDoc(), "TipoDoc");
        exists("sexo.dat", 15, c.getSexo(), "Sexo");
        exists("estadocivil.dat", 15, c.getEstadoCivil(), "EstadoCivil");
        exists("sitlaboral.dat", 15, c.getSituacionLaboral(), "SituacionLaboral");
    }

    private static void exists(String file, int nameLen, int codigo, String field) throws Exception {
        CatalogRepository repo = new CatalogRepository(FilePaths.file(file), nameLen);
        if (repo.findByCodigo(codigo) == null) {
            throw new IllegalArgumentException(field + " code does not exist: " + codigo);
        }
    }

    private static void require(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " is required");
        }
    }
}
