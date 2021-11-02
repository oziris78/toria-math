package com.telek.telekmath.core.functions;


import com.telek.telekmath.core.numbers.ComplexNumber;


public abstract class AbstractFunction {

    /*  FIELDS  */

    protected final TRange range;

    /*  CONSTRUCTORS  */

    public AbstractFunction(TRange range){
        this.range = range;
    }

    /*  METHODS  */

    public abstract double value(double x);
//    public abstract double value(ComplexNumber z);



    /*  GETTERS AND SETTERS  */

    public TRange getRange() { return range; }


}
