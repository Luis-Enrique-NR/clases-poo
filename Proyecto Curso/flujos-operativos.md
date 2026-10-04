# Flujos Operativos — Sistema de Gestión de Crédito Vehicular (Fase 1: JFrame + Archivos)

## 1. Alcance de esta fase

- Solo aplicación desktop con Swing (`JFrame`).
- Persistencia en archivos de acceso directo (`RandomAccessFile`, registros de tamaño fijo).
- Sin web. Sin SQL. Sin base de datos.
- Ventana principal única (`FORM`): menú lateral fijo y panel central que cambia. No se abren ventanas hijas.
- Este documento define qué debe programar. El diccionario de datos con longitudes se define después, a partir de estos flujos.

## 2. Actores

| Actor | Función |
|---|---|
| Administrador | Mantiene tablas generales, clientes, empleados, vehículos, concesionarios, garantes. Consulta reportes. |
| Asesor | Registra solicitudes de crédito. |
| Analista | Evalúa solicitudes y emite resultado. |
| Cajero | Registra pagos de cuotas. |

## 3. Estados del sistema

### 3.1 Solicitud (ESTADO_SOLICITUD)
1. Registrada
2. En evaluación
3. Aprobada
4. Rechazada

Transiciones permitidas:
- Registrada → En evaluación (al iniciar evaluación).
- En evaluación → Aprobada / Rechazada (al registrar resultado).
- No se permite modificar una solicitud Aprobada o Rechazada.

### 3.2 Crédito (ESTADO_CREDITO)
1. Vigente
2. Cancelado
3. Moroso
4. Refinanciado (fuera de Fase 1, solo se reserva el código).

### 3.3 Cuota (ESTADO_CUOTA)
1. Pendiente
2. Pagada
3. Vencida (se calcula por fecha, no se registra manualmente).

## 4. Flujos operativos de principio a fin

### F0. Mantenimiento de tablas generales
**Qué resuelve:** cargar los catálogos que usan los demás formularios.

**Tablas incluidas:** Nacionalidad, Sexo, TipoDocIdentidad, EstadoCivil, SituaciónLaboral, MarcaModelo, TipoVehículo, Color, Moneda, Plazo, EstadoSolicitud, EstadoCredito, EstadoCuota, MedioPago, TipoDocumento, Cargo.

**Flujo:**
1. El administrador selecciona Mantenimiento → Tablas generales → tabla (por ejemplo, Color).
2. El sistema muestra lista (código + nombre) leída del archivo correspondiente.
3. El administrador registra un nuevo código + nombre. El sistema valida que el código no exista.
4. El administrador edita el nombre de un código existente. El código no se puede modificar.
5. El administrador elimina un código solo si no está en uso por otra entidad.

**Qué programar:**
- Una clase por tabla (código + nombre).
- Un repositorio de archivo por tabla con operaciones: insertar, buscar por código, listar todos, actualizar nombre, eliminar.
- Un panel genérico de mantenimiento (tabla + formulario código/nombre).

**Criterio de aceptación:** el CRUD de Color funciona de punta a punta desde el panel y persiste al cerrar y abrir la aplicación.

---

### F1. Mantenimiento de Clientes
**Qué resuelve:** registrar la base de personas que pueden solicitar crédito.

**Flujo:**
1. El administrador selecciona Mantenimiento → Clientes.
2. El sistema muestra la lista de clientes (IdCliente, NroDocumento, Nombres y Apellidos).
3. El administrador registra un cliente con todos los campos de la ficha (ver contexto.md: nacionalidad, tipo y número de documento, nombres, apellidos, sexo, fecha de nacimiento, estado civil, teléfono, correo, dirección, distrito, situación laboral, ingreso mensual).
4. El sistema valida: IdCliente único, NroDocumento único, campos obligatorios completos, correo con formato válido, ingreso mensual mayor o igual a cero.
5. El administrador edita los datos del cliente. El IdCliente no se modifica.
6. El administrador elimina un cliente solo si no tiene solicitudes registradas.

**Qué programar:**
- Clase `Cliente` con todos los campos.
- Repositorio `ClienteRepository` (archivo de tamaño fijo).
- Servicio `ClienteService` con validaciones del paso 4.
- Panel `ClientePanel`: tabla + formulario + botones Guardar / Editar / Eliminar / Limpiar.

---

### F2. Mantenimiento de Empleados, Vehículos, Concesionarios y Garantes
**Qué resuelve:** completar las entidades base antes de operar créditos.

**Flujo:** idéntico a F1, con sus propios campos:
- Empleado: IdEmpleado, DNI, nombres y apellidos, sexo, fecha de nacimiento, cargo (Asesor / Analista / Cajero / Administrador), teléfono, correo, dirección, distrito.
- Vehículo: IdVehiculo, tipo, marca/modelo, color, año, condición (Nuevo / Usado), chasis, motor, precio, concesionario.
- Concesionario: IdConcesionario, RUC, razón social, dirección, teléfono.
- Garante: IdGarante, IdCliente asociado, DNI, nombres y apellidos, teléfono, dirección, ingreso mensual.

**Validaciones mínimas:**
- Códigos de tablas generales deben existir (por ejemplo, no se puede guardar un vehículo con un color inexistente).
- Precio de vehículo mayor a cero.
- RUC y DNI con longitud exacta.
- No eliminar un registro en uso (por ejemplo, vehículo con solicitud activa).

**Qué programar:** por cada entidad, clase + repositorio + servicio + panel. Reutilizar el patrón probado en F0 y F1.

---

### F3. Solicitud de crédito
**Qué resuelve:** formalizar el pedido de un cliente por un vehículo.

**Precondiciones:** existen el cliente, el vehículo, el asesor y el concesionario.

**Flujo:**
1. El asesor selecciona Créditos → Nueva solicitud.
2. El sistema muestra formulario: cliente (búsqueda por documento), vehículo (búsqueda por serie o modelo), asesor, fecha (automática), moneda, precio del vehículo (automático desde Vehículo), cuota inicial, plazo en meses.
3. El asesor ingresa cuota inicial y plazo. El sistema calcula monto solicitado = precio − cuota inicial. Valida que la cuota inicial sea mayor a cero y menor al precio, y que el plazo sea uno de 12 / 24 / 36 / 48 / 60.
4. El asesor confirma. El sistema genera IdSolicitud correlativo, guarda la solicitud con estado Registrada y la muestra en la bandeja de solicitudes.
5. La bandeja permite buscar por número, cliente o estado.

**Qué programar:**
- Clase `SolicitudCredito` + repositorio + servicio con cálculo de monto y validaciones.
- Panel `SolicitudPanel` (formulario + bandeja con filtro por estado).
- Generador de correlativos para IdSolicitud.

---

### F4. Evaluación y aprobación
**Qué resuelve:** decidir si la solicitud se aprueba.

**Precondiciones:** existe una solicitud en estado Registrada o En evaluación.

**Flujo:**
1. El analista selecciona Créditos → Evaluación y elige una solicitud Registrada. Al abrirla, la solicitud pasa a En evaluación.
2. El sistema muestra los datos de la solicitud, del cliente (ingreso mensual, situación laboral) y del vehículo.
3. El analista registra: fecha de evaluación (automática), calificación crediticia, resultado (Aprobado / Rechazado / Observado) y observaciones.
4. Al confirmar, el sistema guarda la evaluación y actualiza la solicitud a Aprobada o Rechazada. Si el resultado es Observado, la solicitud regresa a Registrada para corrección.
5. Una solicitud Aprobada o Rechazada queda bloqueada contra edición.

**Qué programar:**
- Clase `Evaluacion` + repositorio + servicio.
- Panel `EvaluacionPanel` con bloqueo de edición según estado.
- Regla de transición de estados de solicitud.

---

### F5. Desembolso y cronograma de pagos
**Qué resuelve:** convertir una solicitud aprobada en un crédito con cuotas.

**Precondiciones:** solicitud en estado Aprobada, sin crédito previo generado.

**Flujo:**
1. El administrador selecciona Créditos → Desembolso y elige una solicitud Aprobada.
2. El sistema muestra: monto aprobado (igual al monto solicitado salvo ajuste manual), tasa de interés anual (TEA), plazo, fecha de desembolso.
3. Al confirmar, el sistema genera el Crédito con estado Vigente y genera automáticamente el cronograma de N cuotas con el método francés: cuota = M · i / (1 − (1 + i)^−n), donde i es la tasa mensual equivalente a la TEA y n el plazo en meses.
4. Cada cuota contiene: número, fecha de vencimiento (mensual desde el desembolso), capital, interés, seguro, monto de cuota, saldo restante, estado Pendiente.
5. El sistema muestra el cronograma generado y bloquea la generación duplicada para la misma solicitud.

**Qué programar:**
- Clases `Credito` y `CronogramaCuota` + repositorios.
- Servicio `CreditoService` con cálculo de cuota francesa y generación de cronograma.
- Panel `CreditoPanel` con datos del crédito + tabla de cronograma.

---

### F6. Registro de pagos
**Qué resuelve:** cobrar las cuotas del crédito.

**Precondiciones:** existe un crédito Vigente con cuotas Pendientes o Vencidas.

**Flujo:**
1. El cajero selecciona Pagos → Registrar pago y busca el crédito por número o por cliente.
2. El sistema muestra el cronograma con el estado de cada cuota (Pendiente / Pagada / Vencida según fecha actual).
3. El cajero selecciona la primera cuota pendiente, ingresa fecha y hora (automáticas), monto pagado, medio de pago, empleado que cobra, tipo y número de documento (Boleta / Factura).
4. El sistema valida que el monto corresponda a la cuota (o calcula mora si hay atraso), marca la cuota como Pagada y guarda el pago.
5. Si todas las cuotas quedan Pagadas, el crédito pasa a Cancelado. Si hay cuotas vencidas sin pagar, el crédito pasa a Moroso.
6. No se permite pagar una cuota ya Pagada ni saltear el orden de cuotas.

**Qué programar:**
- Clase `Pago` + repositorio + servicio con reglas de orden y mora.
- Panel `PagoPanel` (búsqueda de crédito + cronograma + formulario de pago).
- Actualización automática de estados de cuota y crédito.

---

### F7. Reportes y consultas
**Qué resuelve:** supervisar la operación sin modificar datos.

**Reportes de Fase 1 (solo lectura):**
1. Solicitudes por estado (Registrada / En evaluación / Aprobada / Rechazada).
2. Créditos por estado (Vigente / Cancelado / Moroso).
3. Cronograma por crédito (cuotas con vencimiento, monto y estado).
4. Pagos por crédito y por rango de fechas.
5. Clientes con créditos activos.

**Qué programar:** panel de reportes con filtros y tablas de solo lectura que reutilizan los repositorios existentes.

## 5. Orden de construcción sugerido

1. F0 (una tabla, por ejemplo Color) → valida el patrón de archivos.
2. F1 (Clientes) → valida el patrón de entidad completa + panel.
3. F2 (demás mantenimientos) → réplica.
4. F3 → F4 → F5 → F6 en ese orden, porque cada uno depende del anterior.
5. F7 al final, porque solo lee lo ya guardado.

## 6. Lo que queda fuera de Fase 1

- Versión web (front end / back end).
- Cálculo de seguro detallado (usar valor fijo o cero por ahora).
- Refinanciamiento de créditos.
- Usuarios y contraseñas (Gestión de Usuarios del menú actual queda pendiente hasta definir autenticación).
- Soporte y Sedes del menú actual: no corresponden a este sistema y deben reemplazarse por Mantenimiento, Créditos, Pagos y Reportes.
