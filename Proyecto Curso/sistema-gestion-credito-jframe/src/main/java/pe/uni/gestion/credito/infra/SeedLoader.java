package pe.uni.gestion.credito.infra;

import java.io.File;
import java.util.List;
import pe.uni.gestion.credito.entity.Catalog;
import pe.uni.gestion.credito.entity.MarcaModelo;
import pe.uni.gestion.credito.repository.CatalogRepository;
import pe.uni.gestion.credito.repository.MarcaModeloRepository;

/**
 * Seeds catalog files on first run. If a file already has data, it is kept.
 * Run manually: java -cp target/classes pe.uni.gestion.credito.infra.SeedLoader
 */
public final class SeedLoader {

    private SeedLoader() {
    }

    public static void ensureSeeded() throws Exception {
        seed("nacionalidad.dat", 20, new Object[][]{
            {0, "No Especificado"}, {1, "Peruana"}, {2, "Extranjera"}});
        seed("tipodoc.dat", 25, new Object[][]{
            {0, "No Especificado"}, {1, "DNI"}, {2, "Carnet de Extranjeria"}, {3, "Pasaporte"}});
        seed("sexo.dat", 15, new Object[][]{
            {1, "Masculino"}, {2, "Femenino"}});
        seed("estadocivil.dat", 15, new Object[][]{
            {1, "Soltero"}, {2, "Casado"}, {3, "Divorciado"}, {4, "Viudo"}});
        seed("sitlaboral.dat", 15, new Object[][]{
            {1, "Dependiente"}, {2, "Independiente"}, {3, "Jubilado"}});
        seed("moneda.dat", 15, new Object[][]{
            {1, "Soles (PEN)"}, {2, "Dolares (USD)"}});
        seed("plazo.dat", 15, new Object[][]{
            {12, "12 meses"}, {24, "24 meses"}, {36, "36 meses"}, {48, "48 meses"}, {60, "60 meses"}});
        seed("tipovehiculo.dat", 20, new Object[][]{
            {0, "No Especificado"}, {1, "Sedan"}, {2, "SUV"}, {3, "Pickup"}, {4, "Hatchback"}});
        seed("estadosolicitud.dat", 20, new Object[][]{
            {1, "Registrada"}, {2, "En evaluacion"}, {3, "Aprobada"}, {4, "Rechazada"}});
        seed("cargo.dat", 20, new Object[][]{
            {1, "Asesor"}, {2, "Analista"}, {3, "Cajero"}, {4, "Administrador"}});
        seedColor();
        seedMarcaModelo();
    }

    private static void seed(String fileName, int nameLen, Object[][] rows) throws Exception {
        File file = FilePaths.file(fileName);
        CatalogRepository repo = new CatalogRepository(file, nameLen);
        if (!repo.isEmpty()) {
            return;
        }
        for (Object[] row : rows) {
            repo.insert(new Catalog((Integer) row[0], (String) row[1]));
        }
    }

    private static void seedColor() throws Exception {
        File file = FilePaths.color();
        CatalogRepository repo = new CatalogRepository(file, 20);
        if (!repo.isEmpty()) {
            return;
        }
        repo.insert(new Catalog(1, "Negro"));
        repo.insert(new Catalog(2, "Gris"));
        repo.insert(new Catalog(3, "Blanco"));
        repo.insert(new Catalog(4, "Rojo"));
    }

    private static void seedMarcaModelo() throws Exception {
        File file = FilePaths.file("marcamodelo.dat");
        MarcaModeloRepository repo = new MarcaModeloRepository(file);
        if (!repo.isEmpty()) {
            return;
        }
        String[][] rows = {
            {"0100", "TOYOTA"}, {"0101", "YARIS"}, {"0102", "COROLLA"}, {"0103", "RAV4"},
            {"0200", "HYUNDAI"}, {"0201", "ACCENT"}, {"0202", "TUCSON"},
            {"0300", "KIA"}, {"0301", "RIO"}, {"0302", "SPORTAGE"}};
        for (String[] row : rows) {
            repo.insert(new MarcaModelo(row[0], row[1]));
        }
    }

    public static void main(String[] args) throws Exception {
        ensureSeeded();
        System.out.println("Seeds OK. Files in ./data/:");
        File dir = new File("data");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(" - " + f.getName() + " (" + f.length() + " bytes)");
            }
        }
        // Quick read-back proof for teaching.
        CatalogRepository moneda = new CatalogRepository(FilePaths.file("moneda.dat"), 15);
        List<Catalog> monedas = moneda.findAll();
        System.out.println("Monedas: " + monedas);
    }
}
