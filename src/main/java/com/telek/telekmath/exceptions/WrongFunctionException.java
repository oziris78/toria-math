package com.telek.telekmath.exceptions;


public class WrongFunctionException extends IllegalArgumentException{

    public WrongFunctionException(String theValue, String forValue, String cantBeValue) {
        super(
            theValue + " value for " + forValue + " can't be " + cantBeValue + ", use TPolynomials for constant functions."
        );
    }


}
