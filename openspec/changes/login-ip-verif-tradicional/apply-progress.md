# Apply progress: login-ip-verif-tradicional (backend sin JFrame)

Work unit: `backend-sin-jframe` — request-id `backend-apply-001`.
Scope: SOLO backend. `LoginFrame.java`, `*.form`, `nbactions` NO tocados (solo lectura).
Modo: Standard (strict_tdd=false). Sin tests nuevos; verificación con compile + grep + manual pendiente.

## Tareas completadas (agente)

- [x] 1.1 `pom.xml`: release 26→24, mainClass → `com.swing.login.project.LoginFrame`
- [x] 1.2 `mvn compile` en `Semana 1 - 2/login-ip-verif` → BUILD SUCCESS
- [x] 2.1–2.4 Nuevo `project/LoginValidator.java` (final, ctor privado, todo static, sin Swing)
- [x] 3.3 Guard en `AuthService.validarCredenciales` (forma inválida → false, sin imports UI)
- [x] 4.1 `mvn compile` OK (ver abajo); `exec:java` queda manual (requiere GUI)
- [x] 4.3 Grep path validación → cero hits; `split("\\|")` legacy intacto fuera del path

## Pendiente (usuario / manual compartida)

- [ ] 3.1 [USUARIO] Wiring usuario en `LoginFrame` — receta en `JFrame-req.md`
- [ ] 3.2 [USUARIO] Wiring clave en `LoginFrame` (quitar `.trim()` de pass) — receta en `JFrame-req.md`
- [ ] 4.2 [MANUAL COMPARTIDA] `exec:java` + login manual vs `Usuarios.dat`

## Work Unit Evidence

| Evidence | Value |
|----------|-------|
| Focused test command + result | `mvn compile` en `Semana 1 - 2/login-ip-verif` → `BUILD SUCCESS`, 9 fuentes, `javac release 24` |
| Grep validación + result | `Select-String` sobre `LoginValidator.java` + `AuthService.java` con `matches\|Pattern\|Matcher\|split\|->\|::\|stream\|filter\|map\|forEach\|javax.swing\|JOptionPane` → `ZERO-HITS-OK` |
| Runtime harness + result | N/A aquí (headless): `mvn exec:java` abre `LoginFrame` con GUI; queda para 4.2 manual con `Usuarios.dat` |
| Rollback boundary | Borrar `LoginValidator.java`; revertir 2 líneas `pom.xml` + guard 7 líneas `AuthService.java`; docs `JFrame-req.md` (×2) y marcas `tasks.md`. `LoginFrame.java`/`*.form`/`nbactions` intactos — nada que revertir ahí |

## Desviaciones del diseño

Ninguna en backend. Nota: `mvn -pl "Semana 1 - 2/login-ip-verif" compile` desde el repo root NO funciona
(no hay pom agregador); el comando válido es `mvn compile` dentro del módulo. El `tasks.md` se deja con
el comando por módulo.

## Archivos

- Creado: `Semana 1 - 2/login-ip-verif/src/main/java/com/swing/login/project/LoginValidator.java`
- Modificado: `Semana 1 - 2/login-ip-verif/pom.xml` (2 líneas)
- Modificado: `.../project/AuthService.java` (guard inicial, lógica equals/isActivo intacta)
- Creado: `openspec/changes/login-ip-verif-tradicional/JFrame-req.md` + copia en `Semana 1 - 2/login-ip-verif/JFrame-req.md`
- Actualizado: `openspec/changes/login-ip-verif-tradicional/tasks.md` (scopes + marcas)
