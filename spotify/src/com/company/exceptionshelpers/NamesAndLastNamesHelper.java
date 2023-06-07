package com.company.exceptionshelpers;
import com.company.exceptions.NamesAndLastNamesException;

public class NamesAndLastNamesHelper {
    public NamesAndLastNamesHelper() {
    }

    public void validarEntrada(String s) throws NamesAndLastNamesException {

        if(!s.matches("^([A-Za-z][a-zA-Z]+([ ]?[a-zA-Z]?['-]?[A-Za-z][a-z-A-Z]+)*)$")){
            throw new NamesAndLastNamesException("the input has been incorrectly typed.\n");
        }

        if(s.length() < 1) {
                 throw new NamesAndLastNamesException("the input has an invalid character.\n");
        }

        if(s.length() == 1){
            if (s.equals(" ")){
                throw new NamesAndLastNamesException("the input has an invalid character.\n");
            }
        }

        if(s.length() > 30){
            throw new NamesAndLastNamesException("the input has exceeded the 30 characters limit.\n");
        }
    }
}
