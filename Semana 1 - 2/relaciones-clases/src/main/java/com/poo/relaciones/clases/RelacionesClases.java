package com.poo.relaciones.clases;

import java.util.ArrayList;
import java.util.List;

public class RelacionesClases {

    public static void main(String[] args) {
        
        Estudiante est = new Estudiante("13221838", "Luis", "Perez", "20213432842", "21-2");
        
        est.getFullName();
        
        
        Persona obj01 = new Estudiante("13221838", "Kevin", "Perez", "20213432842", "22-2");
        
        Persona obj02 = new Profesor("21932809", "Alberto", "Montes", true);
        
        List<Persona> personas = new ArrayList<>();
        
        personas.addFirst(obj01); personas.add(obj02);
        
        for (Persona pers : personas) {
            pers.getFullName();
        }
        
        
    }
      
}
