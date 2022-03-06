package com.telek.telekmath.core.functions;


import com.telek.telekmath.core.numbers.TRange;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFunction that = (AbstractFunction) o;
        return Objects.equals(range, that.range);
    }


    @Override
    public int hashCode() {
        return Objects.hash(range);
    }


}
