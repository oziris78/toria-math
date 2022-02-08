package com.telek.tests.zsingle;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.special.DiscreteMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class DiscreteMathTest {


    @Test
    @DisplayName("discreteMathTest")
    void discreteMathTest() {

        Assertions.assertEquals(DiscreteMath.getNumberInBase(2000, 2), "11111010000");
        Assertions.assertEquals(DiscreteMath.getNumberInBase(2000, 16), "7D0");
        Assertions.assertEquals(DiscreteMath.getNumberInBase(2000, 8), "3720");

        Assertions.assertEquals(DiscreteMath.getInBase10("11111010000", 2), 2000);
        Assertions.assertEquals(DiscreteMath.getInBase10("7D0", 16), 2000);
        Assertions.assertEquals(DiscreteMath.getInBase10("3720", 8), 2000);

        Random random = new Random();
        for (int unused = 0; unused < 99999; unused++) {
            int num1 = random.nextInt();
            int num2 = random.nextInt();
            Assertions.assertEquals(DiscreteMath.euclideanAlgorithm(num1, num2), TMath.gcd(num1, num2));
        }

        Assertions.assertEquals(DiscreteMath.primeFactorization(2*2*5*7*11*11), "2^2 * 5 * 7 * 11^2");
        Assertions.assertEquals(DiscreteMath.primeFactorization(2*2*2*5*7*11*11), "2^3 * 5 * 7 * 11^2");
        Assertions.assertEquals(DiscreteMath.primeFactorization(2*2*5*5*7*11*11), "2^2 * 5^2 * 7 * 11^2");
        Assertions.assertEquals(DiscreteMath.primeFactorization(2*2*5*7*11*11*11*11), "2^2 * 5 * 7 * 11^4");


    }


}
