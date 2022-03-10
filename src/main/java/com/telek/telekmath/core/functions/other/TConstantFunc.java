package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import java.util.Objects;


public class TConstantFunc extends AbstractFunction {


    private final double constValue;

    public TConstantFunc(TRange range, double constValue) {
        super(range);
        this.constValue = constValue;
    }

    public TConstantFunc(double constValue){
        this(TRange.REEL_NUMBERS, constValue);
    }


    ///////////////
    /*  METHODS  */
    ///////////////

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return constValue;
    }

    @Override
    public TFunction derivative() {
        return new TFunction( new TConstantFunc(this.range, 0d) );
    }


    @Override
    public String toString() {
        return "y = " + constValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TConstantFunc that = (TConstantFunc) o;
        return Double.compare(that.constValue, constValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), constValue);
    }
}
