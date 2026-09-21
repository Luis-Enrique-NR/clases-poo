package com.swing.todo.project.entity;

public class Usuario {
    private String id;
    private String username;
    private String password;
    private boolean activo;

    public Usuario(String id, String username, String password, boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.activo = activo;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActivo() { return activo; }
}