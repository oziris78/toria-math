package com.telek.telekmath.core.functions.twod;


import com.telek.telekmath.core.functions.TRange;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TwoVarFunc {


    private BiFunction<Double, Double, Double> func;
    private final TRange xDomain, yDomain;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public TwoVarFunc(TRange xDomain, TRange yDomain, BiFunction<Double, Double, Double> func){
        this.xDomain = xDomain;
        this.yDomain = yDomain;
        this.func = func;
    }


    public TwoVarFunc(BiFunction<Double, Double, Double> func){
        this(TRange.REEL_NUMBERS, TRange.REEL_NUMBERS, func);
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public double value(double x, double y){
        if( !xDomain.isInRange(x) || !yDomain.isInRange(y) )
            return 0d;

        return this.func.apply(x, y);
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public TRange getxDomain() {
        return xDomain;
    }


    public TRange getyDomain() {
        return yDomain;
    }

    //////////////////////
    /*  OBJECT METHODS  */
    //////////////////////


    @Override
    public String toString() {
        return "TwoVarFunc{" +
                "func=" + func +
                ", xDomain=" + xDomain +
                ", yDomain=" + yDomain +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoVarFunc that = (TwoVarFunc) o;
        return Objects.equals(func, that.func) &&
                Objects.equals(xDomain, that.xDomain) &&
                Objects.equals(yDomain, that.yDomain);
    }


    @Override
    public int hashCode() {
        return Objects.hash(func, xDomain, yDomain);
    }


}
