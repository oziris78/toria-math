package com.telek.telekmath.core.functions;


public interface IFunction {


    public double value(double value);
    public IFunction derivative();



/*
    public double value(double values);
    public boolean inRange(double value);
    public IFunction add(IFunction func);
    public IFunction substract(IFunction func);
    public IFunction multiply(IFunction func);
    public IFunction divide(IFunction func);
    public IFunction derivative();
    public IFunction derivative(int nthDerivative);
*/

}
