package com.telek.tests.zsingle;

import com.telek.telekmath.TMath;
import com.telek.telekmath.core.matrices.TMatrix;
import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

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


    @Test
    @DisplayName("hyperGeometricFunc")
    void hyperGeometricFunc() {
        Assertions.assertTrue(TMath.areEqual(TMath.hypergeometric(2,3,4,0.5d), 2.728935333122625147973d));
        Assertions.assertTrue(TMath.areEqual(TMath.hypergeometric(2,4,5,0.5d), 2.915741332490500591891));
        Assertions.assertTrue(TMath.areEqual(TMath.hypergeometric(20,40,50,0.25d), 92.23029943156941798814));
        Assertions.assertTrue(TMath.areEqual(TMath.hypergeometric(-5,-2,9,0.751d), 1.959778));
    }

    @Test
    @DisplayName("hyperBenchmark")
    void hyperBenchmark() {
        /*
        Random random = new Random();
        Instant now = Instant.now();
        for (int i = 0; i < 99999999; i++) {
            double z = random.nextInt(2);
            double a = random.nextDouble() * random.nextInt(50);
            double b = random.nextDouble() * random.nextInt(50);
            double c = random.nextDouble() * random.nextInt(50);
            double y = TMath.hypergeometric(a, b, c, z);
        }
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).toMillis());
        */
    }

}
