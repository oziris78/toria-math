package com.telek.telekmath.core.numbers;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.EqualBoundsException;

import java.util.Objects;


/**
 * An immutable range class. <br>
 * Even though this class represents a mathematical function's domain, it's used
 * in many other places throughout telek-math. <br>
 */
public class TRange {

    //////////////
    /*  FIELDS  */
    //////////////

    /**  <b>INCLUSIVE</b> END POINT OF THIS RANGE  */
    public final double left, right;


    /////////////////////
    /*  STATIC FIELDS  */
    /////////////////////


    public static final TRange REEL_NUMBERS = new TRange(-Double.MAX_VALUE, Double.MAX_VALUE);

    public static final TRange ZERO_TO_ONE = new TRange(0d, 1d);

    public static final TRange MONE_TO_ONE = new TRange(-1d, 1d);


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TRange(double inclusiveLeft, double inclusiveRight){
        if(inclusiveLeft >= inclusiveRight)
            throw new EqualBoundsException();
        this.left = inclusiveLeft;
        this.right = inclusiveRight;
    }



    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * Returns a new trange by taking the square root of this range's left and right boundary values.
     * @return a new range whose boundaries are the square root of this range's boundaries
     */
    public TRange getSqrt(){
        return new TRange(TMath.sqrt(this.left), TMath.sqrt(this.right));
    }



    /**
     * Returns a new and scaled version of this TRange.
     * @param scale any double, TRange will be multiplied by this value
     * @return A new and scaled TRange
     */
    public TRange getScaled(double scale){
        return new TRange(this.left * scale, this.right * scale);
    }



    /**
     * Returns a new and shifted version of this TRange.
     * @param shift any double, this value will be added to TRange's boundaries.
     * @return A new and shifted TRange
     */
    public TRange getShifter(double shift){
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
