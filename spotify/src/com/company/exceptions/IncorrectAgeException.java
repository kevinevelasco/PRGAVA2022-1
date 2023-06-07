package com.company.exceptions;

public class IncorrectAgeException extends Exception{
    public static final long serialVersionUID = 700L;
    public IncorrectAgeException(String mensaje){
        super(mensaje);
    }
}
