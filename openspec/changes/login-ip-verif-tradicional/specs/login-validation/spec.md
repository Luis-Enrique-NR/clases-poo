# login-validation Specification

## Purpose

Block injection of `* | / \ \r \n` through login (DAT/TXT/LOG) by validating username/password shape with traditional loops before authentication. Scope: `Semana 1 - 2/login-ip-verif/src/main/java/com/swing/login/project/LoginValidator.java` (new), `LoginFrame.java`, `AuthService.java`, `pom.xml`.

## Requirements

### Requirement: Username shape

The system MUST accept usernames of length 3-20 where every char is ASCII letter (A-Z, a-z), ASCII digit (0-9), dot (`.`), underscore (`_`), or hyphen (`-`). The system MUST reject null, empty, out-of-range length, and any char in `* ´ ° + | / \ \r \n`.

#### Scenario: Valid username passes

- GIVEN username `ana-19.x_y`
- WHEN `esUsuarioValido` is called
- THEN it returns true

#### Scenario: Forbidden and out-of-range usernames fail

- GIVEN username `--`, `a|b`, `x` + newline + `y`, `ab`, 21-char name, null, or empty
- WHEN `esUsuarioValido` is called
- THEN it returns false

### Requirement: Password shape without trim

The system MUST accept passwords of length 4-30 measured on the raw value (NO trim). The system MUST reject null, out-of-range length, and any password containing `|`, `\r`, or `\n`.

#### Scenario: Valid password passes

- GIVEN password `abc-19.x_y 12` (4-30 chars, no `|`/line break)
- WHEN `esPasswordValida` is called
- THEN it returns true

#### Scenario: Forbidden passwords fail

- GIVEN password `a|b`, `x` + newline + `y`, `abc`, 31-char value, or null
- WHEN `esPasswordValida` is called
- THEN it returns false

### Requirement: Validation order and per-field UI feedback

The system MUST validate shape in `LoginFrame.AceptarButtonActionPerformed` BEFORE calling `AuthService.validarCredenciales`. Username SHALL be trimmed before validation; password SHALL NOT be trimmed. Each invalid field MUST show a distinct `JOptionPane` message and abort the login attempt.

#### Scenario: Invalid username stops before AuthService

- GIVEN an invalid username and any password
- WHEN the accept action runs
- THEN a username message shows and `validarCredenciales` is never called

#### Scenario: Invalid password stops before AuthService

- GIVEN a valid username and a password with `|`
- WHEN the accept action runs
- THEN a password message shows and `validarCredenciales` is never called

### Requirement: AuthService defense-in-depth guard

`AuthService.validarCredenciales` MUST return false when username or password shape is invalid, before touching the repository.

#### Scenario: Guard rejects invalid shape

- GIVEN username `a|b` or password with `\n`
- WHEN `validarCredenciales` is called
- THEN it returns false without repository lookup

### Requirement: Traditional implementation only

The validation path SHALL use only `for`, `charAt`, `if/else`, named constants (`MIN/MAX_USER 3/20`, `MIN/MAX_PASS 4/30`, `FORBIDDEN`), and small named predicates (`esUsuarioValido`, `esPasswordValida`, `esLetraAscii`, `esDigitoAscii`, `esCaracterUsuarioPermitido`, `contieneProhibido`). The validation path MUST NOT use `String.matches`, `Pattern`, `Matcher`, or `split`; it MUST NOT use lambdas, streams, method references, or functional programming.

#### Scenario: Forbidden APIs absent from validation path

- GIVEN the `LoginValidator` source plus the validation call sites
- WHEN searched for `matches|Pattern|Matcher|split|->|::|stream|filter|map|forEach`
- THEN no match occurs inside the validation path

### Requirement: Build preconditions

The module `Semana 1 - 2/login-ip-verif/pom.xml` MUST set `maven.compiler.release` to `24` and `exec.mainClass` to `com.swing.login.project.LoginFrame`.

#### Scenario: Build starts login UI

- GIVEN JDK 24 and the fixed `pom.xml`
- WHEN `mvn compile` and `exec:java` run
- THEN compilation succeeds and `LoginFrame` opens
