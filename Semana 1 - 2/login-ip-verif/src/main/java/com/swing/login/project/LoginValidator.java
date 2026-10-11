package com.swing.login.project;

// Utilidad pura de validacion de forma usuario/clave.
// No usa Swing: solo decide si la forma es valida (true) o no (false).
// Solo bucles for + charAt + if/else, sin atajos: todo paso a paso.
public final class LoginValidator {

    // Longitud minima permitida para el nombre de usuario.
    public static final int MIN_USER = 3;

    // Longitud maxima permitida para el nombre de usuario.
    public static final int MAX_USER = 20;

    // Longitud minima permitida para la clave (medida en crudo, sin trim).
    public static final int MIN_PASS = 4;

    // Longitud maxima permitida para la clave (medida en crudo, sin trim).
    public static final int MAX_PASS = 30;

    // Caracteres prohibidos en la clave: delimitador, comodines y saltos de linea.
    // No incluye '.' '_' '-' porque el usuario si los permite.
    public static final String PROHIBIDO = "*´°+|/\r\n";
    
    // Constructor privado: nadie puede crear instancias, todo es static.
    private LoginValidator() {
    }

    // Decide si el usuario tiene forma valida: 3-20 chars, solo letras ASCII, digitos, '.', '_' o '-'.
    public static boolean esUsuarioValido(String usuario) {
        // Un usuario nulo nunca es valido.
        if (usuario == null) {
            return false;
        }
        // Primero la longitud: si esta fuera de rango, se rechaza sin mirar los chars.
        if (usuario.length() < MIN_USER) {
            return false;
        }
        if (usuario.length() > MAX_USER) {
            return false;
        }
        // Luego cada char: uno solo no permitido rechaza todo el usuario.
        for (int i = 0; i < usuario.length(); i = i + 1) {
            char c = usuario.charAt(i);
            if (esCaracterUsuarioPermitido(c)) {
                // Este char esta permitido, se sigue con el siguiente.
            } else {
                return false;
            }
        }
        return true;
    }

    // Decide si la clave tiene forma valida: 4-30 chars en crudo, sin '|' ni saltos de linea.
    public static boolean esPasswordValida(String clave) {
        // Una clave nula nunca es valida.
        if (clave == null) {
            return false;
        }
        // La longitud se mide en crudo: NO se hace trim antes de medir.
        if (clave.length() < MIN_PASS) {
            return false;
        }
        if (clave.length() > MAX_PASS) {
            return false;
        }
        // Si contiene algun prohibido ('|', '\r', '\n', etc.), se rechaza.
        if (contieneProhibido(clave)) {
            return false;
        } else {
            return true;
        }
    }

    // Decide si el char es letra ASCII: A-Z o a-z (con tildes queda fuera a proposito).
    static boolean esLetraAscii(char c) {
        if (c >= 'A') { 
            if (c <= 'Z') {
                return true;
            } else {
                if (c >= 'a') {
                    if (c <= 'z') {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    // Decide si el char es digito ASCII: 0-9.
    static boolean esDigitoAscii(char c) {
        if (c >= '0') {
            if (c <= '9') {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }    

    // Decide si el char esta permitido en el usuario: letra, digito, '.', '_' o '-'.
    static boolean esCaracterUsuarioPermitido(char c) {
        if (esLetraAscii(c)) {
            return true;
        }
        if (esDigitoAscii(c)) {
            return true;
        }
        if (c == '.') {
            return true;
        }
        if (c == '_') {
            return true;
        }
        if (c == '-') {
            return true;
        }
        return false;
    }

    // Decide si el texto contiene algun char de PROHIBIDO (compara char por char con ==).
    static boolean contieneProhibido(String password) {
        for (int i = 0; i < password.length(); i = i + 1) {
            char c = password.charAt(i);
            for (int j = 0; j < PROHIBIDO.length(); j = j + 1) {
                char p = PROHIBIDO.charAt(j);
                
                if (c == p) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // "*´°+|/\r\n";
    
}
