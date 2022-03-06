package com.telek.telekmath.core.constants;


import com.telek.telekutils.plain.TStringUtils;
import java.util.HashMap;


public final class CryptoConstants {

    /* No constructors */
    private CryptoConstants(){}

    public static final StringBuilder ENGLISH_ALPHABET = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    public static final HashMap<Integer, String> baseEleven = getBaseHashMap(11);
    public static final HashMap<Integer, String> baseTwelve = getBaseHashMap(12);
    public static final HashMap<Integer, String> baseThirteen = getBaseHashMap(13);
    public static final HashMap<Integer, String> baseFourteen = getBaseHashMap(14);
    public static final HashMap<Integer, String> baseFifteen = getBaseHashMap(15);
    public static final HashMap<Integer, String> baseSixteen = getBaseHashMap(16);


    /*  HELPERS  */

    private static HashMap<Integer, String> getBaseHashMap(int base){
        HashMap<Integer, String> map = new HashMap<>();
        for(int i = 0; i < base; i++)  map.put(i, TStringUtils.convertNumberToUppercaseBaseString(i));
        return map;
    }


}
