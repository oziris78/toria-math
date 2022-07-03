package com.twistral.tests.zsingle;

import com.twistral.toriamath.utils.ToriaMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class ToriaMathTest {

    static final int TIMES = 10_000;


    @Test
    @DisplayName("tmathTest")
    void tmathTest() {
        Assertions.assertEquals(ToriaMath.floor(2d), 2d);
        Assertions.assertEquals(ToriaMath.floor(2.1d), 2d);
        Assertions.assertEquals(ToriaMath.floor(2.9999999d), 2d);
        Assertions.assertEquals(ToriaMath.floor(3d), 3d);

        for (int i = 0; i < 13; i++) {
            double prod = 1;
            for (int j = 1; j <= i; j++) prod *= j;
            Assertions.assertEquals(ToriaMath.factorialInt(i), prod);
        }

        Assertions.assertEquals(ToriaMath.isPrime(3), true);
        Assertions.assertEquals(ToriaMath.isPrime(4), false);
        Assertions.assertEquals(ToriaMath.isPrime(5), true);
        Assertions.assertEquals(ToriaMath.isPrime(6), false);
        Assertions.assertEquals(ToriaMath.isPrime(7), true);
        Assertions.assertEquals(ToriaMath.isPrime(8), false);
        Assertions.assertEquals(ToriaMath.isPrime(88888), false);

        Assertions.assertEquals(ToriaMath.gcd(60645, 5050), 5);
        Assertions.assertEquals(ToriaMath.gcd(9191, 510), 1);
        Assertions.assertEquals(ToriaMath.gcd(8515, 5010), 5);
        Assertions.assertEquals(ToriaMath.gcd(40, 20), 20);

        Random random = new Random();
        for (int i = 0; i < TIMES; i++) {
            double x = random.nextDouble() *  random.nextInt(999999999);

            double val1 = ToriaMath.log(x);
            double val2 = Math.log(x);
            boolean b1 = ToriaMath.areEqual(val1, val2);
            if(!b1){
                System.out.println("x: " + x);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(b1);
        }


        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(5d), 120));
        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(4d), 24));
        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(3d), 6));
        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(2d), 2));
        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(1d), 1));
        Assertions.assertTrue(ToriaMath.areEqual(ToriaMath.factorial(0d), 1));





    }




}
