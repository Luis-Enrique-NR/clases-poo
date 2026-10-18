# GUIA EXAMEN PARCIAL PASADO

> Instrucciones del profesor de preguntas pasadas

## Pregunta 2

**HERENCIA Y POLIMORFISMO**          **(6 puntos)**

2.   Dada la siguiente jerarquía de 2 niveles

| **Equipo_Computo** |
| :---: |
| ┌──────┴──────┐ |
| **Monitor** **Impresora** |

| Inherit_Equipo_Computo.java | Abstract_Equipo_Computo.java | Interface_Equipo_Computo.java |
| Inherit_Monitor.java | Abstract_Monitor.java | Interface_Monitor.java |
| Inherit_Impresora.java | Abstract_Impresora.java | Interface_Impresora.java |
| Test_Inherit.java | Test_Abstract.java | Test_Interface.java |

Implementar los bloques (*que contienen 4 clases java*) con equivalencia funcional entre si (*usando <u>Herencia</u>, <u>Clases Abstractas</u> e <u>Interfaces</u>*). Esto significa que cada bloque (*Inherit, Abstract, Interface*) debe realizar la misma funcionalidad u operación.


## Pregunta 3

### PROCESAMIENTO DE ARCHIVOS

3. Tomando como base los siguientes archivos (*implementados con organización secuencial o de acceso directo*)



| ARCHIVO | LONGITUD DE REGISTRO | CAMPOS DE INFORMACION |
| --- | --- | --- |
| • CLIENTES.DAT | 32 | (Id,RazonSocial,RUC) |
| • EMPLEADO.DAT | 19 | (Id,NomApe) |
| • CATEGORIAS.DAT | 33 | (Id,Descripcion) |
| • PRODUCTOS.DAT | 37 | (Id,Nombre,IdCategoria,Precio,IdMoneda) |
| • REGISTRO_VENTAS_CABECERA.DAT | 41 | (Id,Fecha,Hora,IdCliente,TipoPago,Monto,IdMoneda,IdEmpleado) |
| • REGISTRO_VENTAS_DETALLE.DAT | 15 | (Id,Secuencia,IdProducto,Cantidad) |

---

### CLIENTES.DAT



```text
C001 Pedro Torres        12345678901
C002 Imprenta ALFA      11223344556
C003 Jose Alvarado      20504060809
C004 Farmacia BETA      99988877766
C005 Alejandro Diaz     10020030012
C006 Maria Zavaleta     65488812399
C007 Jorge Lozano       44400033312
C008 Mecanica SIGMA     99911100085

```

### EMPLEADO.DAT



```text
E01 Luis Arias
E02 Eva Calderon
E03 Rosa Campos
E04 Juan Soto

```

### PRODUCTOS.DAT



```text
P125 Memoria USB 8Gb        3   70.00 2
P248 Disco Externo 1 TB     3  500.00 2
P324 Impresora Laser 2400   3 1000.00 2
P405 Papel Bond A4 x 500    4    25.00 1
P510 Energizante 1 Lt       1    20.00 1
P542 Koca Kola  3 Lt        1    15.00 1
P630 Inka Kola  1 Lt        1     5.00 1
P678 Nectar Natura 2 Lt     1    12.00 1
P691 Nectar ABC 1 Lt        1     8.00 1
P750 Agua Mineral 5 Lt      1    12.00 1
P841 Cera en Pasta Clean    2    30.00 1
P887 Lejia CloroMAX 1 Lt    2    12.00 1
P905 Detergente CloroMAX    2     4.00 1
P957 Jabon Liquido Clean    2    25.00 1

```

---

### REGISTRO_VENTAS_CABECERA.DAT



```text
0001 20250519 091251 C001 1   60.00 1 E01
0002 20250519 103507 C002 2 1350.00 2 E02
0003 20250519 115528 C003 1  140.00 2 E03
0004 20250519 123042 C004 2  800.00 1 E04
0005 20250520 094508 C007 1   20.00 1 E01
0006 20250520 103532 C002 2  350.00 1 E02
0007 20250520 112941 C001 1  105.00 1 E03
0008 20250520 124252 C008 2  780.00 2 E04

```

### CATEGORIAS.DAT



```text
1 Bebidas y Gaseosas
2 Articulos de Limpieza
3 Equipos y Accesorios de Computo
4 Accesorios de Oficina

```

### REGISTRO_VENTAS_DETALLE.DAT



```text
0001 01 P750  2
0001 02 P691  2
0001 03 P630  4
0002 01 P324  1
0002 02 P125  5
0003 01 P125  2
0004 01 P405 10
0004 02 P125  4
0004 03 P957  4
0004 04 P750  5
0004 05 P542  2
0004 06 P510  4
0005 01 P750  1
0005 01 P691  1
0006 01 P405  4
0006 02 P125  3
0006 03 P510  2
0007 01 P905  5
0007 02 P887  2
0007 03 P691  3
0007 04 P678  1
0007 05 P630  5
0008 01 P248  2
0008 02 P125  4

```

Determinar:

* El Producto con mayor cantidad de ventas


* El Cliente con mas frecuencia


* El Empleado con mayor cantidad de operaciones de venta



---

**SUGERENCIA: Dependiendo la organización de los archivos (*acceso secuencial o acceso directo*), DEBE ESCOGER SOLO UNA de las siguientes plantillas de lectura**

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Modelo_FileReader {
//-----------------------------------------------------
public static void Procesar_ArchivosSecuenciales() {
    FileReader FR;
    BufferedReader BR;
    String Linea, DataFile = "Archivo.DAT";
    try {
        FR = new FileReader(DataFile);
        BR = new BufferedReader(FR);
        while ((Linea = BR.readLine()) != null) {
            System.out.println(Linea);
        }
        BR.close();
    }
    catch (IOException E) {
        System.out.println("ERROR: " + E.getMessage());
    }
}
//-----------------------------------------------------
public static void main(String[] args) {
    Procesar_ArchivosSecuenciales();
}
//-----------------------------------------------------
}

```



```java
import java.io.RandomAccessFile;
import java.io.IOException;

public class Modelo_RandomAccessFile {
    private static int W = 52;
    private static byte[] BUFFER = new byte[W];
//-----------------------------------------------------
    public static String BufferToString() {
        String CADENA = "";
        int L = BUFFER.length;
        for (int i = 0; i <= L - 1; i++) {
            CADENA = CADENA + (char) BUFFER[i];
        }
        return CADENA;
    }
//-----------------------------------------------------
    public static void Procesar_ArchivosDirectos() throws
                                IOException, InterruptedException {
        RandomAccessFile RAF = new RandomAccessFile("Archivo.DAT", "r");
        String Linea;
        long N, T;
        int L;
        T = RAF.length();
        N = T / W;
        for (int i = 1; i <= N; i++) {
            RAF.seek((i - 1) * W);
            RAF.read(BUFFER);
            Linea = BufferToString().trim();
            L = Linea.length();
            System.out.println(Linea);
        }
        RAF.close();
    }
//-----------------------------------------------------
    public static void main(String[] args) throws
                                IOException, InterruptedException {
        Procesar_ArchivosDirectos();
    }
//-----------------------------------------------------
}
