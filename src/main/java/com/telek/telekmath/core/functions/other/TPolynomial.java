package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.*;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.containers.TArrays;

import java.util.Arrays;


public class TPolynomial extends AbstractFunction {


    /*  CONSTANTS  */
    public static final TPolynomial CRC_12 = new TPolynomial(new double[]{1d, 1d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 1d});
    public static final TPolynomial CRC_16 = new TPolynomial(new double[]{1d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 1d});
    public static final TPolynomial CRC_ITU = new TPolynomial(new double[]{1d, 0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 1d});
    public static final TPolynomial CRC_32 = new TPolynomial(new double[]{1d, 1d, 1d, 0d, 1d, 1d, 0d, 1d, 1d, 0d, 1d, 1d, 1d, 0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 1d, 1d, 0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d, 1d});

    /*  FIELDS  */
    private final double[] coefficients;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public TPolynomial(TRange range, double... coefficients) {
        super(range);
        if(coefficients[coefficients.length-1] == 0d && coefficients.length != 1)
            throw new UnnecessaryZeroException();
        this.coefficients = coefficients;
    }


    /**
     * Unlike MATLAB, this constructor reads the values from small exponents to higher exponents. <br>
     * For example [1,0,1,9] means 1 + x^2 + 9x^3, in MATLAB in would mean x^3+x+9...
     * @param coefficients an array specifying the coefficient of the polynomial terms
     */
    public TPolynomial(double... coefficients) {
        this(TRange.REEL_NUMBERS, coefficients);
    }




    ///////////////
    /*  METHODS  */
    ///////////////



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


    public static TPolynomial getTPolynomial(TRange range, double... roots){
        TPolynomial P = new TPolynomial(range, new double[]{1d}); // P(x) = 1
        for (int i = 0; i < roots.length; i++) {
            TPolynomial newPoly = new TPolynomial(range, new double[]{ -roots[i], 1d });
            P = P.multiply(newPoly);
        }
        return P;
    }

    public static TPolynomial getTPolynomial(double... roots){
        return getTPolynomial(TRange.REEL_NUMBERS, roots);
    }

    //////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%f", coefficients[0]));
        if(coefficients.length != 1) sb.append(" + ");
        for (int i = 1; i < coefficients.length; i++) {
            double val = coefficients[i];
            if(val == 0) continue;
            sb.append(String.format("%f x^%d", val, i));
            if(i+1 != coefficients.length) sb.append(" + ");
        }
        return sb.toString().replaceAll("\\+ \\-", "- ").replaceAll("\\^1", "");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TPolynomial that = (TPolynomial) o;
        return Arrays.equals(coefficients, that.coefficients);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(coefficients);
        return result;
    }
}
