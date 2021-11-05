package com.telek.tests.unittests.functions;

import com.telek.telekmath.core.functions.polynomials.PolynomialTerm;
import com.telek.telekmath.core.functions.polynomials.TPolynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TPolyTest {

    @Test
    @DisplayName("polyTest")
    void polyTest() {

        TPolynomial p1 = new TPolynomial(
                PolynomialTerm.term(1d, 3),
                PolynomialTerm.term(-1d, 2),
                PolynomialTerm.term(10d, 1),
                PolynomialTerm.term(1d, 0)
        ); // x^3 - x^2 + 10x + 1

        TPolynomial p2 = new TPolynomial(
                PolynomialTerm.term(9d, 2),
                PolynomialTerm.term(-9d, 1),
                PolynomialTerm.term(9d, 0)
        ); // 9x^2 - 9x + 9

        TPolynomial p3 = new TPolynomial(
                PolynomialTerm.term(10d, 0)
        ); // 10

        TPolynomial p4 = new TPolynomial(
                PolynomialTerm.term(0d, 0)
        ); // 0

        TPolynomial p5 = new TPolynomial(
                PolynomialTerm.term(100d, 10)
        ); // 100 x^10

        ArrayList<PolynomialTerm> terms1 = new ArrayList<>();
        terms1.add(PolynomialTerm.term(100d, 10));
        TPolynomial p6 = new TPolynomial(terms1); // 100 x^10

        ArrayList<PolynomialTerm> terms2 = new ArrayList<>();
        terms2.add(PolynomialTerm.term(100d, 10));
        terms2.add(PolynomialTerm.term(30d, 9));
        terms2.add(PolynomialTerm.term(100d, 0));
        TPolynomial p7 = new TPolynomial(terms2); // 100 x^10 + 30 x^9 + 100

        TPolynomial p8 = new TPolynomial(
                PolynomialTerm.term(2d, 2),
                PolynomialTerm.term(2d, 2)
        ); // should be  2 x^2

        /*
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);
         */

        System.out.println(" --- " + p1  + "\n " + " --- " + p3 + " \n ### " + p1.add(p3) + "\n\n");
        System.out.println(" --- " + p1  + "\n " + " --- " + p5 + " \n ### " + p1.add(p5) + "\n\n");
        System.out.println(" --- " + p1  + "\n " + " --- " + p8 + " \n ### " + p1.add(p8) + "\n\n");
        System.out.println(" --- " + p3  + "\n " + " --- " + p5 + " \n ### " + p3.add(p5) + "\n\n");
        System.out.println(" --- " + p3  + "\n " + " --- " + p8 + " \n ### " + p3.add(p8) + "\n\n");
        System.out.println(" --- " + p5  + "\n " + " --- " + p8 + " \n ### " + p5.add(p8) + "\n\n");


        System.out.println(" --- " + p1  + "\n " + " --- " + p3 + " \n ### " + p1.multiply(p3) + "\n\n");
        System.out.println(" --- " + p1  + "\n " + " --- " + p5 + " \n ### " + p1.multiply(p5) + "\n\n");
        System.out.println(" --- " + p1  + "\n " + " --- " + p8 + " \n ### " + p1.multiply(p8) + "\n\n");
        System.out.println(" --- " + p3  + "\n " + " --- " + p5 + " \n ### " + p3.multiply(p5) + "\n\n");
        System.out.println(" --- " + p3  + "\n " + " --- " + p8 + " \n ### " + p3.multiply(p8) + "\n\n");
        System.out.println(" --- " + p5  + "\n " + " --- " + p8 + " \n ### " + p5.multiply(p8) + "\n\n");

    }


}
