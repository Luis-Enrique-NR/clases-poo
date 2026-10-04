/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.uni.gestion.credito.service;

import pe.uni.gestion.credito.infra.FilePaths;

/**
 *
 * @author LUIS
 */
public class CargoService {
    
    
    FilePaths testFile = new FilePaths();
        
    
    public void metodoCualquier() {
        try {
        System.out.println(testFile.file("cargo.txt"));
        }
        catch (Exception e) {
            System.out.println("Error");   
        }
    }
    
}