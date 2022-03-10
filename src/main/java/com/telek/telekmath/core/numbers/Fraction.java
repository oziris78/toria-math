package com.telek.telekmath.core.numbers;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;

import java.util.Objects;


/**
 * An immutable fraction class. <br>
 */
public class Fraction {

    //////////////
    /*  FIELDS  */
    //////////////

    private final int numerator, denominator;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public Fraction(int numerator, int denominator){
        if(denominator == 0) throw new DivisionByZeroException();
        // SORT
        if( (denominator < 0 && numerator < 0) || (numerator > 0 && denominator < 0) ){
            denominator *= -1;
            numerator *= -1;
        }

        // SIMPLIFY
        int gcd = TMath.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;

        this.numerator = numerator;
        this.denominator = denominator;
    }



    ///////////////
    /*  METHODS  */
    ///////////////

    public double getAsDouble(){
        return ((double) this.numerator) / ((double) this.denominator);
    }

    public boolean isInteger(){
        return this.numerator % this.denominator == 0;
    }

    public Fraction add(Fraction frac2){
        return add(frac2.numerator, frac2.denominator);
    }

    public Fraction subtract(Fraction frac2){
        return add(-frac2.numerator, frac2.denominator);
    }

    public Fraction multiply(Fraction frac2){
        return multiply(frac2.numerator, frac2.denominator);
    }

    public Fraction divide(Fraction frac2){
        return multiply(frac2.denominator, frac2.numerator);
    }

    public Fraction pow(int exponent){
        Fraction frac = new Fraction(TMath.pow(this.numerator, exponent), TMath.pow(this.denominator, exponent));
        return frac;
    }

    public boolean isGreaterThan(Fraction other){
        return this.getAsDouble() > other.getAsDouble();
    }

    public boolean isGreaterOrEqualTo(Fraction other){
        return this.getAsDouble() >= other.getAsDouble();
    }

    public boolean isLessOrEqualTo(Fraction other){
        return this.getAsDouble() <= other.getAsDouble();
    }

    public boolean isLessThan(Fraction other){
        return this.getAsDouble() < other.getAsDouble();
    }



    /*  GETTERS AND SETTERS  */

    public int getNumerator() { return numerator;}
    public int getDenominator() { return denominator;}


    /*  HELPERS  */

    private Fraction multiply(int num2, int denom2){
        Fraction frac = new Fraction(this.numerator * num2, this.denominator * denom2);
        return frac;
    }

    private Fraction add(int num2, int denom2){
        // a/b + c/d = (ad+bc) / bd
        if(this.denominator == denom2){
            return new Fraction(this.numerator + num2, this.denominator);
        }
        else{
            int b = this.denominator;
            int d = denom2;
            return new Fraction(this.numerator * d + b * num2, b * d);
        }
    }





    ///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        if(!this.isInteger()){
            return String.format("%s%d / %d",
                    numerator * denominator < 0 ? "-" : "", Math.abs(numerator), Math.abs(denominator));
        }
        else
            return String.valueOf(this.numerator / this.denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction that = (Fraction) o;
        return this.getAsDouble() == that.getAsDouble();
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }


}
