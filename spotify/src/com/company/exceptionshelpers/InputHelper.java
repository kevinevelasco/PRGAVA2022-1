package com.company.exceptionshelpers;

import com.company.exceptions.WordInputInNumberInputException;

public class InputHelper {
    public InputHelper() {
    }
    public void validarEntrada (String entrada) throws WordInputInNumberInputException {
        if (!entrada.matches("[1-9][0-9]*")){
            throw new WordInputInNumberInputException(" the input is not a number");
        }
    }
}
