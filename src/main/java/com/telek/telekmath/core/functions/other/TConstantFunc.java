package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.TRange;

public class TConstantFunc extends AbstractFunction {


    private double constValue;

    public TConstantFunc(TRange range, double constValue) {
        super(range);
        this.constValue = constValue;
    }

    public TConstantFunc(double constValue){
        this(TRange.REEL_NUMBERS, constValue);
    }

    @Override
    public double value(double x) {
        return constValue;
    }

    @Override
    public TFunction derivative() {
        return new TFunction( new TConstantFunc(this.range, 0d) );
    }


}
