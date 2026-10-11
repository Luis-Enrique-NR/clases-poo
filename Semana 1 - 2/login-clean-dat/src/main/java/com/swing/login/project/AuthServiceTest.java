package com.swing.login.project;


import com.swing.login.project.UsuarioRepository;

public class AuthServiceTest {
    
    private UsuarioRepository repository;
    
    Usuario usuario = new Usuario("USERNAME", "PSW", true);
    
    
    public void metodo() {
        String psw = usuario.getPassword();
        
        // ...
        
        System.out.println(psw);
    }
    
    
   
}
