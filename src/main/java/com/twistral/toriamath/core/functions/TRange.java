package com.twistral.toriamath.core.functions;


import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.ToriaMathException.EqualBoundsException;
import java.util.Objects;



/**
 * An immutable range/interval class. <br>
 * Even though this class represents a mathematical interval
 * for a function (or a range of numbers), it's widely used in toria-math. <br>
 */
public class TRange {

    public static final TRange REEL_NUMBERS = new TRange(-Double.MAX_VALUE, Double.MAX_VALUE);

    public static final TRange ZERO_TO_ONE = new TRange(0d, 1d);

    public static final TRange MONE_TO_ONE = new TRange(-1d, 1d);


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    /**  <b>INCLUSIVE</b> END POINT OF THIS RANGE  */
    public final double left, right;


    public TRange(double inclusiveLeft, double inclusiveRight){
        if(inclusiveLeft >= inclusiveRight)
            throw new EqualBoundsException(inclusiveLeft, inclusiveRight);
        this.left = inclusiveLeft;
        this.right = inclusiveRight;
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * Returns a new range by taking the square root of this range's left and right boundary values.
     * @return a new range whose boundaries are the square roots of this range's boundaries
     */
    public TRange sqrt(){
        return new TRange(TMath.sqrt(this.left), TMath.sqrt(this.right));
    }



    /**
     * Returns a new and scaled version of this range.
     * @param scale any double, the range will be multiplied by this value
     * @return A new and scaled TRange
     */
    public TRange scale(double scale){
        return new TRange(this.left * scale, this.right * scale);
    }



    /**
     * Returns a new and shifted version of this range.
     * @param shift any double, this value will be added to range's boundaries.
     * @return A new and shifted TRange
     */
    public TRange shift(double shift){
        return new TRange(this.left + shift, this.right + shift);
    }



    /**
     * @param x any value
     * @return true if the value is in this range
     */
    public boolean isInRange(double x){
        return left <= x && x <= right;
    }


    /**
     * @return For a range [a,b] it returns b-a
     */
    public double size(){
        return this.right - this.left;
    }


    /////////////////////////////////////////////////////////////////


    /**
     * Uses {@link TRange#size()} and returns the range with the bigger size.
     * @param range1 any range
     * @param range2 any range
     * @return the range with the bigger size
     */
    public static TRange getBiggerRange(TRange range1, TRange range2){
        return (range1.size() > range2.size()) ? range1 : range2;
    }


    /////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("[%f, %f]", this.left, this.right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRange other = (TRange) o;
        return TMath.areEqual(this.left, other.left) && TMath.areEqual(this.right, other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }


}
