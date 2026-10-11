## Exploration: Traditional special-character validation for login-ip-verif (no regex)

### Current State
- Module `Semana 1 - 2/login-ip-verif` (copy of `login-clean-dat`, Java Swing, `com.swing.login.project`) has NO regex-based validation today: no `String.matches`, no `Pattern`/`Matcher` anywhere in `src` (verified by grep). The only regex-adjacent call is `String.split("\\|")` on a literal pipe in `UsuarioRepositoryTxt` and `CrearDat`.
- Login flow: `LoginFrame.AceptarButtonActionPerformed` trims user + password, rejects empty, calls `AuthService.validarCredenciales(user, pass)` (exact password `equals` + `isActivo` flag), then `LogService.registrarIntento(user, esValido)` appends to `LOG.TXT`.
- `AuthService` is injected with `UsuarioRepositoryDat` (comment says TXT, code uses Dat). `UsuarioRepositoryDat` reads fixed 130-byte records via `RandomAccessFile`, skips logical-delete mark `'*'` at pos 0, extracts user `substring(53,68)`, password `substring(69,79)`, state `charAt(80)=='1'`.
- There is NO username/password shape validation: any non-empty string reaches the repository. Forbidden characters (`*`, `´`, `°`, `+`, `-`, `|`, `\r`, `\n`, `/`, `\`) are not rejected; `*` collides with the delete mark, `|` collides with the TXT delimiter, `/\` enable path confusion, `\r\n` enable LOG.TXT line injection.
- Intended rules for the change: username 3-20 chars (letters, digits, `.`, `_`, `-`); password 4-30 chars without `|` or line breaks. Didactic constraint: `for`, `charAt`, `if/else`, named constants, small clearly-named methods; forbidden: `matches`/`Pattern`/`Matcher`, lambdas, streams, method refs anywhere in the validation path and specs.

### Affected Areas
- `src/main/java/com/swing/login/project/LoginFrame.java` — `AceptarButtonActionPerformed` is the insertion point: validate BEFORE `AuthService`; needs per-field error messages. Note it uses a method ref (`this::AceptarButtonActionPerformed`) and a lambda in `main` — outside the validation path, leave untouched.
- `src/main/java/com/swing/login/project/AuthService.java` — add defense-in-depth guard (reject invalid shape → `false`) so the rule does not depend on Swing; keep pure business logic, no UI imports.
- `src/main/java/com/swing/login/project/UsuarioRepositoryTxt.java` — `split("\\|")` is regex-based splitting; out of validation-path scope but flag for a future traditional split; trims fields.
- `src/main/java/com/swing/login/project/UsuarioRepositoryDat.java` — fixed-offset parsing; trims substrings; owns the RAF leak (below).
- `src/main/java/com/swing/login/project/LogService.java` — appends `usuario` raw into `LOG.TXT`; needs newline/`|` sanitization once validation exists (defense in depth, keep raw username OUT of the log or sanitize).
- `src/main/java/com/swing/login/project/CrearDat.java` — migration tool; `split` + trims; password field is 10 chars in DAT vs proposed 4-30 rule — document truncation mismatch.
- `pom.xml` / `nbactions.xml` — build/run mismatch (see Risks); no test runner, `src/test` empty.

### Approaches
1. **Allowlist with for + if/else + small named predicates (RECOMMENDED)** — new `LoginValidator` final class (private constructor, all static): constants `MIN_USER=3, MAX_USER=20, MIN_PASS=4, MAX_PASS=30`; public `esUsuarioValido(String)`, `esPasswordValida(String)`; private `esLetraAscii(char)`, `esDigitoAscii(char)`, `esCaracterUsuarioPermitido(char)` (explicit `if (c == '.') return true;` chain, NO switch fall-through tricks), `contieneProhibido(String)` looping a `FORBIDDEN = "*´°+|/\r\n"` constant with `charAt` + `==`. Null-safe (null → false), length checked first, one loop per rule.
   - Pros: most explainable for students; each rule reads as a sentence; zero regex/functional surface; easy to unit-test without Swing; O(n) with early exit.
   - Cons: more lines than a table; must keep the three method layers disciplined (public rule → private char predicate).
   - Effort: Low.
2. **Blacklist-only via `String.indexOf` loop** — single `FORBIDDEN` string; for each input char, `if (FORBIDDEN.indexOf(c) >= 0) return false;` plus length checks.
   - Pros: shortest code; intuitive "banned list" mental model; no regex.
   - Cons: blacklist alone cannot ENFORCE the username allowlist (letters/digits/`.-_`); students may think blocking 10 chars is sufficient; `indexOf` hides the loop but is still O(n·m); two concepts (allowlist + blacklist) end up mixed anyway.
   - Effort: Low.
3. **Boolean lookup table `boolean[65536]` in a static initializer** — precompute allowed/forbidden sets, then one `for` with `if (!ALLOWED[c]) return false;`.
   - Pros: fastest O(n), single array hit per char; elegant for large alphabets.
   - Cons: least didactic — magic 65536, static-init ordering, memory for a 9-field login form; obscures the pedagogical `if/else` mapping the teacher wants; over-engineering.
   - Effort: Medium.

### Recommendation
Adopt **Approach 1**: new `LoginValidator` (pure, Swing-free) + call it first in `LoginFrame.AceptarButtonActionPerformed` with distinct messages per field ("Usuario 3-20: letras, dígitos, punto, guion bajo, guion"), plus a guard clause at the top of `AuthService.validarCredenciales` returning `false` on invalid shape (defense in depth, no UI dependency). Resolve the `-` contradiction explicitly: **username whitelist wins** — `-` is ALLOWED in usernames; the forbidden list applies to the password/log/delimiter context (`* ´ ° + | / \ \r \n`) and MUST exclude `-`, `.`, `_` (specs must state this). Do NOT trim the password before length check (trim only for emptiness of username); document `split("\\|")` as accepted legacy outside the validation path.

### Risks
- **Build broken regardless of this change**: `pom.xml` declares `maven.compiler.release=26` but environment JDK is 24.0.1 → `mvn compile` fails ("release version 26 not supported"). Fix: set release to 24 (or 17/21 per classroom standard) in the change; verify with `mvn -pl "Semana 1 - 2/login-ip-verif" compile`.
- **Wrong mainClass in pom**: `exec.mainClass=com.swing.login.project.LoginProject` does not exist (no such file; real entry is `LoginFrame.main`, confirmed by `nbactions.xml`). `mvn exec:java` via pom fails; NetBeans Run works via override. Fix or document the canonical run command.
- **RAF leak in `UsuarioRepositoryDat.buscarPorUsername`**: `RandomAccessFile` opened without try-with-resources; `RAF.close()` is inside `try`, not `finally` — exception before close leaks the handle; `RAF.read(BUFFER)` return value ignored (short read possible). Fix with try-with-resources in the same change or a follow-up.
- **LOG.TXT injection + naming**: `LogService` writes raw `usuario` with relative path `LOG.TXT` at module working dir; `\r\n` or `|` in a username forges log lines today. Validation fixes the input side; consider sanitizing at log time too. No `intentos_fallidos.txt` exists anywhere (grep over `Semana 1 - 2` finds only `LOG.TXT`) — if the course requires that file/counter/lockout, it is missing scope, not a rename.
- **Password `.trim()` destroys data**: `LoginFrame` trims the password, so leading/trailing spaces are silently stripped before length/auth checks; DAT passwords are space-padded fixed fields and also trimmed on read. Decide: username → trim + validate; password → do NOT trim, validate raw (4-30, no `|`/line breaks). Changing this alters auth for space-padded legacy rows — test against `Usuarios.dat`.
- **DAT width vs new rule**: `CrearDat`/DAT password slot is 10 chars (`substring(69,79)`), but the proposed password rule allows up to 30 — longer passwords would be truncated at migration. Either widen the record (breaking the professor's offsets) or cap passwords at 10 while DAT is the store; record the decision in the proposal.

### Ready for Proposal
Yes — scope is clear and code was read. Proposal should: (a) create `LoginValidator` per Approach 1 with the `-` precedence rule stated; (b) wire it into `LoginFrame` + `AuthService` guard; (c) fix `release` 26→24 and `mainClass` as preconditions; (d) decide password trim + DAT-10-vs-30 policy; (e) defer RAF try-with-resources and log sanitization as explicit follow-ups or same-change stretch goals.
