# Tasks - Parcial POO

## Pregunta 2: Herencia y Polimorfismo (6 pts)

**Objetivo:** Implementar 3 bloques funcionalmente equivalentes usando 3 enfoques distintos.

### Jerarquía de clases
```
Equipo_Computo (padre)
├── Monitor (hijo)
└── Impresora (hijo)
```

### Bloques a implementar (cada uno = 4 clases Java)

| Bloque | Enfoque | Clases requeridas |
|--------|---------|-------------------|
| **Inherit** | Herencia simple (`extends`) | `Inherit_Equipo_Computo.java`, `Inherit_Monitor.java`, `Inherit_Impresora.java`, `Test_Inherit.java` |
| **Abstract** | Clase abstracta + métodos abstractos | `Abstract_Equipo_Computo.java`, `Abstract_Monitor.java`, `Abstract_Impresora.java`, `Test_Abstract.java` |
| **Interface** | Interfaz + implementaciones | `Interface_Equipo_Computo.java`, `Interface_Monitor.java`, `Interface_Impresora.java`, `Test_Interface.java` |

### Requisitos funcionales (idénticos en los 3 bloques)
- `Equipo_Computo`: clase base con atributos comunes (marca, modelo, precio, etc.) y método `mostrarInfo()`
- `Monitor`: hereda/implementa + atributos propios (pulgadas, resolución, tipo panel) + sobrescribe `mostrarInfo()`
- `Impresora`: hereda/implementa + atributos propios (tipo: laser/inkjet, ppm, duplex) + sobrescribe `mostrarInfo()`
- `Test_*`: `main()` que crea instancias de Monitor e Impresora, llama `mostrarInfo()` demostrando polimorfismo

### Entregable
Carpeta `Pregunta2/` con subcarpetas `Inherit/`, `Abstract/`, `Interface/` cada una con sus 4 `.java`.

---

## Pregunta 3: Procesamiento de Archivos

**Objetivo:** Leer 6 archivos `.DAT` de longitud fija y calcular 3 reportes.

### Archivos de entrada (longitud de registro fija)

| Archivo | Long. | Campos (posiciones fijas) |
|---------|-------|---------------------------|
| `CLIENTES.DAT` | 32 | Id(4), RazonSocial(20), RUC(11) |
| `EMPLEADO.DAT` | 19 | Id(3), NomApe(16) |
| `CATEGORIAS.DAT` | 33 | Id(1), Descripcion(32) |
| `PRODUCTOS.DAT` | 37 | Id(4), Nombre(20), IdCategoria(1), Precio(8), IdMoneda(1) |
| `REGISTRO_VENTAS_CABECERA.DAT` | 41 | Id(4), Fecha(8), Hora(6), IdCliente(4), TipoPago(1), Monto(8), IdMoneda(1), IdEmpleado(3) |
| `REGISTRO_VENTAS_DETALLE.DAT` | 15 | Id(4), Secuencia(2), IdProducto(4), Cantidad(5) |

*Los datos de ejemplo están en `Guia-examen-pasado.md`.*

### Reportes a generar
1. **Producto con mayor cantidad de ventas** → sumar `Cantidad` por `IdProducto` en DETALLE, mostrar Nombre del producto
2. **Cliente con más frecuencia** → contar ocurrencias de `IdCliente` en CABECERA, mostrar RazonSocial
3. **Empleado con mayor cantidad de operaciones** → contar ocurrencias de `IdEmpleado` en CABECERA, mostrar NomApe

### Restricción técnica
**Estrategia elegida: Acceso directo** (`RandomAccessFile` + `seek()` + buffer fijo)

Usar la plantilla `Modelo_RandomAccessFile` del enunciado como base. No usar `FileReader`/`BufferedReader`.

### Entregable
Carpeta `Pregunta3/` con:
- `Main.java` (o nombre similar) con `main()` que procesa todo e imprime los 3 reportes
- Clases auxiliares si se desea (ej. `Cliente`, `Producto`, `Venta`, `LectorArchivos`)

---

## Estructura final esperada

```
PARCIAL/
├── tasks.md                    (este archivo)
├── Pregunta2/
│   ├── Inherit/
│   │   ├── Inherit_Equipo_Computo.java
│   │   ├── Inherit_Monitor.java
│   │   ├── Inherit_Impresora.java
│   │   └── Test_Inherit.java
│   ├── Abstract/
│   │   ├── Abstract_Equipo_Computo.java
│   │   ├── Abstract_Monitor.java
│   │   ├── Abstract_Impresora.java
│   │   └── Test_Abstract.java
│   └── Interface/
│       ├── Interface_Equipo_Computo.java
│       ├── Interface_Monitor.java
│       ├── Interface_Impresora.java
│       └── Test_Interface.java
└── Pregunta3/
    ├── Main.java
    ├── Cliente.java
    ├── Empleado.java
    ├── Categoria.java
    ├── Producto.java
    ├── VentaCabecera.java
    ├── VentaDetalle.java
    └── LectorArchivos.java   (o similar, según estrategia elegida)
```

---

## Notas para la implementación

- **Sin TDD**, sin tests automatizados, sin frameworks. Código simple, directo, estilo "examen de parcial".
- En Pregunta 2: que cada bloque compile y ejecute independiente. El `Test_*` es la prueba.
- En Pregunta 3: parsear campos por `substring()` según posiciones fijas. Manejar `trim()` en strings.
- Los archivos `.DAT` se deben colocar en la carpeta de trabajo (mismo nivel que los `.java` o ruta relativa).
- Usar `double` para precios/montos, `int` para cantidades/IDs.