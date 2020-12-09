package com.example.amigos.validar;

public class ValidarDatos {

    public static boolean validarNombre(String nom) {
        boolean error = false;

        if(nom.length() > 20) {
            error = true;
        } else {

            for (int i = 0; i < nom.length(); i++) {
                if (!Character.isAlphabetic(nom.charAt(i)) && nom.charAt(i) != ' ') {
                    error = true;
                }
            }
        }
        return error;
    }

    public static boolean validarTelefono(String tlf) {
        return tlf.length() == 9 && tlf.charAt(0) >= '6' && tlf.charAt(0) <= '9';
    }

    public static boolean validarFecha(String fecha) {
        return fecha.length() == 10 && fecha.charAt(2) == '/' && fecha.charAt(5) == '/';
    }
}
