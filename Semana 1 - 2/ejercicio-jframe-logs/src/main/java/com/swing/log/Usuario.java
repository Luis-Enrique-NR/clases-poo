package com.swing.log;

public class Usuario {
    private String username;
    private String password;
    private boolean activo;

    public Usuario(String username, String password, boolean activo) {
        this.username = username;
        this.password = password;
        this.activo = activo;
    }
    
    public Usuario() {
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isActivo() { return activo; }
}