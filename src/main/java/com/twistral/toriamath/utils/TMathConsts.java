package com.twistral.toriamath.utils;


import com.twistral.toriautils.plain.TStringUtils;

import java.util.HashMap;

public final class TMathConsts {

    /* No constructors */
    private TMathConsts(){}


    // toria-math uses a bigger value just to make it more flexible...
    // this EPSILON will be updated to 1E-6 or 1E-8 in the future
    public static final double EPSILON = 1E-4;

    public static final double PI = 3.14159265358979323846d;
    public static final double E = 2.7182818284590452354d;

    public static final double GOLDEN_RATIO = 1.61803398874989484820d;

    public static final double SQRT2 = 1.4142135623730950488d;
    public static final double SQRT3 = 1.732050807568877293527d;
    public static final double SQRT5 = 2.23606797749978969640d;

    public static final double HALF_LOG_2_PI = 0.9189385332046727417803d;

    /** PI / 2 */
    public static final double PI_OVER_TWO = PI / 2d;

    /** -PI / 2 */
    public static final double MPI_OVER_TWO = -PI_OVER_TWO;


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
        for(int i = 0; i < base; i++)
            map.put(i, TStringUtils.convertNumberToUppercaseBaseString(i));
        return map;
    }

}
