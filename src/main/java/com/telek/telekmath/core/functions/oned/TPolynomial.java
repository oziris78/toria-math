package com.telek.telekmath.core.functions.oned;

import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.utils.TMath;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * An immutable polynomial class. <br>
 */
public class TPolynomial extends AbstractSingleVarFunc {


    private final double[] coefficients;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**
     * @see #TPolynomial(double...)
     * @param domain any domain
     * @param coefficients an array specifying the coefficient of the polynomial terms
     */
    public TPolynomial(TRange domain, double... coefficients){
        super(domain);
        this.coefficients = coefficients.length > 0 ? coefficients : new double[]{ 0d };
    }


    /**
     * Unlike MATLAB, this constructor reads the values from small exponents to higher exponents. <br>
     * For example [1,0,1,9] means 1 + x^2 + 9x^3. <br>
     * in MATLAB it would mean x^3 + x + 9. <br>
     * @param coefficients an array specifying the coefficient of the polynomial terms
     */
    public TPolynomial(double... coefficients){
        this(TRange.REEL_NUMBERS, coefficients);
    }


    /**
     * Constructs P(x) = 0
     */
    public TPolynomial(){
        this(TRange.REEL_NUMBERS, 0d);
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public TPolynomial add(double... other){
        return this.add(new TPolynomial(other));
    }

    public TPolynomial add(TPolynomial other){
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length;

        int newLength = Math.max(thisLen, otherLen);
        double[] newCoefs = new double[newLength];
        for (int i = 0; i < newLength; i++) {
            if(i < thisLen)
                newCoefs[i] += this.coefficients[i];
            if(i < otherLen)
                newCoefs[i] += other.coefficients[i];
        }

        return new TPolynomial(TRange.getBiggerRange(this.domain, other.domain), newCoefs);
    }


    public TPolynomial subtract(double... other){
        return this.subtract(new TPolynomial(other));
    }

    public TPolynomial subtract(TPolynomial other){
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length;

        int newLength = Math.max(thisLen, otherLen);
        double[] newCoefs = new double[newLength];
        double first, second;
        for (int i = 0; i < newLength; i++) {
            first = i < thisLen ? this.coefficients[i] : 0d;
            second = i < otherLen ? other.coefficients[i] : 0d;
            newCoefs[i] += first - second;
        }

        return new TPolynomial(TRange.getBiggerRange(this.domain, other.domain), newCoefs);
    }


    public TPolynomial multiply(double... other){
        return this.multiply(new TPolynomial(other));
    }

    public TPolynomial multiply(TPolynomial other){
        int m = this.coefficients.length;
        int n = other.coefficients.length;
        int totalLength = m + n - 1; // -2 ?

        double[] result = new double[totalLength];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                result[i + j] += this.coefficients[i] * other.coefficients[j];

        return new TPolynomial(TRange.getBiggerRange(this.domain, other.domain), result);
    }


    public double getCoefficientOfDegree(int degree){
        if(degree < 0 || degree > this.coefficients.length - 1)
            return 0d;
        return this.coefficients[degree];
    }


    @Override
    public double value(double x) {
        if( !domain.isInRange(x) || this.coefficients.length == 0 )
            return 0d;

        double result = 0d;
        double coef;

        for (int i = 0; i < this.coefficients.length; i++){
            coef = this.coefficients[i];
            if(!TMath.areEqual(coef, 0d))
                result +=  coef * TMath.pow(x, i);
        }

        return result;
    }



    @Override
    public TCompositeFunc derivative() {
        int newLen = this.coefficients.length - 1;
        double[] newCoefs = new double[newLen];

        for (int i = 0; i < newLen; i++)
            newCoefs[i] = this.coefficients[i+1] * (i+1);

        return new TCompositeFunc( new TPolynomial(this.domain, newCoefs) );
    }




    //////////////////////
    /*  OBJECT METHODS  */
    //////////////////////


    @Override
    public String toString() {
        final int len = this.coefficients.length;

        if(len == 1)
            return String.valueOf(this.coefficients[0]);

        String str = IntStream.range(0, len)
                .map(      n -> len-1-n )
                .filter(   n -> this.coefficients[n] != 0 )
                .mapToObj( n -> ""+this.coefficients[n] + ( n == 1 ? "x"
                        : n != 0 ? "x^"+n : "") )
                .collect(Collectors.joining("+"))
                .replaceAll("\\b1(?=x)|\\+(?=-)", "");

        str = str.replaceAll("\\+", " \\+ ");
        str = str.replaceAll("-", " - ");
        str = str.replaceAll("x", " x");

        if(str.startsWith(" - "))
            str = "-" + str.substring(3);

        return str;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TPolynomial that = (TPolynomial) o;
        return Arrays.equals(coefficients, that.coefficients);
    }




    @Override
    public int hashCode() {
        return Arrays.hashCode(coefficients);
    }



}
