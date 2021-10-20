package com.telek.telekmath.core.functions.general;


public class TRange {

    public final double left, right;

    public static final TRange REEL_NUMBERS = new TRange(Double.MIN_VALUE, Double.MAX_VALUE);
    public static final TRange ZERO_TO_ONE = new TRange(0d, 1d);
    public static final TRange MONE_TO_ONE = new TRange(-1d, 1d);


    public TRange(double inclusiveLeft, double inclusiveRight){
        this.left = inclusiveLeft;
        this.right = inclusiveRight;
    }


    public boolean isInRange(double x){
        return left <= x && x <= right;
    }


    @Override
    public String toString() {
        return String.format("[%f, %f]", this.left, this.right);
    }

}
