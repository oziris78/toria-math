package com.telek.telekmath.helpers;

import java.util.HashMap;

public class TSpecialUtils {

    /**
     * Stringifies the output of DiscreteMath.primeFactorization(long)
     * @param factors output of DiscreteMath.primeFactorization(long)
     * @return factors in a string form
     */
    public static String stringifyFactors(HashMap<Long,Long> factors){
        StringBuilder sb = new StringBuilder();
        for(Long key : factors.keySet()) sb.append(String.format("%d^%d * ", key, factors.get(key)));
        return sb.substring(0, sb.length()-2);
    }

}
