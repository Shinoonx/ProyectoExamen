package Utilidadess;

import java.util.regex.Pattern;

public class Validador {

    public static boolean esCorreoValido(String correo) {
        String patronCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(patronCorreo).matcher(correo).matches();
    }

    public static boolean esRutValido(String rut) {
        String patronRut = "^[0-9]{8}-[0-9Kk]$";
        return Pattern.compile(patronRut).matcher(rut).matches();
    }
    public static boolean esCodigoBaesValido(String codigoBaes) {
        String patronCodigoBaes = "^[0-9]{4}$";
        return Pattern.compile(patronCodigoBaes).matcher(codigoBaes).matches();
    }
}

