package com.telek.telekmath.advanced.distributions;


public abstract class Experiment {

    protected double E, Var;

    public double getE() { return E; }
    public double getE2() { return (this.E * this.E) + this.Var; }
    public double getVAR() { return Var; }

}
