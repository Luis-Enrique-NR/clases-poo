# Instrucciones JFrame — SolicitudPanel en NetBeans (manual, para la explicación)

> Requisito: proyecto `sistema-gestion-credito-jframe` abierto en NetBeans, con Etapas 0–4 compilando en verde.
> No pegar código generado a mano. Todo lo visual se hace en la pestaña Design.
> Tiempo estimado: 30–40 minutos explicando en voz alta.

## 1. Crear el JPanel Form

1. En Projects, clic derecho sobre el paquete `pe.uni.gestion.credito.ui` → New → JPanel Form.
2. Class Name: `SolicitudPanel`. Package: `pe.uni.gestion.credito.ui`. Finish.
3. Verifica que se crean dos archivos: `SolicitudPanel.java` y `SolicitudPanel.form`.

## 2. Armar el encabezado

1. Abre `SolicitudPanel.java` → pestaña Design.
2. Desde Palette → Swing Controls, arrastra un Label. Propiedades (Window → Properties):
   - text: `Nueva solicitud de crédito`.
   - font: Bold 16.
3. En Navigator renómbralo a `lblTitulo` (clic derecho → Rename).

## 3. Bloque Cliente

1. Arrastra Label → text `Documento cliente:`. Renómbralo `lblDoc`.
2. Arrastra Text Field → variable `txtDocCliente`, columns `15`.
3. Arrastra Button → text `Buscar`, variable `btnBuscarCliente`.
4. Arrastra Label → text `—`, variable `lblClienteDatos` (aquí mostrarás "id - nombres apellidos").

## 4. Bloque Vehículo

1. Label `Chasis:` (`lblChasis`).
2. Text Field `txtChasis`, columns `20`.
3. Button `Buscar` → `btnBuscarVehiculo`.
4. Label `—` → `lblVehiculoDatos`.

## 5. Bloque Asesor, Moneda y Plazo

1. Label `Id asesor:` + Text Field `txtIdAsesor`, text inicial `1`, columns `5`.
2. Label `Moneda:` + Combo Box `cmbMoneda`. En Properties → model: escribe una opción por línea `1 - Soles (PEN)`, `2 - Dolares (USD)`.
3. Label `Plazo:` + Combo Box `cmbPlazo` con `12`, `24`, `36`, `48`, `60`.

## 6. Bloque montos

1. Label `Precio:` + Text Field `txtPrecio`. En Properties marca `editable: false`.
2. Label `Cuota inicial:` + Text Field `txtInicial`, columns `10`.
3. Label `Monto:` + Text Field `txtMonto`, `editable: false`.
4. Label `Fecha:` + Label `lblFecha` (se llena con la fecha actual, no se tipea).

## 7. Botones y bandeja

1. Button `Confirmar` → `btnConfirmar`. Button `Limpiar` → `btnLimpiar`.
2. Label `Filtro:` + Combo Box `cmbFiltroEstado` con `Todos`, `Registrada`, `En evaluación`, `Aprobada`, `Rechazada`.
3. Desde Palette → Swing Controls arrastra Table → `tblSolicitudes`. En Properties → model: columnas `Id`, `Cliente`, `Vehículo`, `Monto`, `Plazo`, `Estado`. Marca `enabled: false` si solo es lectura (o deja edición desactivada por código después).

## 8. Conectar eventos (lo que se tipea en vivo)

1. Doble clic en `btnBuscarCliente` → se crea `btnBuscarClienteActionPerformed`. Explica en voz alta: "busco por documento con ClienteService y muestro el id en lblClienteDatos".
2. Doble clic en `btnBuscarVehiculo` → igual con VehiculoService; además llena `txtPrecio` con el precio encontrado.
3. Doble clic en `btnConfirmar` → explica las 4 reglas mientras escribes:
   - Armar `Solicitud` con ids + moneda + plazo + inicial.
   - Llamar `new SolicitudService().registrar(s)`.
   - `txtMonto` se llena con `s.getMontoSolicitado()` (nunca se tipea).
   - Recargar `tblSolicitudes` y limpiar `txtInicial`.
4. Doble clic en `btnLimpiar` → vacía campos, no toca archivos.

Guía de narración: cada botón es un "controller" que llama al service. Nada de `RandomAccessFile` aquí.

## 9. Probar sin shell principal

1. Clic derecho sobre el paquete `ui` → New → JFrame Form → `TestSolicitud` (solo prueba, se borra después).
2. En su Design, arrastra un Panel personalizado o usa el Inspector para agregar `SolicitudPanel` como componente principal. Alternativa rápida: en `TestSolicitud.java` Source, en el constructor tras `initComponents()`, añade `add(new SolicitudPanel())` a mano y explica que es solo andamio de prueba.
3. Clic derecho en `TestSolicitud.java` → Run File. Verifica: buscar cliente `87654321` y chasis `CHS00000000000001` (datos de Etapa 3), confirmar con inicial `10000` y plazo `36`, ver la fila nueva en la tabla.
4. Cierra y vuelve a correr: los datos siguen (vienen de `data/*.dat`).

## 10. Errores típicos durante la explicación

- `UnsupportedOperationException TODO en vivo`: aún no descomentaste el bloque REAL de Solicitud. Descomenta `repository` primero y luego `service`.
- Combo vacío: no cargaste `moneda.dat`; corre `SeedLoader` antes.
- `FileNotFoundException data/...`: corre desde la carpeta del proyecto, no desde otra ruta.
- Precio en cero: no pulsaste Buscar vehículo antes de Confirmar.

## 11. Cierre de la demo

1. Muestra `data/solicitud.dat` en el explorador: 60 bytes por registro.
2. Borra el `TestSolicitud` de prueba.
3. Recuerda: el shell con menú queda fuera de esta iteración.
