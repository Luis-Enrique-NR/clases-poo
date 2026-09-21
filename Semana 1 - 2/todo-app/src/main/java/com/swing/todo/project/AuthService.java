package com.swing.todo.project;

import com.swing.todo.project.entity.Usuario;
import com.swing.todo.project.repository.UsuarioRepository;

public class AuthService {
    private UsuarioRepository repository;

    // Inyección de dependencia por constructor
    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = repository.buscarPorUsername(username);

        if (usuario == null) {
            return false; // Usuario no existe
        }

        // Reglas de negocio puras:
        boolean passwordCorrecta = usuario.getPassword().equals(password);
        boolean usuarioActivo = usuario.isActivo();

        return passwordCorrecta && usuarioActivo;
    }

    // --- MÉTODODELEGADO PARA RECUPERAR EL OBJETO USUARIO COMPLETO ---
    public Usuario obtenerUsuario(String username) {
        return repository.buscarPorUsername(username);
    }
}