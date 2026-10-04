package pe.uni.gestion.credito.repository;

import java.io.File;
import java.util.List;
import pe.uni.gestion.credito.entity.Solicitud;
import pe.uni.gestion.credito.infra.FilePaths;

public class SolicitudRepository {

    public static final int RECORD_SIZE = 60;

    private final File file;

    public SolicitudRepository() throws Exception {
        this.file = FilePaths.solicitud();
    }

    SolicitudRepository(File file) {
        this.file = file;
    }

    /* REAL insert: append at end of file.
    public void insert(Solicitud s) throws Exception {
        try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, s);
        }
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public void insert(Solicitud s) throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar insert");
    }

    /* REAL findById: scan all records and filter by id.
    public Solicitud findById(int id) throws Exception {
        for (Solicitud s : findAll()) {
            if (s.getIdSolicitud() == id) {
                return s;
            }
        }
        return null;
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public Solicitud findById(int id) throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar findById");
    }

    /* REAL findAll: sequential read of fixed records.
    public List<Solicitud> findAll() throws Exception {
        java.util.List<Solicitud> list = new java.util.ArrayList<>();
        try (java.io.RandomAccessFile raf = new java.io.RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                list.add(read(raf));
            }
        }
        return list;
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public List<Solicitud> findAll() throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar findAll");
    }

    /* REAL nextId: max + 1, from 1 when empty.
    public int nextId() throws Exception {
        int max = 0;
        for (Solicitud s : findAll()) {
            if (s.getIdSolicitud() > max) {
                max = s.getIdSolicitud();
            }
        }
        return max + 1;
    }
    */
    // TODO en vivo: descomentar el bloque REAL de arriba y borrar este stub.
    public int nextId() throws Exception {
        throw new UnsupportedOperationException("TODO en vivo: implementar nextId");
    }

    /* REAL helpers: write/read deben seguir el mismo orden de campos.
    private static void write(java.io.RandomAccessFile raf, Solicitud s) throws Exception {
        raf.writeInt(s.getIdSolicitud());
        raf.writeInt(s.getIdCliente());
        raf.writeInt(s.getIdVehiculo());
        raf.writeInt(s.getIdEmpleado());
        raf.writeLong(s.getFechaSolicitud());
        raf.writeInt(s.getMoneda());
        raf.writeDouble(s.getPrecioVehiculo());
        raf.writeDouble(s.getCuotaInicial());
        raf.writeDouble(s.getMontoSolicitado());
        raf.writeInt(s.getPlazo());
        raf.writeInt(s.getEstado());
    }

    private static Solicitud read(java.io.RandomAccessFile raf) throws Exception {
        Solicitud s = new Solicitud();
        s.setIdSolicitud(raf.readInt());
        s.setIdCliente(raf.readInt());
        s.setIdVehiculo(raf.readInt());
        s.setIdEmpleado(raf.readInt());
        s.setFechaSolicitud(raf.readLong());
        s.setMoneda(raf.readInt());
        s.setPrecioVehiculo(raf.readDouble());
        s.setCuotaInicial(raf.readDouble());
        s.setMontoSolicitado(raf.readDouble());
        s.setPlazo(raf.readInt());
        s.setEstado(raf.readInt());
        return s;
    }
    */
}
