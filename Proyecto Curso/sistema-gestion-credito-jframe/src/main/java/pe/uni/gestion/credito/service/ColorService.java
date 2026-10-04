package pe.uni.gestion.credito.service;

import java.io.IOException;
import java.util.List;
import pe.uni.gestion.credito.entity.Color;
import pe.uni.gestion.credito.repository.ColorRepository;

/**
 * Business rules for COLOR catalog.
 */
public class ColorService {

    private final ColorRepository repository;

    public ColorService() throws IOException {
        this.repository = new ColorRepository();
    }

    ColorService(ColorRepository repository) {
        this.repository = repository;
    }

    public Color insertar(int codigo, String nombre) throws IOException {
        validate(codigo, nombre);
        if (repository.findByCodigo(codigo) != null) {
            throw new IllegalArgumentException("Codigo already exists: " + codigo);
        }
        Color color = new Color(codigo, nombre.trim());
        repository.insert(color);
        return color;
    }

    public List<Color> listar() throws IOException {
        return repository.findAll();
    }

    public Color buscar(int codigo) throws IOException {
        return repository.findByCodigo(codigo);
    }

    private static void validate(int codigo, String nombre) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Codigo must be greater than zero");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre is required");
        }
        if (nombre.trim().length() > 20) {
            throw new IllegalArgumentException("Nombre max length is 20");
        }
    }
}
