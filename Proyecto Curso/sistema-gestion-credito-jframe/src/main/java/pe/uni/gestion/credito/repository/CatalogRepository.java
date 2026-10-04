package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Catalog;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Direct-access repository for int-code catalogs.
 * Record: int codigo + CHAR(nameLen).
 */
public class CatalogRepository {

    private final File file;
    private final int nameLen;
    private final int recordSize;

    public CatalogRepository(File file, int nameLen) {
        this.file = file;
        this.nameLen = nameLen;
        this.recordSize = 4 + nameLen * 2;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void insert(Catalog entry) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            raf.writeInt(entry.getCodigo());
            FixedString.writeFixed(raf, entry.getNombre(), nameLen);
        }
    }

    public Catalog findByCodigo(int codigo) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / recordSize;
            for (long i = 0; i < count; i++) {
                raf.seek(i * recordSize);
                int code = raf.readInt();
                String name = FixedString.readFixed(raf, nameLen);
                if (code == codigo) {
                    return new Catalog(code, name);
                }
            }
        }
        return null;
    }

    public List<Catalog> findAll() throws Exception {
        List<Catalog> list = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / recordSize;
            for (long i = 0; i < count; i++) {
                raf.seek(i * recordSize);
                list.add(new Catalog(raf.readInt(), FixedString.readFixed(raf, nameLen)));
            }
        }
        return list;
    }

    public boolean isEmpty() throws Exception {
        return file.length() == 0;
    }
}
