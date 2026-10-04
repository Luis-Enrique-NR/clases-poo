/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.uni.gestion.credito;

import pe.uni.gestion.credito.infra.FilePaths;

/**
 *
 * @author LUIS
 */
public class SistemaGestionCreditoJframe {

    public static void main(String[] args) {
        
        
        FilePaths testFile = new FilePaths();
        
        try {
        System.out.println(testFile.cliente());
        }
        catch (Exception e) {
            System.out.println("Error");   
        }
        
        
    }
}
