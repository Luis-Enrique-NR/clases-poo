# Sistema de Gestión de Crédito Vehicular

## OBJETIVO

Diseñar e implementar un Sistema de Información orientado a la Gestión de Créditos Vehiculares en arquitecturas **Desktop C/S y Web**.

| C/S | (Swing: Frames, Menús, Paneles, ...) |
| --- | --- |
| **Web** | (*Front End – Back End*) |

---

## DEFINICIÓN DEL PROBLEMA

Desarrollar un Sistema de Gestión de Crédito Vehicular.
El sistema debe permitir la operación y mantenimiento de los siguientes módulos:
* Tablas Generales
* Clientes
* Garantes / Avales
* Empleados (asesores y analistas)
* Vehículos
* Concesionarios
* Solicitudes de Crédito
* Evaluación y Aprobación
* Cronograma de Pagos
* Registro de Pagos
* etc...

## MODALIDAD

Trabajo Grupal

## FECHA DE PRESENTACIÓN

SEMANA ___

## DESCRIPCIÓN DE LOS DATOS

Se debe registrar la información en archivos de acceso directo en disco.
Cada entidad debe representar una clase:

### CLIENTE
* IdCliente
* Nacionalidad (1: Peruana 2: Extranjera)
* TipoDocIdentidad y NroDocumento
* Nombres y Apellidos
* Sexo
* Fecha Nacimiento
* Estado Civil
* Teléfono
* Correo
* Dirección
* Distrito
* Situación Laboral
* Ingreso Mensual

### GARANTE
* IdGarante
* IdCliente
* DNI
* Nombres y Apellidos
* Teléfono
* Dirección
* Ingreso Mensual

### EMPLEADO
* IdEmpleado
* DNI
* Nombres y Apellidos
* Sexo
* Fecha Nacimiento
* Cargo (Asesor, Analista, Cajero)
* Teléfono
* Correo
* Dirección
* Distrito

### CONCESIONARIO
* IdConcesionario
* RUC
* Razón Social
* Dirección
* Teléfono

### VEHÍCULO
* IdVehiculo
* Tipo (1: Sedán 2: SUV 3: Pickup, ...)
* IdMarcaModelo
* Color
* Año
* Condición (Nuevo / Usado)
* Nro Serie / Chasis
* Nro Motor
* Precio
* IdConcesionario

### SOLICITUD_CREDITO
* IdSolicitud
* IdCliente
* IdVehiculo
* IdEmpleado (asesor)
* FechaSolicitud
* Moneda
* PrecioVehiculo
* CuotaInicial
* MontoSolicitado
* Plazo (meses)
* Estado

### EVALUACION
* IdEvaluacion
* IdSolicitud
* IdEmpleado (analista)
* FechaEvaluacion
* CalificacionCrediticia
* Resultado (Aprobado / Rechazado / Observado)
* Observaciones

### CREDITO
* IdCredito
* IdSolicitud
* FechaDesembolso
* MontoAprobado
* TasaInteres (TEA)
* Plazo
* CuotaMensual
* IdSeguro
* Estado

### CRONOGRAMA_CUOTAS
* IdCredito
* NroCuota
* FechaVencimiento
* Capital
* Interés
* Seguro
* MontoCuota
* Saldo
* EstadoCuota

### PAGO
* IdPago
* IdCredito
* NroCuota
* FechaPago
* HoraPago
* MontoPagado
* Mora
* MedioPago
* IdEmpleado
* TipoDocumento (1: Boleta 2: Factura)
* NroDocumento

## TABLAS GENERALES

### NACIONALIDAD
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0 | No Especificado |
| 1 | Peruana |
| 2 | Extranjera |

### SEXO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Masculino |
| 2 | Femenino |

### TIPO_DOC_IDENTIDAD
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0 | No Especificado |
| 1 | DNI |
| 2 | Carnet de Extranjería |
| 3 | Pasaporte |

### ESTADO_CIVIL
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Soltero |
| 2 | Casado |
| 3 | Divorciado |
| 4 | Viudo |

### SITUACION_LABORAL
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Dependiente |
| 2 | Independiente |
| 3 | Jubilado |

### MARCA_MODELO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0100 | TOYOTA |
| 0101 | YARIS |
| 0102 | COROLLA |
| 0103 | RAV4 |
| 0200 | HYUNDAI |
| 0201 | ACCENT |
| 0202 | TUCSON |
| 0300 | KIA |
| 0301 | RIO |
| 0302 | SPORTAGE |
| 0400 | NISSAN |
| .... | .......... |

### TIPO_VEHICULO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0 | No Especificado |
| 1 | Sedán |
| 2 | SUV |
| 3 | Pickup |
| 4 | Hatchback |
| ... | ... |

### COLOR
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0 | No Especificado |
| 1 | Negro |
| 2 | Gris |
| 3 | Plata |
| 4 | Azul |
| 5 | Verde |
| 6 | Rojo |
| 7 | Amarillo |
| 8 | Blanco |
| ... | ... |

### MONEDA
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Soles (PEN) |
| 2 | Dólares (USD) |

### PLAZO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 12 | 12 meses |
| 24 | 24 meses |
| 36 | 36 meses |
| 48 | 48 meses |
| 60 | 60 meses |

### ESTADO_SOLICITUD
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Registrada |
| 2 | En evaluación |
| 3 | Aprobada |
| 4 | Rechazada |

### ESTADO_CREDITO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Vigente |
| 2 | Cancelado |
| 3 | Moroso |
| 4 | Refinanciado |

### ESTADO_CUOTA
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Pendiente |
| 2 | Pagada |
| 3 | Vencida |

### MEDIO_PAGO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Efectivo |
| 2 | Transferencia |
| 3 | Tarjeta |
| 4 | Débito automático |

### TIPO_DOCUMENTO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 0 | No Especificado |
| 1 | Boleta |
| 2 | Factura |

### CARGO
- Código
- Nombre

| CÓDIGO | NOMBRE |
| :--- | :--- |
| 1 | Asesor |
| 2 | Analista |
| 3 | Cajero |
| 4 | Administrador |

## CÓMO EMPEZAR

1. **Arma el documento primero.** Copia el formato con las secciones anteriores; es lo que el profesor revisa para ver si entendieron el problema.
2. **Dibuja el flujo del negocio:** Cliente $\rightarrow$ Solicitud $\rightarrow$ Evaluación $\rightarrow$ Crédito $\rightarrow$ Cronograma $\rightarrow$ Pagos. Así verás qué entidad se relaciona con cuál.
3. **Crea las clases en Java.** Empieza por una clase por cada tabla general, luego Cliente, Empleado y Vehículo, y al final las transaccionales (Solicitud, Crédito, Pago).
4. **Implementa los archivos de acceso directo.** Usa RandomAccessFile con registros de tamaño fijo. Haz primero el CRUD de una tabla simple (por ejemplo, Color) y luego replica el patrón en las demás.
5. **Arma la interfaz Swing.** Un menú principal con opciones Mantenimiento, Créditos, Pagos y Reportes, y un formulario por módulo.
6. **Agrega la lógica clave.** El cálculo de la cuota con el método francés, cuota = $M \cdot i / (1 - (1 + i)^{-n})$, y la generación automática del cronograma. Esto es lo que diferencia tu proyecto.
7. **Al final, la versión Web.** Con las mismas clases como back end, añade un front end en HTML/JS o JSP.