# Explicación Teórica: Pregunta 2 — Herencia vs Clase Abstracta vs Interfaz

## Contexto del Examen

La **Pregunta 2** pide implementar la **misma jerarquía de clases** con **tres enfoques distintos**, demostrando **equivalencia funcional** (mismo comportamiento observable) pero con **mecánicas diferentes**.

```
Equipo_Computo (padre/base)
├── Monitor (hijo/implementación)
└── Impresora (hijo/implementación)
```

Cada enfoque produce 4 archivos `.java` y un `Test_*.java` que demuestra polimorfismo.

---

## 1. Herencia Simple (`extends`) — Bloque **Inherit**

### Concepto Central
> **"Un Monitor ES UN Equipo_Computo"** — relación **IS-A** estricta.

### Características Clave

| Aspecto | Detalle |
|---------|---------|
| Palabra clave | `extends` |
| Clase padre | **Concreta** (se puede instanciar) |
| Métodos | Pueden ser concretos o sobrescritos (`@Override`) |
| Atributos | Heredados directamente (`protected`/`public`) |
| Constructor | `super(...)` obligatorio en hijos si padre no tiene constructor por defecto |

### Código Representativo

```java
// Padre CONCRETO
public class Inherit_Equipo_Computo {
    protected String marca, modelo, numeroSerie;
    protected double precio;

    public Inherit_Equipo_Computo(String marca, String modelo, double precio, String numeroSerie) {
        this.marca = marca; this.modelo = modelo; this.precio = precio; this.numeroSerie = numeroSerie;
    }

    public void mostrarInfo() {  // Método CONCRETO con implementación
        System.out.println("Marca: " + marca + "...");
    }
}

// Hijo
public class Inherit_Monitor extends Inherit_Equipo_Computo {
    private double pulgadas;
    private String resolucion, tipoPanel;

    public Inherit_Monitor(..., double pulgadas, String resolucion, String tipoPanel) {
        super(marca, modelo, precio, numeroSerie);  // LLAMADA A SUPER
        this.pulgadas = pulgadas; ...
    }

    @Override
    public void mostrarInfo() {  // SOBRESCRITURA
        super.mostrarInfo();     // Reutiliza lógica del padre
        System.out.println("Pulgadas: " + pulgadas + "...");
    }
}
```

### Cuándo Usar
- Hay una **jerarquía natural** con comportamiento compartido real.
- La clase base **tiene sentido instanciarla por sí sola**.
- Quieres **reutilizar código** (atributos + métodos) directamente.

### Limitación
- **Una sola herencia** en Java (`extends` una sola clase).
- Acoplamiento fuerte: cambios en padre afectan a todos los hijos.

---

## 2. Clase Abstracta (`abstract class`) — Bloque **Abstract**

### Concepto Central
> **"Equipo_Computo define QUÉ debe hacer un equipo, pero NO CÓMO lo hace completamente"** — clase **incompleta a propósito**.

### Características Clave

| Aspecto | Detalle |
|---------|---------|
| Palabra clave | `abstract class` |
| Instanciación | **NO se puede instanciar** (`new Abstract_Equipo_Computo()` → error) |
| Métodos abstractos | `abstract void mostrarInfo();` — **obligan** a implementar en hijos |
| Métodos concretos | Pueden tener implementación compartida (`mostrarInfoBase()`) |
| Atributos | Igual que herencia normal (`protected`) |

### Código Representativo

```java
// Padre ABSTRACTO
public abstract class Abstract_Equipo_Computo {
    protected String marca, modelo, numeroSerie;
    protected double precio;

    public Abstract_Equipo_Computo(String marca, String modelo, double precio, String numeroSerie) {
        this.marca = marca; this.modelo = modelo; this.precio = precio; this.numeroSerie = numeroSerie;
    }

    // Método ABSTRACTO: SIN CUERPO, obliga a implementar
    public abstract void mostrarInfo();

    // Método CONCRETO compartido (template method pattern)
    protected void mostrarInfoBase() {
        System.out.println("=== EQUIPO DE CÓMPUTO ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Precio: S/ " + precio);
        System.out.println("N° Serie: " + numeroSerie);
    }
}

// Hijo DEBE implementar mostrarInfo()
public class Abstract_Monitor extends Abstract_Equipo_Computo {
    // ... atributos y constructor con super(...) ...

    @Override
    public void mostrarInfo() {  // IMPLEMENTACIÓN OBLIGATORIA
        mostrarInfoBase();       // Usa método compartido del padre
        System.out.println("--- MONITOR ---");
        System.out.println("Pulgadas: " + pulgadas + "...");
    }
}
```

### Cuándo Usar
- La clase base **no tiene sentido instanciarla sola** (¿qué es un "Equipo_Computo" genérico?).
- Quieres **forzar un contrato** (métodos abstractos) pero **compartir código común**.
- Necesitas **estado** (atributos) en la base + comportamiento parcial.

### Diferencia Clave con Herencia Simple
| Herencia Simple | Clase Abstracta |
|-----------------|-----------------|
| Padre instanciable | Padre **NO** instanciable |
| Métodos opcionales de sobrescribir | Métodos abstractos **obligatorios** |
| "Es un" con implementación completa | "Es un" con **huecos por rellenar** |

---

## 3. Interfaz (`interface`) — Bloque **Interface**

### Concepto Central
> **"Un Monitor CUMPLE el contrato de Equipo_Computo"** — relación **CAN-DO / CONTRATO**, no necesariamente IS-A.

### Características Clave

| Aspecto | Detalle |
|---------|---------|
| Palabra clave | `interface` + `implements` |
| Instanciación | **NO** (es solo contrato) |
| Métodos | **Todos abstractos implícitamente** (Java 7-) / `default`/`static` (Java 8+) |
| Atributos | Solo `static final` (constantes) — **NO estado de instancia** |
| Herencia múltiple | **SÍ** — una clase `implements` varias interfaces |

### Código Representativo

```java
// CONTRATO puro
public interface Interface_Equipo_Computo {
    // Constantes (implícitamente public static final)
    String TIPO_BASE = "Equipo de Cómputo";

    // Métodos abstractos (implícitamente public abstract)
    void mostrarInfo();
    String getMarca(); void setMarca(String marca);
    String getModelo(); void setModelo(String modelo);
    double getPrecio(); void setPrecio(double precio);
    String getNumeroSerie(); void setNumeroSerie(String numeroSerie);

    // Default method (Java 8+) — implementación COMPARTIDA opcional
    default void mostrarInfoBase() {
        System.out.println("=== " + TIPO_BASE + " ===");
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Precio: S/ " + getPrecio());
        System.out.println("N° Serie: " + getNumeroSerie());
    }
}

// IMPLEMENTACIÓN — cada clase GESTIONA SU PROPIO ESTADO
public class Interface_Monitor implements Interface_Equipo_Computo {
    // ESTADO PROPIO (no heredado)
    private String marca, modelo, numeroSerie;
    private double precio;
    private double pulgadas;
    private String resolucion, tipoPanel;

    public Interface_Monitor(String marca, String modelo, double precio, String numeroSerie,
                             double pulgadas, String resolucion, String tipoPanel) {
        this.marca = marca; this.modelo = modelo; this.precio = precio; this.numeroSerie = numeroSerie;
        this.pulgadas = pulgadas; this.resolucion = resolucion; this.tipoPanel = tipoPanel;
    }

    // IMPLEMENTAR TODOS LOS MÉTODOS DEL CONTRATO
    @Override public String getMarca() { return marca; }
    @Override public void setMarca(String marca) { this.marca = marca; }
    // ... resto de getters/setters ...

    @Override
    public void mostrarInfo() {
        mostrarInfoBase();  // Usa default method de la interfaz
        System.out.println("--- MONITOR ---");
        System.out.println("Pulgadas: " + pulgadas + "...");
    }
}
```

### Cuándo Usar
- Definir **capacidades/contratos** que clases no relacionadas pueden cumplir.
- Necesitas **herencia múltiple de tipo** (ej. `Monitor implements Equipo_Computo, Vendible, Reparable`).
- No hay estado compartido real, solo **comportamiento esperado**.
- Diseño **basado en composición** (preferir interfaces sobre herencia).

### Limitación
- **Sin estado de instancia** (cada implementación repite atributos + getters/setters).
- Más verboso (boilerplate) para jerarquías simples.

---

## Tabla Comparativa Resumen

| Característica | `extends` (Herencia) | `abstract class` | `interface` |
|----------------|----------------------|------------------|-------------|
| **Relación** | IS-A fuerte | IS-A con huecos | CAN-DO / Contrato |
| **Instancia padre** | ✅ Sí | ❌ No | ❌ No |
| **Estado (atributos)** | Heredado (`protected`) | Heredado (`protected`) | Solo `static final` |
| **Métodos concretos** | ✅ Libres | ✅ Libres | Solo `default`/`static` (Java 8+) |
| **Métodos abstractos** | ❌ No | ✅ Obligatorios | ✅ Todos (implícitos) |
| **Herencia múltiple** | ❌ Una sola | ❌ Una sola | ✅ Múltiples `implements` |
| **Constructores** | `super(...)` | `super(...)` | ❌ No |
| **Polimorfismo** | ✅ Por clase base | ✅ Por clase base | ✅ Por tipo interfaz |
| **Acoplamiento** | Alto (código + tipo) | Medio (tipo + algo de código) | Bajo (solo tipo) |

---

## En el Examen: Qué Demuestra Cada Bloque

### Bloque `Inherit` (Herencia Simple)
```java
Inherit_Equipo_Computo eq = new Inherit_Monitor(...);  // Polimorfismo
eq.mostrarInfo();  // Llama a Monitor.mostrarInfo()
```
- Demuestra: **herencia de implementación**, `super()`, `@Override`, polimorfismo básico.

### Bloque `Abstract` (Clase Abstracta)
```java
Abstract_Equipo_Computo eq = new Abstract_Monitor(...);
// Abstract_Equipo_Computo x = new Abstract_Equipo_Computo(); // ERROR COMPILACIÓN
eq.mostrarInfo();
```
- Demuestra: **clase no instanciable**, método `abstract` obligatorio, método `protected` compartido (`mostrarInfoBase()`), template method.

### Bloque `Interface` (Interfaz)
```java
Interface_Equipo_Computo eq = new Interface_Monitor(...);
eq.mostrarInfo();  // Polimorfismo por interfaz
// eq.mostrarInfoBase(); // También accesible (default method)
```
- Demuestra: **contrato puro**, `implements`, **estado propio en cada clase**, `default method` para código compartido, herencia múltiple posible.

---

## Conclusión Pedagógica

| Enfoque | Qué Evalúa el Profesor |
|---------|------------------------|
| **Inherit** | ¿Entiende herencia básica, `super`, `@Override`, polimorfismo por clase? |
| **Abstract** | ¿Sabe cuándo NO instanciar padre, fuerza implementación con `abstract`, comparte código con métodos `protected`? |
| **Interface** | ¿Entiende contrato vs implementación, `implements`, getters/setters obligatorios, `default method`, herencia múltiple? |

> **Los tres producen la MISMA SALIDA en consola** — eso es la "equivalencia funcional". La diferencia está en **CÓMO se logra** esa estructura y qué restricciones/ventajas trae cada una.

---

## Referencias Rápidas (Java 17)

- **Herencia**: `extends`, `super`, `@Override`, `protected`
- **Abstract**: `abstract class`, `abstract method`, no instanciable, constructores
- **Interface**: `interface`, `implements`, `default`, `static`, `public abstract` implícito, herencia múltiple
- **Polimorfismo**: Variable de tipo base/interface referenciando objeto hijo/implementación