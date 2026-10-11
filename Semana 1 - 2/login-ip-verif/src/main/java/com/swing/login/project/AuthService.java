package com.swing.login.project;

public class AuthService {
    private UsuarioRepository repository;

    // Inyección de dependencia por constructor
    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean validarCredenciales(String username, String password) {
        // Defensa en profundidad: si la forma es invalida, se rechaza sin tocar el repositorio.
        if (!LoginValidator.esUsuarioValido(username)) {
            return false;
        }
        if (!LoginValidator.esPasswordValida(password)) {
            return false;
        }

        Usuario usuario = repository.buscarPorUsername(username);

        if (usuario == null) {
            return false; // Usuario no existe
        }

        // Reglas de negocio puras:
        boolean passwordCorrecta = usuario.getPassword().equals(password);
        boolean usuarioActivo = usuario.isActivo();

        return passwordCorrecta && usuarioActivo;
    }
}