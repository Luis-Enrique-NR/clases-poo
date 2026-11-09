# Propuesta: Sistema de solicitud y gestión de créditos vehiculares

## Nombre del Cambio
Sistema de solicitud y gestión de créditos vehiculares usando Random Access File

## Propósito
Simular un proceso transaccional completo para solicitar, aprobar/rechazar y consultar créditos vehiculares. El sistema utiliza un archivo `.dat` con registros fijos (40 bytes) para persistencia, permitiendo acceso aleatorio a los registros por medio de offsets calculados.

## Alcance
1. **Perspectiva Solicitante (cliente):** 
   - Solicitar nuevo crédito vehicular (dni, placa, monto)
   - Consultar estado y detalles de propia solicitud
   - Listar historial propio de solicitudes

2. **Perspectiva Administrador (revisor):**
   - Aprobar o rechazar solicitudes pendientes
   - Consultar historial general de todas las solicitudes
   - Consultar detalles de cualquier solicitud

3. **Almacenamiento:**
   - Archivo `creditos.dat` con registros fijos de 40 bytes
   - Cada registro contiene: DNI, placa, código crédito, monto, estado, fecha epoch
   - Sin base de datos externa; todo en un solo archivo binario

## Enfoque Técnico
- Usar `RandomAccessFile` para lectura/escritura directa por posición
- Cálculo de offset: `posicionRegistro = indice * 40 bytes`
- Formato de datos fijo para localización matemática
- Estados: 0=PENDIENTE, 1=APROBADO, 2=RECHAZADO, 3=PAGADO

## Limitaciones (conscientes)
- Sin base de datos relacional (solo archivo plano)
- Sin concurrencia real (simulación single-thread)
- Validaciones básicas solo de formato (no valida DNI real, placas únicas, etc.)
- Simulación de transacciones sin persistencia ACID real