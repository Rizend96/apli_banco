package com.example.aplicacionbancaria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarPass(String pass) {
        if(Pattern.compile("[0-9]").matcher(pass).find()) {
            if (Pattern.compile("[0-9]").matcher(pass).find()) {
                System.out.println("Contraseña tiene numeros y caracteres");
                return true;
            }
        }
        return false;
    }

}
