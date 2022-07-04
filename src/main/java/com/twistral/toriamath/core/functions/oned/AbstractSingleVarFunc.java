package com.twistral.toriamath.core.functions.oned;


import com.twistral.toriamath.core.functions.TRange;

public abstract class AbstractSingleVarFunc {

    protected final TRange domain;

    public AbstractSingleVarFunc(TRange domain){
        this.domain = domain;
    }

    public abstract double value(double x);

    public abstract CompositeFunc derivative();

    public TRange getDomain() {
        return this.domain;
    }

}
