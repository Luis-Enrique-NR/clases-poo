package com.swing.todo.project.repository;

import com.swing.todo.project.entity.Usuario;

public interface UsuarioRepository {
    Usuario buscarPorUsername(String username);
}
