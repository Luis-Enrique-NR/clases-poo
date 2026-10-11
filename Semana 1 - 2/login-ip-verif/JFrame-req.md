# JFrame-req: contrato para el usuario (LoginFrame, alcance USER)

> El agente backend NO toca `LoginFrame.java` ni `*.form` ni `nbactions`.
> Este archivo es la receta exacta para que el usuario complete el wiring en
> `AceptarButtonActionPerformed`. Alcance: tareas 3.1 y 3.2.

## Firmas exactas a llamar

```java
// En com.swing.login.project.LoginValidator (todo static, sin Swing):
public static boolean esUsuarioValido(String usuario);
public static boolean esPasswordValida(String clave);
```

Constantes de referencia (no hace falta usarlas en el JFrame, son para entender los rangos):

```java
public static final int MIN_USER = 3;
public static final int MAX_USER = 20;
public static final int MIN_PASS = 4;
public static final int MAX_PASS = 30;
public static final String FORBIDDEN = "*´°+|/\r\n";
```

## Orden obligatorio en `AceptarButtonActionPerformed`

1. Leer campos: `UsernameField.getText()` y `new String(PasswordField.getPassword())`.
2. **Trim SOLO al usuario**: `String user = UsernameField.getText().trim();`
   La clave se usa **cruda**: `String pass = new String(PasswordField.getPassword());`
   (quitar el `.trim()` actual de la clave).
3. Validar usuario primero:
   `if (!LoginValidator.esUsuarioValido(user))` → `JOptionPane` de usuario y `return;` (abortar).
4. Validar clave después, solo si el usuario pasó:
   `if (!LoginValidator.esPasswordValida(pass))` → `JOptionPane` de clave y `return;` (abortar).
5. **Recién entonces** llamar `authService.validarCredenciales(user, pass)` y el resto igual
   (`logService.registrarIntento`, mensajes de éxito/error actuales).

Esqueleto sugerido (pegar dentro del handler, reemplazando el `isEmpty` + `.trim()` de pass):

```java
String user = UsernameField.getText().trim();
String pass = new String(PasswordField.getPassword());

if (!LoginValidator.esUsuarioValido(user)) {
    javax.swing.JOptionPane.showMessageDialog(this,
        "Usuario inválido: use 3-20 caracteres (letras A-Z sin tildes, dígitos, punto, guion bajo o guion medio).",
        "Usuario inválido",
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}

if (!LoginValidator.esPasswordValida(pass)) {
    javax.swing.JOptionPane.showMessageDialog(this,
        "Clave inválida: use 4-30 caracteres sin | ni saltos de línea.",
        "Clave inválida",
        javax.swing.JOptionPane.WARNING_MESSAGE);
    return;
}

boolean esValido = authService.validarCredenciales(user, pass);
// ... resto igual (logService + mensajes actuales)
```

## Textos sugeridos por campo (JOptionPane)

| Campo | Título | Mensaje sugerido | Tipo |
|-------|--------|------------------|------|
| Usuario inválido | `Usuario inválido` | `Usuario inválido: use 3-20 caracteres (letras A-Z sin tildes, dígitos, punto, guion bajo o guion medio).` | `WARNING_MESSAGE` |
| Clave inválida | `Clave inválida` | `Clave inválida: use 4-30 caracteres sin \| ni saltos de línea.` | `WARNING_MESSAGE` |

## Ejemplos

| Entrada | Resultado esperado |
|---------|--------------------|
| Usuario `ana-19.x_y` | válido → pasa a validar clave |
| Usuario `a\|b` | inválido → JOptionPane usuario, aborta, `AuthService` nunca se llama |
| Usuario con salto (`x` + newline + `y`) | inválido → JOptionPane usuario, aborta |
| Usuario `--` (2 chars) | inválido → JOptionPane usuario, aborta (muy corto, aunque `-` esté permitido) |
| Clave `abc-19.x_y 12` | válida → llega a `AuthService` |
| Clave `a\|b` o con `\n` | inválida → JOptionPane clave, aborta |

## Qué NO hacer

- **NO** hacer `trim()` a la clave: los espacios son parte de la clave y el `trim()` destruye datos.
- **NO** validar con regex (`matches`, `Pattern`, `Matcher`, `split`) ni lambdas/streams en el handler.
- **NO** llamar `authService.validarCredenciales` si algún campo es inválido (cada `JOptionPane` va con `return;`).
- **NO** invertir el orden: usuario primero, clave después, `AuthService` al final.
- **NO** tocar la lógica `equals`/`isActivo` de `AuthService` ni los `*.form`/`nbactions`.
