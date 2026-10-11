# Design: login-ip-verif-tradicional

## Technical Approach

Add a pure, Swing-free `final class LoginValidator` (private ctor, all `static`) that validates username/password shape with `for` + `charAt` + `if/else` only. `LoginFrame.AceptarButtonActionPerformed` trims username only, validates per field with distinct `JOptionPane` messages, and aborts before `AuthService`. `AuthService.validarCredenciales` repeats the same check as a UI-free guard returning `false` before touching the repository. `pom.xml` preconditions (`release 24`, `mainClass LoginFrame`) unblock the build. Maps to proposal allowlist approach and `login-validation` spec; no regex, lambdas, streams, or method refs in the validation path.

## Architecture Decisions

| Decision | Options considered | Tradeoff | Rationale |
|----------|-------------------|----------|-----------|
| Username whitelist wins over blacklist | Allowlist (letters/digits/`.-_`) vs blacklist-only (`indexOf` on `FORBIDDEN`) vs `boolean[65536]` table | Verbose but explicit vs short but insufficient vs fast but opaque | Allowlist enforces shape; blacklist alone cannot. Didactic `if/else` chain (`if (c == '.') return true;`) is the course goal. `FORBIDDEN` therefore excludes `-._`. |
| Password NOT trimmed, username trimmed | Trim both (current code) vs trim user only vs trim none | Trimming password silently destroys spaces; trimming user aids UX | DAT passwords are space-padded and compared after `trim()` on read; measuring raw length 4-30 preserves intent. Username `trim()` before validation avoids whitespace-only bypass. |
| DAT 10 vs rule 30 cap accepted | Widen DAT record vs cap passwords at 10 vs accept truncation | Widening breaks professor offsets (130/53-68/69-79/80); capping contradicts spec | Accept documented cap: passwords 11-30 pass shape validation but truncate at `CrearDat.padRight(...,10)` / `substring(69,79)`. No widen without professor approval. |
| Legacy `split("\\|")` accepted | Rewrite traditional split now vs defer | Rewrite touches migration tool outside login path; risk without tests | `split` lives in `UsuarioRepositoryTxt`/`CrearDat`, not the validation path. Spec forbids `split` only inside validation; legacy use stays, flagged as follow-up. |
| Pure static utility, no Swing | Instance bean vs static helper vs validation inside `AuthService` only | Static is less mockable but simplest; UI-free means testable without Swing | `LoginFrame` keeps UI (`JOptionPane`), `AuthService` keeps business rule (guard, no UI imports). Both call the same static methods; single source of truth. |

Implementation order inside each predicate: null check → length check → single `for` loop with early `return false`, `return true` at end.

## Data Flow

```
LoginFrame --trim(user), raw(pass)--> LoginValidator --true--> AuthService guard --true--> RepositoryDat
     |                 |                      | false                | false
     |                 |                JOptionPane(user)      return false
     |                 |                JOptionPane(pass)      (no repository hit)
```

Sequence: user types → `AceptarButtonActionPerformed` trims username only → `esUsuarioValido` fails → username `JOptionPane`, abort → else `esPasswordValida` fails → password `JOptionPane`, abort → else `authService.validarCredenciales` re-checks shape → `false` on invalid → else `buscarPorUsername` + `equals` + `isActivo`.

## File Changes

| File | Action | Description |
|------|--------|-------------|
| `.../project/LoginValidator.java` | Create | `final` class, private ctor, constants + 6 static methods, `for`/`charAt`/`if-else` only |
| `.../project/LoginFrame.java` | Modify | Trim user only; per-field validation + `JOptionPane` before `AuthService`; keep `this::`/`()->` lines untouched |
| `.../project/AuthService.java` | Modify | Top guard `if (!esUsuarioValido \|\| !esPasswordValida) return false;` no UI imports |
| `login-ip-verif/pom.xml` | Modify | `release 26→24`, `mainClass → com.swing.login.project.LoginFrame` |

## Interfaces / Contracts

```java
public final class LoginValidator {
    public static final int MIN_USER = 3;   // longitud minima del usuario
    public static final int MAX_USER = 20;  // longitud maxima del usuario
    public static final int MIN_PASS = 4;   // longitud minima de la clave
    public static final int MAX_PASS = 30;  // longitud maxima de la clave
    // Caracteres prohibidos en clave/log/delimitador (sin .-_ porque el usuario los permite)
    public static final String FORBIDDEN = "*´°+|/\r\n";

    public static boolean esUsuarioValido(String usuario);          // null->false, 3-20, cada char permitido
    public static boolean esPasswordValida(String clave);           // null->false, 4-30 cruda, sin | \r \n
    private static boolean esLetraAscii(char c);                    // (A-Z) o (a-z) con if/else
    private static boolean esDigitoAscii(char c);                   // ('0'-'9') con if/else
    private static boolean esCaracterUsuarioPermitido(char c);      // letra, digito, '.', '_', '-'
    private static boolean contieneProhibido(String texto);         // recorre FORBIDDEN con charAt + ==
}
```

`LoginFrame` contract: username trimmed then validated; password raw. `AuthService` contract: invalid shape → `false`, repository never called.

## Testing Strategy

| Layer | What to Test | Approach |
|-------|-------------|----------|
| Unit | `ana-19.x_y` true; `--`, `a\|b`, `x\ny`, `ab`, 21-char, null, empty false; password `abc-19.x_y 12` true, `a\|b`, `\n`, `abc`, 31-char, null false | Manual `main`/JUnit if runner added; no Swing needed |
| Integration | Invalid shape never reaches repository; valid shape reaches `AuthService` | Stub `UsuarioRepository`, assert no lookup on invalid |
| E2E | `mvn compile` + `exec:java` opens `LoginFrame`; grep validation path for `matches\|Pattern\|Matcher\|split\|->\|::\|stream` → zero hits | Manual run against `Usuarios.dat` |

## Threat Matrix

N/A — no routing, shell, subprocess, VCS/PR automation, executable-file classification, or process-integration boundary. LOG/DAT injection is handled as input-shape validation, not a process boundary.

## Migration / Rollout

No data migration. Preconditions ship in this change (`release 24`, `mainClass`). Rollback: `git revert`, delete `LoginValidator.java`, restore three files. Known cap: DAT password slot 10 chars truncates 11-30 inputs (documented, needs professor sign-off to widen).

## Open Questions

- None blocking. Follow-ups (out of scope): RAF try-with-resources, `LogService` sanitization, traditional `split` replacement, DAT widen decision with professor.
