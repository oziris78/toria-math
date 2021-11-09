package com.telek.tests.old;

import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekutils.plain.TCollectionUtils;

public class NewPolyTest {

    public static void main(String[] args) {

        TPolynomial p1 = new TPolynomial(new TRange(-10, 10), TCollectionUtils.doubleArr(
                1, 1, 1, 2
        )); // 1 + x + x^2 + 2x^3

        TPolynomial p2 = new TPolynomial(TCollectionUtils.doubleArr(
                0, 1, 0, 1, 0, 10
        )); // x + x^3 + 10x^5






        /*
          System.out.println(p1);
          System.out.println(p2);

          System.out.println(p1.value(0));
          System.out.println(p2.value(0));

          System.out.println(p1.value(1));
          System.out.println(p2.value(1));

          System.out.println(p1.value(-1));
          System.out.println(p2.value(-1));

          System.out.println(p1.value(2));
          System.out.println(p2.value(2));

          System.out.println(p1.derivative());
          System.out.println(p2.derivative());

          System.out.println(p1.derivative().value(0));
          System.out.println(p2.derivative().value(0));

          System.out.println(p1.derivative().value(1));
          System.out.println(p2.derivative().value(1));


            System.out.println(p1.multiply(p2).value(1));
            System.out.println(p1.multiply(p2).value(2));
            System.out.println(p1.multiply(p2).value(3));

        */


    }






}
