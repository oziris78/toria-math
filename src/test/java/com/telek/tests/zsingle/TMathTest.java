package com.telek.tests.zsingle;

import com.telek.telekmath.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TMathTest {

    @Test
    @DisplayName("tmathTest")
    void tmathTest() {

        Assertions.assertEquals(TMath.floor(2d), 2d);
        Assertions.assertEquals(TMath.floor(2.1d), 2d);
        Assertions.assertEquals(TMath.floor(2.9999999d), 2d);
        Assertions.assertEquals(TMath.floor(3d), 3d);

        for (int i = 0; i < 13; i++) {
            double prod = 1;
            for (int j = 1; j <= i; j++) prod *= j;
            Assertions.assertEquals(TMath.factorial(i), prod);
        }

        Assertions.assertEquals(TMath.isPrime(3), true);
        Assertions.assertEquals(TMath.isPrime(4), false);
        Assertions.assertEquals(TMath.isPrime(5), true);
        Assertions.assertEquals(TMath.isPrime(6), false);
        Assertions.assertEquals(TMath.isPrime(7), true);
        Assertions.assertEquals(TMath.isPrime(8), false);
        Assertions.assertEquals(TMath.isPrime(88888), false);

        Assertions.assertEquals(TMath.gcd(60645, 5050), 5);
        Assertions.assertEquals(TMath.gcd(9191, 510), 1);
        Assertions.assertEquals(TMath.gcd(8515, 5010), 5);
        Assertions.assertEquals(TMath.gcd(40, 20), 20);

        for (int i = 0; i < 13; i++) {
            Assertions.assertEquals(TMath.pow(2, i), Math.pow(2, i));
        }







    }




}
