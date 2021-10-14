package com.telek.tests;

import com.telek.telekmath.helpers.TMathSpecial;
import com.telek.telekmath.special.DiscreteMath;

import java.util.HashMap;

public class DiscreteMathTest {

    public static void main(String[] args) {

        HashMap<Long, Long> factors = DiscreteMath.primeFactorization(4511541); // 1503847 ^ 1 * 3 ^ 1
        String factorizationStr = TMathSpecial.stringifyFactors(factors);
        System.out.println(factorizationStr);

        System.out.println( TMathSpecial.stringifyFactors(
            DiscreteMath.primeFactorization(1561674465123L)
        ));


    }

}
