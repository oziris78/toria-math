package com.telek.telekutils.plain;


public class TStringUtils {


    /* No Constructor */
    private TStringUtils(){}


    /**
     * If x is in [0,9] it returns x as a string but if it's 10,11,12,... it returns "A","B","C",...
     * @param x any number
     * @return x as a string for bases
     */
    public static String convertNumberToUppercaseBaseString(int x){
        if( 0 <= x && x <= 9 ) return String.valueOf(x);
        return String.valueOf( (char) (65 + x - 10) );
    }

    public static String getAsHex(int value){
        String valueAsHex = Integer.toHexString(value);
        return (valueAsHex.length() == 1) ? "0" + valueAsHex : valueAsHex;
    }


    /**
     * Returns a string that is the given string repeated n times.
     * @param str any string
     * @param n any integer
     * @return the given string repeated n times
     */
    public static String repeat(String str, int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(str);
        return sb.toString();
    }



}
