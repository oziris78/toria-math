package com.telek.telekmath.core.functions;


public class Range {

    public final double left, right;

    public Range(double inclusiveLeft, double inclusiveRight){
        this.left = inclusiveLeft;
        this.right = inclusiveRight;
    }



    @Override
    public String toString() {
        return String.format("[%f, %f]", this.left, this.right);
    }

}
