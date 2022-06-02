package com.telek.telekmath.core.functions;


import com.telek.telekmath.core.functions.onevar.TCompositeFunc;


public abstract class SingleVarFunc {

    protected final TRange domain;

    public SingleVarFunc(TRange domain){
        this.domain = domain;
    }

    public abstract double value(double x);

    public abstract TCompositeFunc derivative();

    public TRange getDomain() {
        return this.domain;
    }

}
