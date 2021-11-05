package com.telek.tests.old;

import com.telek.telekmath.core.numbers.Fraction;

public class FractionTest {

    public static void main(String[] args) {


        Fraction f1 = new Fraction(20, 3);
        Fraction f2 = new Fraction(40, 6);
        Fraction f3 = new Fraction(9999, 999);
        Fraction f4 = new Fraction(610, 61);

        for(Fraction f : new Fraction[]{ f1, f2, f3, f4}){
            System.out.println(f);
        }
        System.out.println();


        for(Fraction f : new Fraction[]{ f1, f2, f3, f4}){
            System.out.println(f.getAsDouble());
        }
        System.out.println();


        Fraction f5 = new Fraction(10, 9);
        System.out.println(f5.pow(2));
        System.out.println(f5.pow(3));
        System.out.println();

        System.out.println( f1.add(f2) );
        System.out.println( f1.subtract(f2) );
        System.out.println( f1.multiply(f2) );
        System.out.println( f1.divide(f2) );
        System.out.println();

        System.out.println(new Fraction(-2, 7));
        System.out.println(new Fraction(2, -7));
        System.out.println(new Fraction(-2, -7));
        System.out.println();



    }


}
