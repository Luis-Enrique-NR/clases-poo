package pe.uni.gestion.credito.spike;

/**
 * Internal verification for Etapa 3. No business logic here.
 */
public class Etapa3Verify {

    public static void main(String[] args) throws Exception {
        pe.uni.gestion.credito.infra.SeedLoader.ensureSeeded();

        pe.uni.gestion.credito.service.ConcesionarioService concService =
                new pe.uni.gestion.credito.service.ConcesionarioService();
        if (concService.listar().isEmpty()) {
            pe.uni.gestion.credito.entity.Concesionario conc = new pe.uni.gestion.credito.entity.Concesionario();
            conc.setRuc("20123456789");
            conc.setRazonSocial("AUTOS LIMA SAC");
            conc.setDireccion("AV. PRUEBA 123");
            conc.setTelefono("012345678");
            concService.registrar(conc);
        }

        pe.uni.gestion.credito.service.EmpleadoService empService =
                new pe.uni.gestion.credito.service.EmpleadoService();
        if (empService.listar().isEmpty()) {
            pe.uni.gestion.credito.entity.Empleado emp = new pe.uni.gestion.credito.entity.Empleado();
            emp.setDni("12345678");
            emp.setNombres("ANA");
            emp.setApellidos("ASESOR PRUEBA");
            emp.setSexo(2);
            emp.setFechaNacimiento(19900101L);
            emp.setCargo(1);
            emp.setTelefono("999888777");
            emp.setCorreo("ana@test.pe");
            emp.setDireccion("CALLE 1");
            emp.setDistrito("LIMA");
            empService.registrar(emp);
        }

        pe.uni.gestion.credito.service.ClienteService cliService =
                new pe.uni.gestion.credito.service.ClienteService();
        if (cliService.listar().isEmpty()) {
            pe.uni.gestion.credito.entity.Cliente cli = new pe.uni.gestion.credito.entity.Cliente();
            cli.setNacionalidad(1);
            cli.setTipoDoc(1);
            cli.setNroDocumento("87654321");
            cli.setNombres("JUAN");
            cli.setApellidos("PEREZ PRUEBA");
            cli.setSexo(1);
            cli.setFechaNacimiento(19900505L);
            cli.setEstadoCivil(1);
            cli.setTelefono("987654321");
            cli.setCorreo("juan@test.pe");
            cli.setDireccion("AV. SIEMPRE VIVA 123");
            cli.setDistrito("MIRAFLORES");
            cli.setSituacionLaboral(1);
            cli.setIngresoMensual(3500.0);
            cliService.registrar(cli);
        }

        pe.uni.gestion.credito.service.VehiculoService vehService =
                new pe.uni.gestion.credito.service.VehiculoService();
        if (vehService.listar().isEmpty()) {
            int concId = concService.listar().get(0).getIdConcesionario();
            pe.uni.gestion.credito.entity.Vehiculo v = new pe.uni.gestion.credito.entity.Vehiculo();
            v.setTipo(1);
            v.setIdMarcaModelo("0101");
            v.setColor(1);
            v.setAnio(2024);
            v.setCondicion("Nuevo");
            v.setNroChasis("CHS00000000000001");
            v.setNroMotor("MOT00000000000001");
            v.setPrecio(55000.0);
            v.setIdConcesionario(concId);
            vehService.registrar(v);
        }

        System.out.println("Clientes: " + cliService.listar());
        System.out.println("Vehiculos: " + vehService.listar());
        System.out.println("ETAPA3_OK");
    }
}
