package com.company.exceptions;

public class NamesAndLastNamesException extends Exception{
    public static final long serialVersionUID = 700L;
    public NamesAndLastNamesException(String mensaje){
        super(mensaje);
    }
}
