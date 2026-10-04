package pe.uni.gestion.credito.spike;

import pe.uni.gestion.credito.service.ColorService;

/**
 * Internal verification only. No business logic here.
 * It only calls the service and prints to console.
 */
public class ColorSpike {

    public static void main(String[] args) throws Exception {
        
        
        ColorService service = new ColorService();
        try {
            service.insertar(1, "Rojo");
        } catch (IllegalArgumentException dup) {
            System.out.println("Already seeded: " + dup.getMessage());
        }
        try {
            service.insertar(2, "Negro");
        } catch (IllegalArgumentException dup) {
            System.out.println("Already seeded: " + dup.getMessage());
        }
        System.out.println(service.listar());
    }
}
