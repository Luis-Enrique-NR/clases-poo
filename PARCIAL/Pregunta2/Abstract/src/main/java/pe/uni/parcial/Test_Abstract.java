package pe.uni.parcial;

public class Test_Abstract {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA CLASE ABSTRACTA ===\n");

        // Crear Monitor
        Abstract_Monitor monitor = new Abstract_Monitor(
            "Samsung", "Odyssey G5", 1200.00, "SN123456",
            27.0, "2560x1440", "VA"
        );

        // Crear Impresora
        Abstract_Impresora impresora = new Abstract_Impresora(
            "HP", "LaserJet Pro", 850.00, "SN789012",
            "laser", 22, true
        );

        // Polimorfismo: array de tipo base abstracto
        Abstract_Equipo_Computo[] equipos = { monitor, impresora };

        System.out.println("--- Recorrido polimórfico ---\n");
        for (Abstract_Equipo_Computo eq : equipos) {
            eq.mostrarInfo();
            System.out.println();
        }

        // Demostración: no se puede instanciar la clase abstracta
        // Abstract_Equipo_Computo eq = new Abstract_Equipo_Computo(...); // Error de compilación

        System.out.println("=== FIN PRUEBA CLASE ABSTRACTA ===");
    }
}