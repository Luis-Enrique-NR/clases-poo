package pe.uni.parcial;

public class Test_Inherit {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA HERENCIA (extends) ===\n");

        // Crear Monitor
        Inherit_Monitor monitor = new Inherit_Monitor(
            "Samsung", "Odyssey G5", 1200.00, "SN123456",
            27.0, "2560x1440", "VA"
        );

        // Crear Impresora
        Inherit_Impresora impresora = new Inherit_Impresora(
            "HP", "LaserJet Pro", 850.00, "SN789012",
            "laser", 22, true
        );

        // Polimorfismo: array de tipo base
        Inherit_Equipo_Computo[] equipos = { monitor, impresora };

        System.out.println("--- Recorrido polimórfico ---\n");
        for (Inherit_Equipo_Computo eq : equipos) {
            eq.mostrarInfo();
            System.out.println();
        }

        System.out.println("=== FIN PRUEBA HERENCIA ===");
    }
}