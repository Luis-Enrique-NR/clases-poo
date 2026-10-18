package pe.uni.parcial;

public class Test_Interface {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA INTERFAZ ===\n");

        // Crear Monitor
        Interface_Equipo_Computo monitor = new Interface_Monitor(
            "Samsung", "Odyssey G5", 1200.00, "SN123456",
            27.0, "2560x1440", "VA"
        );

        // Crear Impresora
        Interface_Equipo_Computo impresora = new Interface_Impresora(
            "HP", "LaserJet Pro", 850.00, "SN789012",
            "laser", 22, true
        );

        // Polimorfismo: array de tipo interfaz
        Interface_Equipo_Computo[] equipos = { monitor, impresora };

        System.out.println("--- Recorrido polimórfico ---\n");
        for (Interface_Equipo_Computo eq : equipos) {
            eq.mostrarInfo();
            System.out.println();
        }

        // Demostración: múltiples implementaciones posibles
        // Interface_Equipo_Computo otro = new OtraImplementacion(...);

        System.out.println("=== FIN PRUEBA INTERFAZ ===");
    }
}