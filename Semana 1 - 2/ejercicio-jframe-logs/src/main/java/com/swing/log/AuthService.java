package com.swing.log;

public class AuthService {
    private UsuarioRepository repository; // ---> ABSTRACTO
    
    // Inyección de dependencia por constructor
    public AuthService(UsuarioRepository repository) {    // ---> ES LO ESPECIFICO
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
}
