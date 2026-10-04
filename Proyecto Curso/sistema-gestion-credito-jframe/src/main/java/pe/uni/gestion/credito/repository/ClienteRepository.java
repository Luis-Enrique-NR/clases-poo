package pe.uni.gestion.credito.repository;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import pe.uni.gestion.credito.entity.Cliente;
import pe.uni.gestion.credito.infra.FilePaths;
import pe.uni.gestion.credito.infra.FixedString;

/**
 * Direct-access repository for CLIENTE. RECORD_SIZE = 500 bytes.
 * Layout: 6 ints + long + double + fixed chars in field order.
 */
public class ClienteRepository {

    public static final int RECORD_SIZE = 500;

    private static final int LEN_DOC = 15;
    private static final int LEN_NOM = 30;
    private static final int LEN_APE = 40;
    private static final int LEN_TEL = 15;
    private static final int LEN_MAIL = 40;
    private static final int LEN_DIR = 60;
    private static final int LEN_DIS = 30;

    private final File file;

    public ClienteRepository() throws Exception {
        this.file = FilePaths.cliente();
    }

    ClienteRepository(File file) {
        this.file = file;
    }

    public void insert(Cliente c) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            write(raf, c);
        }
    }

    
    public Cliente findById(int id) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                Cliente c = read(raf);
                if (c.getIdCliente() == id) {
                    return c;
                }
            }
        }
        return null;
    }

    public Cliente findByDocumento(String doc) throws Exception {
        if (doc == null) {
            return null;
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long count = raf.length() / RECORD_SIZE;
            for (long i = 0; i < count; i++) {
                raf.seek(i * RECORD_SIZE);
                Cliente c = read(raf);
                if (doc.trim().equalsIgnoreCase(c.getNroDocumento())) {
                    return c;
                }
            }
        }
        return null;
    }

    public List<Cliente> findAll() throws Exception {
        
        List<Cliente> list = new ArrayList<>();
        
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
        for (Cliente c : findAll()) {
            if (c.getIdCliente() > max) {
                max = c.getIdCliente();
            }
        }
        return max + 1;
    }

    private static void write(RandomAccessFile raf, Cliente c) throws Exception {
        raf.writeInt(c.getIdCliente());
        raf.writeInt(c.getNacionalidad());
        raf.writeInt(c.getTipoDoc());
        FixedString.writeFixed(raf, c.getNroDocumento(), LEN_DOC);
        FixedString.writeFixed(raf, c.getNombres(), LEN_NOM);
        FixedString.writeFixed(raf, c.getApellidos(), LEN_APE);
        raf.writeInt(c.getSexo());
        raf.writeLong(c.getFechaNacimiento());
        raf.writeInt(c.getEstadoCivil());
        FixedString.writeFixed(raf, c.getTelefono(), LEN_TEL);
        FixedString.writeFixed(raf, c.getCorreo(), LEN_MAIL);
        FixedString.writeFixed(raf, c.getDireccion(), LEN_DIR);
        FixedString.writeFixed(raf, c.getDistrito(), LEN_DIS);
        raf.writeInt(c.getSituacionLaboral());
        raf.writeDouble(c.getIngresoMensual());
    }

    private static Cliente read(RandomAccessFile raf) throws Exception {
        Cliente c = new Cliente();
        c.setIdCliente(raf.readInt());
        c.setNacionalidad(raf.readInt());
        c.setTipoDoc(raf.readInt());
        c.setNroDocumento(FixedString.readFixed(raf, LEN_DOC));
        c.setNombres(FixedString.readFixed(raf, LEN_NOM));
        c.setApellidos(FixedString.readFixed(raf, LEN_APE));
        c.setSexo(raf.readInt());
        c.setFechaNacimiento(raf.readLong());
        c.setEstadoCivil(raf.readInt());
        c.setTelefono(FixedString.readFixed(raf, LEN_TEL));
        c.setCorreo(FixedString.readFixed(raf, LEN_MAIL));
        c.setDireccion(FixedString.readFixed(raf, LEN_DIR));
        c.setDistrito(FixedString.readFixed(raf, LEN_DIS));
        c.setSituacionLaboral(raf.readInt());
        c.setIngresoMensual(raf.readDouble());
        return c;
    }
}
