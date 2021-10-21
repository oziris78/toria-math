package com.telek.telekmath.core.functions.general;


import com.telek.telekmath.exceptions.NotInRangeException;

public abstract class TFunction {

    /*  FIELDS  */

    protected final TRange range;

    public TFunction(TRange range){
        this.range = range;
    }


    public abstract double value(double x);

//    public abstract <T extends TFunction> T multiply(T func);
//    public abstract <T extends TFunction> T add(T func);

    public abstract TFunction derivative();





    protected void checkForRange(double x){
        if( !this.range.isInRange(x) )
            throw new NotInRangeException(range, x);
    }


}
