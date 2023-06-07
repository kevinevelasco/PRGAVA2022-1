package com.company.exceptions;

public class WordInputInNumberInputException extends Exception{
    public static final long serialVersionUID = 700L;
    public WordInputInNumberInputException(String mensaje){
        super(mensaje);
    }
}
