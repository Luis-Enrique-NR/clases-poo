package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.MarcaModelo;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Repository for MARCA_MODELO: CHAR(4) codigo + CHAR(20) nombre = 48 bytes.
 */
public class MarcaModeloRepository {

    public static final int CODIGO_LEN = 4;
    public static final int NOMBRE_LEN = 20;
    public static final int RECORD_SIZE = CODIGO_LEN * 2 + NOMBRE_LEN * 2;

    private final File file;

    public MarcaModeloRepository(File file) {
        this.file = file;
    }

    public void insert(MarcaModelo entry) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            FixedString.writeFixed(raf, entry.getCodigo(), CODIGO_LEN);
            FixedString.writeFixed(raf, entry.getNombre(), NOMBRE_LEN);
        }
    }

    public MarcaModelo findByCodigo(String codigo) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                String code = FixedString.readFixed(raf, CODIGO_LEN);
                String name = FixedString.readFixed(raf, NOMBRE_LEN);
                if (code.equals(codigo)) {
                    return new MarcaModelo(code, name);
                }
            }
        }
        return null;
    }

    public List<MarcaModelo> findAll() throws Exception {
        List<MarcaModelo> list = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                list.add(new MarcaModelo(
                        FixedString.readFixed(raf, CODIGO_LEN),
                        FixedString.readFixed(raf, NOMBRE_LEN)));
            }
        }
        return list;
    }

    public boolean isEmpty() throws Exception {
        return file.length() == 0;
    }
}
