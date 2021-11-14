package com.telek.telekmath.core.functions;


public abstract class AbstractFunction {

    /*  FIELDS  */
    protected TRange range;


    /*  CONSTRUCTORS  */
    public AbstractFunction(TRange range){ this.range = range; }


    /*  METHODS  */
    public abstract double value(double x);
    public abstract TFunction derivative();


    /*  GETTERS AND SETTERS  */
    public void setRange(TRange range) {this.range = range;}
    public TRange getRange() { return range; }

}
