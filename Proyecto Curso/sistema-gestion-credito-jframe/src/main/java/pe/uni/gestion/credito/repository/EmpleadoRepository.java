package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Empleado;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Repository for EMPLEADO. RECORD_SIZE = 466 bytes.
 */
public class EmpleadoRepository {

    public static final int RECORD_SIZE = 466;

    private static final int LEN_DNI = 8;
    private static final int LEN_NOM = 30;
    private static final int LEN_APE = 40;
    private static final int LEN_TEL = 15;
    private static final int LEN_MAIL = 40;
    private static final int LEN_DIR = 60;
    private static final int LEN_DIS = 30;

    private final File file;

    public EmpleadoRepository() throws Exception {
        this.file = FilePaths.empleado();
    }

    EmpleadoRepository(File file) {
        this.file = file;
    }

    public void insert(Empleado e) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, e);
        }
    }

    public Empleado findById(int id) throws Exception {
        for (Empleado e : findAll()) {
            if (e.getIdEmpleado() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Empleado> findAll() throws Exception {
        List<Empleado> list = new ArrayList<>();
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
        for (Empleado e : findAll()) {
            if (e.getIdEmpleado() > max) {
                max = e.getIdEmpleado();
            }
        }
        return max + 1;
    }

    private static void write(RandomAccessFile raf, Empleado e) throws Exception {
        raf.writeInt(e.getIdEmpleado());
        FixedString.writeFixed(raf, e.getDni(), LEN_DNI);
        FixedString.writeFixed(raf, e.getNombres(), LEN_NOM);
        FixedString.writeFixed(raf, e.getApellidos(), LEN_APE);
        raf.writeInt(e.getSexo());
        raf.writeLong(e.getFechaNacimiento());
        raf.writeInt(e.getCargo());
        FixedString.writeFixed(raf, e.getTelefono(), LEN_TEL);
        FixedString.writeFixed(raf, e.getCorreo(), LEN_MAIL);
        FixedString.writeFixed(raf, e.getDireccion(), LEN_DIR);
        FixedString.writeFixed(raf, e.getDistrito(), LEN_DIS);
    }

    private static Empleado read(RandomAccessFile raf) throws Exception {
        Empleado e = new Empleado();
        e.setIdEmpleado(raf.readInt());
        e.setDni(FixedString.readFixed(raf, LEN_DNI));
        e.setNombres(FixedString.readFixed(raf, LEN_NOM));
        e.setApellidos(FixedString.readFixed(raf, LEN_APE));
        e.setSexo(raf.readInt());
        e.setFechaNacimiento(raf.readLong());
        e.setCargo(raf.readInt());
        e.setTelefono(FixedString.readFixed(raf, LEN_TEL));
        e.setCorreo(FixedString.readFixed(raf, LEN_MAIL));
        e.setDireccion(FixedString.readFixed(raf, LEN_DIR));
        e.setDistrito(FixedString.readFixed(raf, LEN_DIS));
        return e;
    }
}
