# Explicación Teórica: Pregunta 3 — Estimación de Tamaños de Campo y Uso de HashMap

## Contexto del Examen

La **Pregunta 3** proporciona **6 archivos `.DAT`** con **longitud de registro fija** y **nombres de campos**, pero **NO da el ancho exacto de cada campo**. El programador debe **deducir los anchos** para poder leer correctamente con `RandomAccessFile`.

| Archivo | Long. Registro | Campos |
|---------|----------------|--------|
| CLIENTES.DAT | 32 | Id, RazonSocial, RUC |
| EMPLEADO.DAT | 19 | Id, NomApe |
| CATEGORIAS.DAT | 33 | Id, Descripcion |
| PRODUCTOS.DAT | 37 | Id, Nombre, IdCategoria, Precio, IdMoneda |
| REGISTRO_VENTAS_CABECERA.DAT | 41 | Id, Fecha, Hora, IdCliente, TipoPago, Monto, IdMoneda, IdEmpleado |
| REGISTRO_VENTAS_DETALLE.DAT | 15 | Id, Secuencia, IdProducto, Cantidad |

Adicionalmente, el enunciado incluye **datos de ejemplo** (filas reales) que permiten inferir los anchos.

---

## Metodología General

1. **Sumar anchos conocidos** (basados en formato estándar o datos de ejemplo)
2. **Restar del total** para obtener el ancho del campo "variable" (usualmente descripción/nombre)
3. **Validar con datos de ejemplo** que el corte `substring(inicio, fin)` produce valores coherentes
4. **Ajustar** si hay desbordes o truncamientos

---

## Análisis por Archivo

### 1. CLIENTES.DAT — 32 bytes

**Campos:** `Id`, `RazonSocial`, `RUC`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **4** | Ejemplos: `C001`, `C002`... `C008` → 4 chars fijos |
| `RUC` | **11** | Ejemplos: `12345678901` (11 dígitos), `11223344556` → RUC peruano estándar = 11 dígitos |
| `RazonSocial` | **17** | **32 - 4 - 11 = 17** bytes restantes |

**Validación con ejemplo:**
```
C001Pedro Torres         12345678901
^^^^ ^^^^^^^^^^^^^^^ ^^^^^^^^^^^
  4        17            11
```
`"Pedro Torres"` = 12 chars → cabe en 17 con padding espacios.

---

### 2. EMPLEADO.DAT — 19 bytes

**Campos:** `Id`, `NomApe`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **3** | Ejemplos: `E01`, `E02`, `E03`, `E04` → 3 chars |
| `NomApe` | **16** | **19 - 3 = 16** bytes restantes |

**Validación:**
```
E01Luis Arias
^^^ ^^^^^^^^^^^
  3      16
```
`"Luis Arias"` = 10 chars → cabe en 16.

---

### 3. CATEGORIAS.DAT — 33 bytes

**Campos:** `Id`, `Descripcion`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **2** | Ejemplos: `1`, `2`, `3`, `4` → 1 dígito, pero alineado a 2 para fixed-width |
| `Descripcion` | **31** | **33 - 2 = 31** bytes restantes |

**Validación:**
```
 1Bebidas y Gaseosas
^^ ^^^^^^^^^^^^^^^^^^^
 2        31
```
`"Bebidas y Gaseosas"` = 18 chars → cabe en 31.
`"Equipos y Accesorios de Computo"` = 31 chars → **justo completo**.

---

### 4. PRODUCTOS.DAT — 37 bytes

**Campos:** `Id`, `Nombre`, `IdCategoria`, `Precio`, `IdMoneda`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **4** | Ejemplos: `P125`, `P248`, `P324`... `P957` → 4 chars |
| `IdCategoria` | **1** | Ejemplos: `1`, `2`, `3`, `4` → 1 dígito |
| `IdMoneda` | **1** | Ejemplos: `1`, `2` → 1 dígito |
| `Precio` | **11** | Ejemplos: `70.00` (5), `500.00` (6), `1000.00` (7) → alineado a derecha en 11 para acomodar `1000.00` + padding |
| `Nombre` | **20** | **37 - 4 - 1 - 11 - 1 = 20** bytes restantes |

**Validación:**
```
P125Memoria USB 8Gb       3          70.00 2
^^^^ ^^^^^^^^^^^^^^^^^^^ ^ ^^^^^^^^^^^ ^
  4        20           1     11      1
```
`"Memoria USB 8Gb"` = 15 chars → cabe en 20.
`"Impresora Laser 2400"` = 20 chars → **justo completo**.

---

### 5. REGISTRO_VENTAS_CABECERA.DAT — 41 bytes

**Campos:** `Id`, `Fecha`, `Hora`, `IdCliente`, `TipoPago`, `Monto`, `IdMoneda`, `IdEmpleado`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **4** | Ejemplos: `0001`...`0008` → 4 dígitos con ceros izquierda |
| `Fecha` | **8** | Formato `YYYYMMDD` → `20250519` = 8 chars |
| `Hora` | **6** | Formato `HHMMSS` → `091251` = 6 chars |
| `IdCliente` | **4** | Ejemplos: `C001`...`C008` → 4 chars |
| `TipoPago` | **1** | Ejemplos: `1`, `2` → 1 dígito |
| `IdMoneda` | **1** | Ejemplos: `1`, `2` → 1 dígito |
| `IdEmpleado` | **3** | Ejemplos: `E01`...`E04` → 3 chars |
| `Monto` | **14** | **41 - 4 - 8 - 6 - 4 - 1 - 1 - 3 = 14** bytes restantes |

**Validación:**
```
000120250519091251C0011              60.001E01
^^^^ ^^^^^^^^ ^^^^^^ ^^^^ ^ ^^^^^^^^^^^ ^ ^^^
  4     8      6     4   1      14      1   3
```
`Monto` ejemplos: `60.00` (5), `1350.00` (7), `800.00` (6) → 14 permite right-align con espacios.

---

### 6. REGISTRO_VENTAS_DETALLE.DAT — 15 bytes

**Campos:** `Id`, `Secuencia`, `IdProducto`, `Cantidad`

| Campo | Ancho | Justificación |
|-------|-------|---------------|
| `Id` | **4** | Ejemplos: `0001`...`0008` → 4 dígitos |
| `Secuencia` | **2** | Ejemplos: `01`, `02`, `03`... → 2 dígitos |
| `IdProducto` | **4** | Ejemplos: `P750`, `P691`, `P125`... → 4 chars |
| `Cantidad` | **5** | **15 - 4 - 2 - 4 = 5** bytes restantes |

**Validación:**
```
000101P750    2
^^^^ ^^ ^^^^ ^^^^^
  4   2   4    5
```
`Cantidad` ejemplos: `1`, `2`, `3`, `4`, `5`, `10` → 5 chars right-align permite `10` (2 dígitos) + 3 espacios.

---

## Tabla Resumen de Offsets (para `substring(inicio, fin)`)

| Archivo | Campo | Inicio | Fin | Longitud |
|---------|-------|--------|-----|----------|
| **CLIENTES** | Id | 0 | 4 | 4 |
| | RazonSocial | 4 | 21 | 17 |
| | RUC | 21 | 32 | 11 |
| **EMPLEADO** | Id | 0 | 3 | 3 |
| | NomApe | 3 | 19 | 16 |
| **CATEGORIAS** | Id | 0 | 2 | 2 |
| | Descripcion | 2 | 33 | 31 |
| **PRODUCTOS** | Id | 0 | 4 | 4 |
| | Nombre | 4 | 24 | 20 |
| | IdCategoria | 24 | 25 | 1 |
| | Precio | 25 | 36 | 11 |
| | IdMoneda | 36 | 37 | 1 |
| **CABECERA** | Id | 0 | 4 | 4 |
| | Fecha | 4 | 12 | 8 |
| | Hora | 12 | 18 | 6 |
| | IdCliente | 18 | 22 | 4 |
| | TipoPago | 22 | 23 | 1 |
| | Monto | 23 | 37 | 14 |
| | IdMoneda | 37 | 38 | 1 |
| | IdEmpleado | 38 | 41 | 3 |
| **DETALLE** | Id | 0 | 4 | 4 |
| | Secuencia | 4 | 6 | 2 |
| | IdProducto | 6 | 10 | 4 |
| | Cantidad | 10 | 15 | 5 |

> **Nota:** En Java `substring(inicio, fin)` el índice `fin` es **exclusivo**, por eso `substring(0,4)` toma posiciones 0,1,2,3 (4 caracteres).

---

## ¿Por Qué Usar `HashMap` en los Reportes?

### El Problema
Los reportes requieren **agrupar y sumar/contar** por una clave:
- **Reporte 1:** Sumar `Cantidad` por `IdProducto` (desde DETALLE)
- **Reporte 2:** Contar ocurrencias por `IdCliente` (desde CABECERA)
- **Reporte 3:** Contar ocurrencias por `IdEmpleado` (desde CABECERA)

### Alternativas Consideradas

| Enfoque | Ventajas | Desventajas |
|---------|----------|-------------|
| **Arrays paralelos** (`String[] ids`, `int[] totales`) | Simple, sin colecciones | Tamaño fijo, búsqueda O(n), propenso a errores de índice |
| **ArrayList de objetos** | Orientado a objetos | Búsqueda O(n) cada vez, mucho boilerplate |
| **HashMap\<String, Integer>** | Búsqueda O(1), API clara (`get`, `put`, `containsKey`) | Ligeramente más abstracto |

### Por Qué HashMap Es La Mejor Opción

```java
// Patrón clásico: "acumular por clave"
Map<String, Integer> mapa = new HashMap<>();

for (Registro r : registros) {
    String clave = r.getClave();        // ej: "P125", "C002", "E01"
    int valor = r.getValor();           // ej: cantidad, o 1 para contar

    if (mapa.containsKey(clave)) {
        mapa.put(clave, mapa.get(clave) + valor);  // sumar al existente
    } else {
        mapa.put(clave, valor);                    // primera vez
    }
}
```

**Razones pedagógicas y prácticas:**

1. **Complejidad O(1) promedio** — `get/put/containsKey` son hash directos, no recorridos lineales
2. **Semántica clara** — "mapa de clave → acumulador" es el patrón canónico de agrupación
3. **Extensible** — si mañana piden "también mostrar el total por cliente", solo agregas otro mapa
4. **Estándar de la industria** — `HashMap` es la estructura base para conteos y sumas por clave en Java
5. **Enseña conceptos clave**:
   - Clave-valor (asociación)
   - Hashing (cómo se guarda internamente)
   - Colisiones (implícito, pero se menciona)
   - `Map.Entry` para recorrer resultados

### Recorrer el HashMap para Encontrar el Máximo

```java
String claveMax = null;
int valorMax = -1;

for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
    String clave = entry.getKey();
    int valor = entry.getValue();
    if (valor > valorMax) {
        valorMax = valor;
        claveMax = clave;
    }
}
```

- `entrySet()` devuelve pares `clave-valor` iterables
- Comparación simple `>` para encontrar el máximo
- Sin streams, sin `Optional`, sin lambdas — solo bucles e `if`

---

## Conclusión

| Tema | Qué Se Demuestra |
|------|------------------|
| **Estimación de anchos** | Análisis de datos de ejemplo + aritmética simple + validación cruzada |
| **RandomAccessFile** | Lectura por posiciones fijas (`seek`, `readFully`, `substring`) |
| **HashMap** | Agrupación eficiente por clave, patrón "mapa acumulador", recorrido de `entrySet` |

> **Para el examen:** Saber deducir anchos de campo de archivos fixed-width y usar `HashMap` para reportes de agregación son habilidades fundamentales de procesamiento de datos en Java.