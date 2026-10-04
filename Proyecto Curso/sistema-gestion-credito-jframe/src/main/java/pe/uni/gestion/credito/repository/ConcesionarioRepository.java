package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Concesionario;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Repository for CONCESIONARIO. RECORD_SIZE = 276 bytes.
 */
public class ConcesionarioRepository {

    public static final int RECORD_SIZE = 276;

    private static final int LEN_RUC = 11;
    private static final int LEN_RS = 50;
    private static final int LEN_DIR = 60;
    private static final int LEN_TEL = 15;

    private final File file;

    public ConcesionarioRepository() throws Exception {
        this.file = FilePaths.concesionario();
    }

    ConcesionarioRepository(File file) {
        this.file = file;
    }

    public void insert(Concesionario c) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, c);
        }
    }

    public Concesionario findById(int id) throws Exception {
        for (Concesionario c : findAll()) {
            if (c.getIdConcesionario() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Concesionario> findAll() throws Exception {
        List<Concesionario> list = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                list.add(read(raf));
            }
        }
        return list;
    }

    public int nextId() throws Exception {
        int max = 0;
        for (Concesionario c : findAll()) {
            if (c.getIdConcesionario() > max) {
                max = c.getIdConcesionario();
            }
        }
        return max + 1;
    }

    private static void write(RandomAccessFile raf, Concesionario c) throws Exception {
        raf.writeInt(c.getIdConcesionario());
        FixedString.writeFixed(raf, c.getRuc(), LEN_RUC);
        FixedString.writeFixed(raf, c.getRazonSocial(), LEN_RS);
        FixedString.writeFixed(raf, c.getDireccion(), LEN_DIR);
        FixedString.writeFixed(raf, c.getTelefono(), LEN_TEL);
    }

    private static Concesionario read(RandomAccessFile raf) throws Exception {
        Concesionario c = new Concesionario();
        c.setIdConcesionario(raf.readInt());
        c.setRuc(FixedString.readFixed(raf, LEN_RUC));
        c.setRazonSocial(FixedString.readFixed(raf, LEN_RS));
        c.setDireccion(FixedString.readFixed(raf, LEN_DIR));
        c.setTelefono(FixedString.readFixed(raf, LEN_TEL));
        return c;
    }
}
