package com.company.exceptions;

public class IncorrectUsernameException extends Exception{
    public static final long serialVersionUID = 700L;
    public IncorrectUsernameException(String mensaje){
        super(mensaje);
    }
}
