package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.*;
import com.telek.telekmath.exceptions.TelekMathException.*;
import java.util.Arrays;


public class TPolynomial extends AbstractFunction {


    /*  CONSTANTS  */
    public static final TPolynomial CRC_12 = new TPolynomial(new double[]{1d, 1d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 1d});
    public static final TPolynomial CRC_16 = new TPolynomial(new double[]{1d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 1d});
    public static final TPolynomial CRC_ITU = new TPolynomial(new double[]{1d, 0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 1d});
    public static final TPolynomial CRC_32 = new TPolynomial(new double[]{1d, 1d, 1d, 0d, 1d, 1d, 0d, 1d, 1d, 0d, 1d, 1d, 1d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 1d, 1d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 1d});

    /*  FIELDS  */
    private double[] coefficients;

    /*  CONSTRUCTORS  */

    public TPolynomial(TRange range, double[] coefficients) {
        super(range);
        if(coefficients[coefficients.length-1] == 0d)
            throw new UnnecessaryZeroException();
        this.coefficients = coefficients;
    }


    public TPolynomial(double[] coefficients) {
        this(TRange.REEL_NUMBERS, coefficients);
    }



    /*  METHODS  */

    public TPolynomial add(TPolynomial other){
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length;
        int newLength = Math.max(thisLen, otherLen);
        double[] newCoefs = new double[newLength];

        for (int i = 0; i < newLength; i++) {
            newCoefs[i] = 0d;
            if(i < thisLen) newCoefs[i] += this.coefficients[i];
            if(i < otherLen) newCoefs[i] += other.coefficients[i];
        }

        return new TPolynomial(TRange.getBiggerRange(this.range, other.range), newCoefs);
    }


    public TPolynomial subtract(TPolynomial other){
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length;
        int newLength = Math.max(thisLen, otherLen);
        double[] newCoefs = new double[newLength];

        for (int i = 0; i < newLength; i++) {
            double first = i < thisLen ? this.coefficients[i] : 0d;
            double second = i < otherLen ? other.coefficients[i] : 0d;
            newCoefs[i] += first - second;
        }

        return new TPolynomial(TRange.getBiggerRange(this.range, other.range), newCoefs);
    }


    public TPolynomial multiply(TPolynomial other){

        int m = this.coefficients.length;
        int n = other.coefficients.length;
        int totalLength = m + n - 1;

        double[] result = new double[totalLength];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                result[i + j] += this.coefficients[i] * other.coefficients[j];

        return new TPolynomial(TRange.getBiggerRange(this.range, other.range), result);
    }







    public double getCoefficientOfDegree(int degree){
        int maxDegree = getMaxDegree();
        if(degree < 0 || degree > maxDegree) return 0d;
        return this.coefficients[degree];
    }


    public int getMaxDegree(){
        return this.coefficients.length - 1;
    }


    public boolean hasTerm(int degree){
        int maxDegree = getMaxDegree();
        if(degree < 0 || degree > maxDegree) return false;
        return this.coefficients[degree] != 0;
    }


    @Override
    public TFunction derivative() {
        int newLen = this.coefficients.length - 1;
        double[] newCoefs = new double[newLen];

        for (int i = 0; i < newLen; i++)
            newCoefs[i] = this.coefficients[i+1] * (i+1);

        return new TFunction( new TPolynomial(this.range, newCoefs) );
    }


    @Override
    public double value(double x) {
        if( !range.isInRange(x) ) return 0d;
        double result = 0d;

        int len = this.coefficients.length;
        for (int i = 0; i < len; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }

        return result;
    }


    //////////////////////////////////////////////////////////////////////////////


    /**
     * Returns [1, 1, 1, ...] using the degree.
     * @param degree any integer in range (0,inf)
     * @return 1 + x + x^2 + x^3 + x^4 + ...
     */
    public static TPolynomial getCoefOnePoly(TRange range, int degree){
        double[] newCoef = new double[degree+1];
        for (int i = 0; i < newCoef.length; i++) {
            newCoef[i] = 1d;
        }
        return new TPolynomial(range, newCoef);
    }


    /**  @see #getCoefOnePoly(TRange, int)  */
    public static TPolynomial getCoefOnePoly(int degree){
        return getCoefOnePoly(TRange.REEL_NUMBERS, degree);
    }


    //////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return Arrays.toString(coefficients);
    }

}
