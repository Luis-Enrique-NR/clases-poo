package pe.uni.gestion.credito.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FilePaths {

    private static final Path BASE = Paths.get("data");

    public FilePaths() {
    }

    /**
    * Obtiene un objeto File correspondiente a un archivo dentro del directorio base.
    * Si el directorio base o el archivo no existen, se crean automáticamente.
    */
    public static File file(String name) throws IOException {
        
        // 1. Asegura que el directorio base exista (crea la ruta completa si no existe)
        Files.createDirectories(BASE);

        // 2. Combina el directorio base con el nombre del archivo para obtener su ruta completa
        Path path = BASE.resolve(name);

        // 3. Comprueba si el archivo todavía no existe en el sistema de archivos
        if (!Files.exists(path)) {
            // Si no existe, crea un archivo vacío en dicha ruta
            Files.createFile(path);
        }

        // 4. Convierte el objeto Path a un objeto File tradicional de Java y lo retorna
        return path.toFile();
    }

    public static File color() throws IOException {
        return file("color.dat");
    }

    public static File cliente() throws IOException {
        return file("cliente.dat");
    }

    public static File vehiculo() throws IOException {
        return file("vehiculo.dat");
    }

    public static File concesionario() throws IOException {
        return file("concesionario.dat");
    }

    public static File empleado() throws IOException {
        return file("empleado.dat");
    }

    public static File solicitud() throws IOException {
        return file("solicitud.dat");
    }
}
