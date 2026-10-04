package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Color;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Direct-access repository for COLOR. Fixed record: int + CHAR(20) = 44 bytes.
 */
public class ColorRepository {

    public static final int NOMBRE_LEN = 20;
    public static final int RECORD_SIZE = 4 + NOMBRE_LEN * 2;

    private final File file;

    public ColorRepository() throws IOException {
        this.file = FilePaths.color();
    }

    ColorRepository(File file) {
        this.file = file;
    }

    public void insert(Color color) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, color);
        }
    }

    public Color findByCodigo(int codigo) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                Color c = read(raf);
                if (c.getCodigo() == codigo) {
                    return c;
                }
            }
        }
        return null;
    }

    public List<Color> findAll() throws IOException {
        List<Color> list = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                list.add(read(raf));
            }
        }
        return list;
    }

    public boolean update(Color color) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                if (raf.readInt() == color.getCodigo()) {
                    raf.seek(i * RECORD_SIZE);
                    write(raf, color);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean delete(int codigo) throws IOException {
        List<Color> all = findAll();
        boolean removed = all.removeIf(c -> c.getCodigo() == codigo);
        if (!removed) {
            return false;
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(0);
            for (Color c : all) {
                write(raf, c);
            }
        }
        return true;
    }

    public int nextId() throws IOException {
        int max = 0;
        for (Color c : findAll()) {
            if (c.getCodigo() > max) {
                max = c.getCodigo();
            }
        }
        return max + 1;
    }

    private static void write(RandomAccessFile raf, Color color) throws IOException {
        raf.writeInt(color.getCodigo());
        FixedString.writeFixed(raf, color.getNombre(), NOMBRE_LEN);
    }

    private static Color read(RandomAccessFile raf) throws IOException {
        int codigo = raf.readInt();
        String nombre = FixedString.readFixed(raf, NOMBRE_LEN);
        return new Color(codigo, nombre);
    }
}
