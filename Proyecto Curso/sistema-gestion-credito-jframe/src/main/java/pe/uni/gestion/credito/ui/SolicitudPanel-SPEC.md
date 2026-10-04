# SPEC manual — ui/SolicitudPanel (lo arma el usuario en NetBeans, no generar código)

Tipo: `JPanel Form` nuevo en `pe.uni.gestion.credito.ui`, nombre `SolicitudPanel`.
No crear `JFrame`. No escribir lógica de archivos aquí. Solo llama a `SolicitudService`.

## Título
- Título superior: "Nueva solicitud de crédito".

## Campos (de arriba hacia abajo)
1. Cliente: `txtDocCliente` (texto) + botón `btnBuscarCliente` + etiqueta `lblClienteDatos` (muestra "id - nombres apellidos" tras buscar).
2. Vehículo: `txtChasis` (texto) + botón `btnBuscarVehiculo` + etiqueta `lblVehiculoDatos` (muestra "id - marca modelo - precio").
3. Asesor: `txtIdAsesor` (texto, por defecto 1) + etiqueta de verificación.
4. Moneda: `cmbMoneda` (combo cargado desde `moneda.dat`: 1 Soles, 2 Dólares).
5. Plazo: `cmbPlazo` (combo: 12, 24, 36, 48, 60).
6. Precio: `txtPrecio` (solo lectura, se llena desde el vehículo).
7. Cuota inicial: `txtInicial` (editable, número > 0 y < precio).
8. Monto solicitado: `txtMonto` (solo lectura, calculado como precio − inicial).
9. Fecha: automática del día, no se ingresa.

## Botones
- `btnConfirmar`: valida y llama a `solicitudService.registrar(...)`. Al éxito, recarga la bandeja y limpia inicial.
- `btnLimpiar`: vacía todos los campos y la bandeja sigue igual.
- `btnBuscarCliente`: busca por documento vía `clienteService` (solo lectura para traer el id).
- `btnBuscarVehiculo`: busca por chasis vía `vehiculoService` (solo lectura para traer id y precio).

## Bandeja (debajo del formulario)
- Tabla `tblSolicitudes` de solo lectura: Id, Cliente, Vehículo, Monto, Plazo, Estado.
- Filtro `cmbFiltroEstado`: Todos / Registrada / En evaluación / Aprobada / Rechazada.

## Reglas visibles
- Monto nunca se tipea, siempre se calcula.
- Confirmar exige cliente y vehículo encontrados, inicial válida y plazo seleccionado.
- Tras confirmar, la nueva fila aparece con estado Registrada.
