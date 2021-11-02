package com.telek.tests;


import com.telek.telekmath.core.functions.polynomials.PolynomialTerm;
import com.telek.telekmath.core.functions.polynomials.TPolynomial;
import com.telek.telekmath.special.NumericalAnalysis;


public class NumericalAnalTest {

    public static void main(String[] args) {

        TPolynomial p1 = new TPolynomial(
            PolynomialTerm.term(1d, 4),
            PolynomialTerm.term(-2d, 0),
            PolynomialTerm.term(2d, 1),
            PolynomialTerm.term(1d, 2)
        );

        // -2.0 + 2.0 x + 1.0 x^2 + 1.0 x^4
        // zeros are 0.6721, -1.3057

        System.out.println("regulaFalse: " + NumericalAnalysis.regulaFalse(p1, -1, 2, 500));
        System.out.println("regulaFalse: " + NumericalAnalysis.regulaFalse(p1, -1, -2, 500));

        System.out.println("bisectionMethod: " + NumericalAnalysis.bisectionMethod(p1, -1, 2, 500));
        System.out.println("bisectionMethod: " + NumericalAnalysis.bisectionMethod(p1, -1, -2, 500));

        // TODO: 02/11/2021
//        System.out.println("newtonMethod: " + NumericalAnalysis.newtonsMethod(p1, 0, 2, 500));
//        System.out.println("newtonMethod: " + NumericalAnalysis.newtonsMethod(p1, -1, -2, 500));

    }

}
