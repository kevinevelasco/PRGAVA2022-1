package com.company.exceptionshelpers;
import com.company.exceptions.OtherStringsExceptions;

public class OtherStringsHelper {
    public OtherStringsHelper() {
    }

    public void validarEntrada(String s) throws OtherStringsExceptions {
        if(s.length() < 1) {
                 throw new OtherStringsExceptions("the input has an invalid character.\n");
        }

        if(s.length() == 1){
            if (s.equals(" ")){
                throw new OtherStringsExceptions("the input has an invalid character.\n");
            }
        }

        if(s.length() > 30){
            throw new OtherStringsExceptions("the input has exceeded the 30 characters limit.\n");
        }
    }
}
