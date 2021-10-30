package com.telek.telekutils.plain;

public class TStringUtils {



    /**
     * If x is in [0,9] it returns x as a string but if it's 10,11,12,... it returns "A","B","C",...
     * @param x any number
     * @return x as a string for bases
     */
    public static String convertNumberToUppercaseBaseString(int x){
        if( 0 <= x && x <= 9 ) return String.valueOf(x);
        return String.valueOf( (char) (65 + x - 10) );
    }



}
