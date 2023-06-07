package com.company.exceptionshelpers;
import com.company.exceptions.PasswordExceptions;

public class PasswordHelper {
    public PasswordHelper() {
    }

    public void validarEntrada(String s) throws PasswordExceptions {
        if(!s.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
                 throw new PasswordExceptions("the input has not included the specified requirements.\n");
        }
    }
}
