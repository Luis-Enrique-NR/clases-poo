# Proposal: login-ip-verif-tradicional

## Intent
Cerrar inyección de `* | \r \n / \` en login DAT/TXT/LOG sin regex: validar forma usuario/clave con bucles `for` + `charAt` + `if/else` didácticos antes de `AuthService`.

## Scope
### In Scope
- Nueva clase final `LoginValidator` (estática pura, sin Swing): `esUsuarioValido`, `esPasswordValida`, `esLetraAscii`, `esDigitoAscii`, `esCaracterUsuarioPermitido`, `contieneProhibido`; constantes `MIN/MAX_USER 3/20`, `MIN/MAX_PASS 4/30`, `FORBIDDEN="*´°+|/\r\n"`.
- Wiring en `LoginFrame.AceptarButtonActionPerformed`: validar antes de `AuthService`, mensajes por campo.
- Guard en `AuthService.validarCredenciales`: forma inválida → `false` (defensa en profundidad).
- Precondiciones build: `pom.xml` release `26→24`, `mainClass` → `LoginFrame`.
### Out of Scope
- Refactor completo RAF (try-with-resources), lockout/contador `intentos_fallidos.txt`, cambio formato DAT (ancho 130/offsets), sanitización en `LogService`, migración `split("\\|")` legacy.

## Capabilities
### New Capabilities
- `login-validation`: reglas forma usuario 3-20 (letras ASCII, dígitos, `.` `_` `-`) y clave 4-30 sin `|` ni saltos, sin regex.
### Modified Capabilities
- None

## Approach
Allowlist `for`+`charAt`+`if/else`: longitud primero, luego un bucle por regla con salida temprana. Usuario: cada char pasa `esCaracterUsuarioPermitido` (cadena explícita de `if (c == '.') return true;`). Clave: longitud + `contieneProhibido` sobre `FORBIDDEN` (sin `-._`). `LoginFrame` valida y muestra error; `AuthService` repite guard sin UI.

## Affected Areas
| Area | Impact | Description |
|------|--------|-------------|
| `.../project/LoginValidator.java` | New | Clase final validación tradicional |
| `.../project/LoginFrame.java` | Modified | Llamar validador antes de `AuthService` |
| `.../project/AuthService.java` | Modified | Guard forma inválida → `false` |
| `login-ip-verif/pom.xml` | Modified | release 24, mainClass `LoginFrame` |

## Tradeoffs
- Allowlist verbosa vs blacklist corta: se elige verbosidad didáctica.
- `indexOf`/tabla `boolean[65536]` rechazadas: ocultan el `if/else` pedagógico.
- `split("\\|")` legacy aceptado fuera del path de validación.

## Risks
| Risk | Likelihood | Mitigation |
|------|------------|------------|
| Build roto release 26 vs JDK 24 | High | Fijar release 24 en este change |
| mainClass inexistente | High | Apuntar a `LoginFrame` |
| Password `.trim()` destruye espacios | Med | Clave NO trim; usuario sí trim |
| DAT 10 vs regla 30 (truncado `CrearDat`) | Med | Cap documentado: claves >10 se truncan; no widen sin profesor |
| RAF leak / LOG injection | Low | Deferidos explícitos, no bloquean |

## Rollback Plan
`git revert` del commit del change; borra `LoginValidator.java`, restaura `LoginFrame`/`AuthService`/`pom.xml`. Sin migración de datos: DAT/LOG intactos.

## Dependencies
- JDK 24 + Maven; `Usuarios.dat` existente para prueba manual; `nbactions.xml` como referencia run.

## Success Criteria
- [ ] `mvn compile` OK con release 24 y `exec:java` arranca `LoginFrame`
- [ ] Usuario `--`, `a|b`, `x\ny` rechazados; `ana-19.x_y` aceptado
- [ ] Clave con `|`/`\n` rechazada; clave válida llega a `AuthService`
- [ ] Cero `matches/Pattern/Matcher/split`/lambdas/streams/method-refs en path validación

## Restricciones duras
SIN regex (`matches/Pattern/Matcher/split` en validación); SIN lambdas/streams/method refs en validación y specs; bucles e `if/else` explicativos; comentarios español didáctico; código técnico inglés.

## Decisiones a registrar
- `-` whitelist gana sobre blacklist; `FORBIDDEN` excluye `-._`.
- Password NO trim; usuario sí trim + validar.
- DAT 10 vs 30 cap aceptado (truncado documentado).
- `split` legacy aceptado fuera del path.
