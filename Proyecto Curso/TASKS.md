# TASKS — Iteración 1: Solicitud Registrable (JFrame + Archivos)

> Fuente: `contexto.md`, `flujos-operativos.md` (F0–F3), `diccionario.md`.
> Alcance: solo desktop Swing + `RandomAccessFile`. Sin web, sin SQL.
> Proyecto: `Proyecto Curso/sistema-gestion-credito-jframe/`
> Paquete base: `pe.uni.gestion.credito`

## Etapa 0 — Andamiaje base (preparación, sin lógica de negocio) ✅ 2026-10-04

- [x] 0.1 Crear paquetes: `infra`, `entity`, `repository`, `service`, `ui`, `spike`.
- [x] 0.2 `infra/FixedString.java`: `writeFixed(raf, texto, n)` con padding a espacios y truncate; `readFixed(raf, n)` con trim. Sin dependencias.
- [x] 0.3 `infra/FilePaths.java`: resuelve `data/*.dat`, crea carpeta `data/` y archivo vacío si no existe. Métodos `color()`, `cliente()`, `vehiculo()`, `concesionario()`, `empleado()`, `solicitud()` y catálogos.
- [x] 0.4 Verificar `mvn clean compile` en verde. Decisión: Java 24 (JDK instalado 24.0.1, pom bajado de 26 a 24).

## Etapa 1 — Spike interno Color (solo verificación, no se enseña) ✅ 2026-10-04

- [x] 1.1 `entity/Color.java`: `codigo:int`, `nombre:String`. `RECORD_SIZE = 44`.
- [x] 1.2 `repository/ColorRepository.java`: `insert`, `findByCodigo`, `findAll`, `update`, `delete`, `nextId()` con `seek(pos * 44)`.
- [x] 1.3 `service/ColorService.java`: valida código no duplicado y nombre no vacío.
- [x] 1.4 `spike/ColorSpike.java`: solo llama al service (insertar 2 + listar por consola). Sin lógica de archivos. Uso interno.
- [x] 1.5 Criterio: corre el spike, cierra y reabre, los datos persisten. Evidencia: `data/color.dat` 88 bytes (2×44), segunda corrida imprime `Already seeded` y lista `[1 - Rojo, 2 - Negro]`.

## Etapa 2 — Catálogos semilla (sin panel en Iteración 1) ✅ 2026-10-04

- [x] 2.1 Entities + repositories mínimos para: Nacionalidad, TipoDoc, Sexo, EstadoCivil, SitLaboral, Moneda, Plazo, TipoVehículo, MarcaModelo, EstadoSolicitud, Cargo. Implementado con `entity/Catalog.java` + `entity/MarcaModelo.java` y `repository/CatalogRepository.java` (int + CHAR(n)) + `repository/MarcaModeloRepository.java` (CHAR(4)+CHAR(20)=48) para evitar 24 archivos boilerplate.
- [x] 2.2 `infra/SeedLoader.java`: precarga valores del diccionario si el `.dat` está vacío. Ejecutable manual: `java -cp target/classes pe.uni.gestion.credito.infra.SeedLoader`.
- [x] 2.3 Criterio: `data/*.dat` de catálogos existen con datos tras primera ejecución. Evidencia: 12 archivos en `./data/`, `moneda.dat` 68 bytes con `[1 - Soles (PEN), 2 - Dolares (USD)]`, `marcamodelo.dat` 480 bytes (10×48). Nota: `color.dat` conserva 88 bytes del spike (1-Rojo,2-Negro) porque el seeder no sobrescribe datos existentes.

## Etapa 3 — Entidades base (soporte de solicitud) ✅ 2026-10-04

- [x] 3.1 `entity/Cliente.java` (15 campos, RECORD 500) + `repository/ClienteRepository.java` + `service/ClienteService.java` con validaciones del diccionario (Id único, documento único, FK existentes, correo con @, ingreso >= 0).
- [x] 3.2 `entity/Concesionario.java` (RECORD 276) + repository + service (RUC 11 dígitos único).
- [x] 3.3 `entity/Empleado.java` (RECORD 466) + repository + service. Solo asesor (cargo=1) obligatorio para Iteración 1.
- [x] 3.4 `entity/Vehiculo.java` (RECORD 156) + repository + service (FK tipo/marca/color/concesionario, precio > 0, chasis y motor únicos).
- [x] 3.5 Criterio: CRUD de cada entidad por consola persiste y valida FK. Evidencia interna `spike/Etapa3Verify`: `ETAPA3_OK`, `cliente.dat` 500, `concesionario.dat` 276, `empleado.dat` 466, `vehiculo.dat` 156 (1 registro c/u), segunda corrida reutiliza datos sin duplicar.

## Etapa 4 — Solicitud (backend en modo enseñanza; UI la hace el usuario) ✅ backend 2026-10-04

- [x] 4.1 `entity/Solicitud.java` (11 campos, RECORD 60). Completa, comentada, visible como guía.
- [x] 4.2 `repository/SolicitudRepository.java`: implementación real comentada en bloque + stub que lanza `UnsupportedOperationException("TODO en vivo")`. Compila en verde.
- [x] 4.3 `service/SolicitudService.java`: igual técnica (copiar precio, validar inicial, plazo 12/24/36/48/60, `monto = precio - inicial`, estado 1, Id max+1). Stub compilable.
- [ ] 4.4 UI manual (no generar código): el usuario crea `ui/SolicitudPanel` en NetBeans siguiendo `ui/SolicitudPanel-SPEC.md`.
- [x] 4.5 Sin shell en este proyecto (no existe `FORM` aquí). Integración con menú fuera de Etapa 4.
- [x] 4.6 Verificación interna previa al modo enseñanza: `Solicitudes: [1 - cli:1 veh:1 monto:45000.0 plazo:36 est:1]`, `solicitud.dat` 60 bytes (1×60). Runner interno retirado para no confundir la demo. Corrección: RECORD_SIZE real es 60 (7 ints + long + 3 doubles), no 64.

## Etapa 5 — Verificación final

- [ ] 5.1 `mvn clean compile` en verde con Solicitud en modo enseñanza (stubs).
- [ ] 5.2 Descomentar `SolicitudRepository` + `SolicitudService` y recompilar: demo punta a punta OK.
- [ ] 5.3 Revisar que ningún `JFrame` hijo quede en uso; solo `FORM` + paneles.
- [ ] 5.4 Archivos `.dat` en `data/` con tamaños múltiplos de su `RECORD_SIZE`.

## Orden de ejecución

`0 → 1 → 2 → 3 → 4 → 5`. No empezar UI (4.4/4.5) sin tener 1–3 en verde.

## Fuera de esta TASKS

F4 Evaluación, F5 Desembolso/cronograma francés, F6 Pagos, F7 Reportes, versión web. Quedan para Iteración 2/3.
