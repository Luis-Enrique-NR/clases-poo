# Tasks: login-ip-verif-tradicional

## Review Workload Forecast

| Field | Value |
|-------|-------|
| Estimated changed lines | 150-170 (backend solo, sin JFrame) |
| 400-line budget risk | Low |
| Chained PRs recommended | No |
| Suggested split | Single PR |
| Delivery strategy | ask-on-risk |
| Chain strategy | pending |

Decision needed before apply: Yes
Chained PRs recommended: No
Chain strategy: pending
400-line budget risk: Low

### Suggested Work Units

| Unit | Goal | Likely PR | Focused test command | Runtime harness | Rollback boundary |
|------|------|-----------|----------------------|-----------------|-------------------|
| 1 | Validación tradicional + wiring + build OK | PR 1 | `mvn compile` en `login-ip-verif` | `mvn exec:java` abre LoginFrame, login manual vs `Usuarios.dat` | `git revert`: borra `LoginValidator.java`, restaura `LoginFrame`/`AuthService`/`pom.xml` |

Restricciones apply: SIN regex (`matches/Pattern/Matcher/split` en validación), SIN lambdas/streams/method-refs en validación, tradicional explicativo, delivery=ask-on-risk, budget=800.

## Phase 1: Precondiciones build — scope: AGENTE

- [x] 1.1 Fijar `login-ip-verif/pom.xml`: `release` 26→24 y `mainClass` → `com.swing.login.project.LoginFrame`
- [x] 1.2 Verificar `mvn compile` en `Semana 1 - 2/login-ip-verif` compila sin errores

## Phase 2: LoginValidator tradicional (nuevo) — scope: AGENTE

- [x] 2.1 Crear `project/LoginValidator.java` final con constantes `MIN/MAX_USER 3/20`, `MIN/MAX_PASS 4/30`, `FORBIDDEN="*´°+|/\r\n"` (sin `-._`)
- [x] 2.2 Implementar `esLetraAscii` + `esDigitoAscii` con `for`+`charAt`+`if/else` (solo ASCII)
- [x] 2.3 Implementar `esCaracterUsuarioPermitido` (letra/dígito/`.`/`_`/`-`) y `contieneProhibido` (recorre `FORBIDDEN` con `==`)
- [x] 2.4 Implementar `esUsuarioValido` (null→false, longitud 3-20, bucle por char) y `esPasswordValida` (null→false, 4-30 cruda sin trim, rechaza `|`/`\r`/`\n`)

## Phase 3: Wiring + guard

- [ ] 3.1 [SCOPE: USUARIO — no agente] En `LoginFrame.AceptarButtonActionPerformed`: trim solo usuario, validar `esUsuarioValido` → `JOptionPane` usuario y abortar antes de `AuthService`. Ver receta en `JFrame-req.md`.
- [ ] 3.2 [SCOPE: USUARIO — no agente] Misma acción: validar `esPasswordValida` con clave cruda → `JOptionPane` clave y abortar; quitar `.trim()` de password. Ver receta en `JFrame-req.md`.
- [x] 3.3 [SCOPE: AGENTE] En `AuthService.validarCredenciales`: guard inicial `if (!esUsuarioValido || !esPasswordValida) return false;` sin imports UI

## Phase 4: Verificación

- [x] 4.1 [SCOPE: AGENTE] Compilar `mvn compile` y arrancar `mvn exec:java` (abre `LoginFrame`)
- [ ] 4.2 [SCOPE: MANUAL COMPARTIDA — usuario + agente] Prueba manual: `ana-19.x_y` acepta; `--`, `a|b`, `x\ny` rechazan; clave `|`/`\n` rechaza, válida llega a `AuthService`
- [x] 4.3 [SCOPE: AGENTE] Grep path validación (`matches|Pattern|Matcher|split|->|::|stream`) → cero hits; `split("\\|")` legacy intacto fuera del path
