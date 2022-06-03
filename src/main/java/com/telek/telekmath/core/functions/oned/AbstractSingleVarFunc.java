package com.telek.telekmath.core.functions.oned;


import com.telek.telekmath.core.functions.TRange;

public abstract class AbstractSingleVarFunc {

    protected final TRange domain;

    public AbstractSingleVarFunc(TRange domain){
        this.domain = domain;
    }

    public abstract double value(double x);

    public abstract TCompositeFunc derivative();

    public TRange getDomain() {
        return this.domain;
    }

}
