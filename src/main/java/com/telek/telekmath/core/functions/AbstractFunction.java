package com.telek.telekmath.core.functions;


public abstract class AbstractFunction {

    protected final TRange range;

    public AbstractFunction(TRange range){
        this.range = range;
    }

    public abstract double value(double x);
    public abstract TCompositeFunc derivative();

    public TRange getRange() {
        return range;
    }


}
