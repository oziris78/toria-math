package com.telek.telekmath.exceptions;

public class RepeatedPermutationException extends IllegalArgumentException {


    public RepeatedPermutationException(int n, int... rValues){
        super( getErrString(n, rValues) );
    }

    private static String getErrString(int n, int... rValues){
        StringBuilder sbError = new StringBuilder( String.format("Invalid values for repeated permutation for perm(%d,", n) );
        for(int i = 0; i < rValues.length; i++) {
            if(i+1 == rValues.length) sbError.append(rValues[i] + ")");
            else sbError.append(rValues[i] + ",");
        }
        return sbError.toString();
    }



}
