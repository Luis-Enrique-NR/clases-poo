package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Vehiculo;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Repository for VEHICULO. RECORD_SIZE = 156 bytes.
 */
public class VehiculoRepository {

    public static final int RECORD_SIZE = 156;

    private static final int LEN_MARCA = 4;
    private static final int LEN_COND = 10;
    private static final int LEN_CHA = 25;
    private static final int LEN_MOT = 25;

    private final File file;

    public VehiculoRepository() throws Exception {
        this.file = FilePaths.vehiculo();
    }

    VehiculoRepository(File file) {
        this.file = file;
    }

    public void insert(Vehiculo v) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, v);
        }
    }

    public Vehiculo findById(int id) throws Exception {
        for (Vehiculo v : findAll()) {
            if (v.getIdVehiculo() == id) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> findAll() throws Exception {
        List<Vehiculo> list = new ArrayList<>();
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
        for (Vehiculo v : findAll()) {
            if (v.getIdVehiculo() > max) {
                max = v.getIdVehiculo();
            }
        }
        return max + 1;
    }

    private static void write(RandomAccessFile raf, Vehiculo v) throws Exception {
        raf.writeInt(v.getIdVehiculo());
        raf.writeInt(v.getTipo());
        FixedString.writeFixed(raf, v.getIdMarcaModelo(), LEN_MARCA);
        raf.writeInt(v.getColor());
        raf.writeInt(v.getAnio());
        FixedString.writeFixed(raf, v.getCondicion(), LEN_COND);
        FixedString.writeFixed(raf, v.getNroChasis(), LEN_CHA);
        FixedString.writeFixed(raf, v.getNroMotor(), LEN_MOT);
        raf.writeDouble(v.getPrecio());
        raf.writeInt(v.getIdConcesionario());
    }

    private static Vehiculo read(RandomAccessFile raf) throws Exception {
        Vehiculo v = new Vehiculo();
        v.setIdVehiculo(raf.readInt());
        v.setTipo(raf.readInt());
        v.setIdMarcaModelo(FixedString.readFixed(raf, LEN_MARCA));
        v.setColor(raf.readInt());
        v.setAnio(raf.readInt());
        v.setCondicion(FixedString.readFixed(raf, LEN_COND));
        v.setNroChasis(FixedString.readFixed(raf, LEN_CHA));
        v.setNroMotor(FixedString.readFixed(raf, LEN_MOT));
        v.setPrecio(raf.readDouble());
        v.setIdConcesionario(raf.readInt());
        return v;
    }
}
