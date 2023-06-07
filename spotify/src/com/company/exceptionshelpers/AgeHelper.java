package com.company.exceptionshelpers;


import com.company.exceptions.IncorrectAgeException;

public class AgeHelper {
    public AgeHelper() {
    }
    public void validarEntrada (int entrada) throws IncorrectAgeException {
        if (entrada < 14){
            throw new IncorrectAgeException(" you are not allowed to create an account. You must be 13 or older. ");
        }
        if (entrada > 110){
            throw new IncorrectAgeException(" you are not allowed to create an account. People do not live that much... ");
        }

    }
}
