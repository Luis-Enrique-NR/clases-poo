# Diccionario de Datos — Iteración 1: Solicitud Registrable

## Convenciones

- Fase 1: archivos de acceso directo con `RandomAccessFile`. Todo registro es de tamaño fijo.
- `INT`: entero Java de 4 bytes.
- `LONG`: entero largo de 8 bytes. Las fechas se guardan como `LONG` en formato AAAAMMDD (ejemplo: 20260115).
- `DOUBLE`: decimal de 8 bytes. Se usa para montos.
- `CHAR(n)`: texto de longitud fija de n caracteres. Se guarda con `writeChar`, 2 bytes por carácter. Si el valor es más corto, se rellena con espacios. Si es más largo, se trunca.
- `RECORD_SIZE`: suma total en bytes del registro. No cambia después de definida.
- `PK`: clave primaria. No se repite. No se modifica.
- `FK`: clave foránea. Debe existir en la tabla indicada.
- Archivo físico sugerido entre paréntesis, por ejemplo `cliente.dat`.

## Tablas generales necesarias (catálogos)

Solo las que usa la Iteración 1. Se cargan por archivo semilla. El código no se modifica, solo el nombre.

### NACIONALIDAD (`nacionalidad.dat`, RECORD_SIZE = 44)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 0: No especificado, 1: Peruana, 2: Extranjera. |
| Nombre | CHAR(20) | 20 caracteres | — | Nombre del catálogo. |

RECORD_SIZE = 4 + 40 = 44 bytes.

### TIPO_DOC_IDENTIDAD (`tipodoc.dat`, RECORD_SIZE = 54)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 0: No especificado, 1: DNI, 2: Carnet de extranjería, 3: Pasaporte. |
| Nombre | CHAR(25) | 25 caracteres | — | Nombre del documento. |

RECORD_SIZE = 4 + 50 = 54 bytes.

### SEXO (`sexo.dat`, RECORD_SIZE = 34)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Masculino, 2: Femenino. |
| Nombre | CHAR(15) | 15 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 30 = 34 bytes.

### ESTADO_CIVIL (`estadocivil.dat`, RECORD_SIZE = 34)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Soltero, 2: Casado, 3: Divorciado, 4: Viudo. |
| Nombre | CHAR(15) | 15 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 30 = 34 bytes.

### SITUACION_LABORAL (`sitlaboral.dat`, RECORD_SIZE = 34)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Dependiente, 2: Independiente, 3: Jubilado. |
| Nombre | CHAR(15) | 15 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 30 = 34 bytes.

### MONEDA (`moneda.dat`, RECORD_SIZE = 34)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Soles (PEN), 2: Dólares (USD). |
| Nombre | CHAR(15) | 15 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 30 = 34 bytes.

### PLAZO (`plazo.dat`, RECORD_SIZE = 34)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | Meses: 12, 24, 36, 48, 60. El código es el plazo. |
| Nombre | CHAR(15) | 15 caracteres | — | Ejemplo: "12 meses". |

RECORD_SIZE = 4 + 30 = 34 bytes.

### TIPO_VEHICULO (`tipovehiculo.dat`, RECORD_SIZE = 44)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 0: No especificado, 1: Sedán, 2: SUV, 3: Pickup, 4: Hatchback. |
| Nombre | CHAR(20) | 20 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 40 = 44 bytes.

### COLOR (`color.dat`, RECORD_SIZE = 44)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | Correlativo desde 1. |
| Nombre | CHAR(20) | 20 caracteres | — | Ejemplo: Negro, Blanco, Rojo. |

RECORD_SIZE = 4 + 40 = 44 bytes.

### MARCA_MODELO (`marcamodelo.dat`, RECORD_SIZE = 48)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | CHAR(4) | 4 caracteres | PK | Código compuesto: 0100 TOYOTA, 0101 YARIS, 0200 HYUNDAI. Se usa texto para conservar el cero inicial. |
| Nombre | CHAR(20) | 20 caracteres | — | Nombre de marca o modelo. |

RECORD_SIZE = 8 + 40 = 48 bytes.

### ESTADO_SOLICITUD (`estadosolicitud.dat`, RECORD_SIZE = 44)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Registrada, 2: En evaluación, 3: Aprobada, 4: Rechazada. |
| Nombre | CHAR(20) | 20 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 40 = 44 bytes.

### CARGO (`cargo.dat`, RECORD_SIZE = 44)

| Atributo | Tipo | Longitud máxima | Clave | Descripción |
|---|---|---|---|---|
| Codigo | INT | — | PK | 1: Asesor, 2: Analista, 3: Cajero, 4: Administrador. |
| Nombre | CHAR(20) | 20 caracteres | — | Nombre. |

RECORD_SIZE = 4 + 40 = 44 bytes.

## Entidades base de la Iteración 1

### CLIENTE (`cliente.dat`, RECORD_SIZE = 500)

| Atributo | Tipo | Longitud máxima | Clave | Descripción y validación |
|---|---|---|---|---|
| IdCliente | INT | — | PK | Correlativo desde 1. No se modifica. |
| Nacionalidad | INT | — | FK → NACIONALIDAD | Código de nacionalidad. Debe existir. |
| TipoDoc | INT | — | FK → TIPO_DOC_IDENTIDAD | Código de documento. Debe existir. |
| NroDocumento | CHAR(15) | 15 caracteres | — | Único. Si TipoDoc es DNI, longitud exacta de 8 dígitos. |
| Nombres | CHAR(30) | 30 caracteres | — | Obligatorio. |
| Apellidos | CHAR(40) | 40 caracteres | — | Obligatorio. Incluye paterno y materno. |
| Sexo | INT | — | FK → SEXO | Debe existir. |
| FechaNacimiento | LONG | AAAAMMDD | — | Fecha válida, no futura. |
| EstadoCivil | INT | — | FK → ESTADO_CIVIL | Debe existir. |
| Telefono | CHAR(15) | 15 caracteres | — | Puede incluir + y espacios. |
| Correo | CHAR(40) | 40 caracteres | — | Formato con @. Puede quedar vacío. |
| Direccion | CHAR(60) | 60 caracteres | — | Dirección completa. |
| Distrito | CHAR(30) | 30 caracteres | — | Distrito de residencia. |
| SituacionLaboral | INT | — | FK → SITUACION_LABORAL | Debe existir. |
| IngresoMensual | DOUBLE | — | — | Mayor o igual a cero. |

RECORD_SIZE = 4×6 (24) + 8 + 8 (16) + (15+30+40+15+40+60+30)×2 (460) = 500 bytes.

### EMPLEADO (`empleado.dat`, RECORD_SIZE = 466)

Solo se requiere un asesor para la Iteración 1. Los demás cargos se agregan en la Iteración 2.

| Atributo | Tipo | Longitud máxima | Clave | Descripción y validación |
|---|---|---|---|---|
| IdEmpleado | INT | — | PK | Correlativo desde 1. |
| DNI | CHAR(8) | 8 caracteres | — | Único. Solo dígitos. |
| Nombres | CHAR(30) | 30 caracteres | — | Obligatorio. |
| Apellidos | CHAR(40) | 40 caracteres | — | Obligatorio. |
| Sexo | INT | — | FK → SEXO | Debe existir. |
| FechaNacimiento | LONG | AAAAMMDD | — | Fecha válida. |
| Cargo | INT | — | FK → CARGO | En Iteración 1 debe ser 1 (Asesor). |
| Telefono | CHAR(15) | 15 caracteres | — | Opcional. |
| Correo | CHAR(40) | 40 caracteres | — | Formato con @. |
| Direccion | CHAR(60) | 60 caracteres | — | Dirección. |
| Distrito | CHAR(30) | 30 caracteres | — | Distrito. |

RECORD_SIZE = 466 bytes (4 + 16 + 60 + 80 + 4 + 8 + 4 + 30 + 80 + 120 + 60).

### CONCESIONARIO (`concesionario.dat`, RECORD_SIZE = 276)

| Atributo | Tipo | Longitud máxima | Clave | Descripción y validación |
|---|---|---|---|---|
| IdConcesionario | INT | — | PK | Correlativo desde 1. |
| RUC | CHAR(11) | 11 caracteres | — | Único. Solo dígitos. Longitud exacta 11. |
| RazonSocial | CHAR(50) | 50 caracteres | — | Obligatorio. |
| Direccion | CHAR(60) | 60 caracteres | — | Dirección del local. |
| Telefono | CHAR(15) | 15 caracteres | — | Teléfono de contacto. |

RECORD_SIZE = 276 bytes (4 + 22 + 100 + 120 + 30).

### VEHICULO (`vehiculo.dat`, RECORD_SIZE = 156)

| Atributo | Tipo | Longitud máxima | Clave | Descripción y validación |
|---|---|---|---|---|
| IdVehiculo | INT | — | PK | Correlativo desde 1. |
| Tipo | INT | — | FK → TIPO_VEHICULO | Debe existir. |
| IdMarcaModelo | CHAR(4) | 4 caracteres | FK → MARCA_MODELO | Debe existir. |
| Color | INT | — | FK → COLOR | Debe existir. |
| Anio | INT | — | — | Entre 2000 y año actual + 1. |
| Condicion | CHAR(10) | 10 caracteres | — | Valores: "Nuevo" o "Usado". |
| NroChasis | CHAR(25) | 25 caracteres | — | Único. |
| NroMotor | CHAR(25) | 25 caracteres | — | Único. |
| Precio | DOUBLE | — | — | Mayor a cero. Es la base de la solicitud. |
| IdConcesionario | INT | — | FK → CONCESIONARIO | Debe existir. |

RECORD_SIZE = 156 bytes (4 + 4 + 8 + 4 + 4 + 20 + 50 + 50 + 8 + 4).

### SOLICITUD_CREDITO (`solicitud.dat`, RECORD_SIZE = 60)

| Atributo | Tipo | Longitud máxima | Clave | Descripción y validación |
|---|---|---|---|---|
| IdSolicitud | INT | — | PK | Correlativo desde 1. |
| IdCliente | INT | — | FK → CLIENTE | Debe existir. |
| IdVehiculo | INT | — | FK → VEHICULO | Debe existir. Sin solicitud previa Aprobada o Vigente para el mismo vehículo si se define esa regla. |
| IdEmpleado | INT | — | FK → EMPLEADO | Asesor que registra. Debe existir y ser cargo Asesor. |
| FechaSolicitud | LONG | AAAAMMDD | — | Automática del día. |
| Moneda | INT | — | FK → MONEDA | Debe existir. |
| PrecioVehiculo | DOUBLE | — | — | Copiado de VEHICULO.Precio al momento de registrar. |
| CuotaInicial | DOUBLE | — | — | Mayor a cero y menor al precio. |
| MontoSolicitado | DOUBLE | — | — | Calculado: PrecioVehiculo − CuotaInicial. No se ingresa a mano. |
| Plazo | INT | — | FK → PLAZO | Debe ser 12, 24, 36, 48 o 60. |
| Estado | INT | — | FK → ESTADO_SOLICITUD | Inicial 1 (Registrada). Solo cambia por el flujo F4. |

RECORD_SIZE = 4×6 (24) + 8 (32) + 8×3 (56)... detalle: 4 (Id) + 4 (Cliente) + 4 (Vehículo) + 4 (Empleado) + 8 (Fecha) + 4 (Moneda) + 8 (Precio) + 8 (Inicial) + 8 (Monto) + 4 (Plazo) + 4 (Estado) = 64 bytes.

RECORD_SIZE = 60 bytes.

## Resumen de archivos de la Iteración 1

- Catálogos: nacionalidad.dat, tipodoc.dat, sexo.dat, estadocivil.dat, sitlaboral.dat, moneda.dat, plazo.dat, tipovehiculo.dat, color.dat, marcamodelo.dat, estadosolicitud.dat, cargo.dat.
- Base: cliente.dat (500), empleado.dat (466), concesionario.dat (276), vehiculo.dat (160).
- Operativa: solicitud.dat (64).

## Reglas que el código debe cumplir

1. Ningún `FK` puede apuntar a un código inexistente. Antes de guardar, el servicio verifica existencia en el archivo correspondiente.
2. Los campos `CHAR(n)` se rellenan con espacios a la derecha y se recortan al leer.
3. Los correlativos `PK` se generan como máximo existente + 1. Si el archivo está vacío, desde 1.
4. `MontoSolicitado` nunca se pide por pantalla. Siempre se calcula.
5. Una solicitud con estado distinto de Registrada no se edita desde el formulario de solicitud.
