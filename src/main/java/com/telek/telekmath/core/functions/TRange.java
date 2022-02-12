package com.telek.telekmath.core.functions;


import com.telek.telekmath.utils.TMath;

import java.util.Objects;

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
    public static final TRange BYTE_RANGE = new TRange(Byte.MIN_VALUE, Byte.MAX_VALUE);


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TRange(double inclusiveLeft, double inclusiveRight){
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
        TRange r1 = (TRange) o;
        return TMath.areEqual(left, r1.left) && TMath.areEqual(right, r1.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }


}
