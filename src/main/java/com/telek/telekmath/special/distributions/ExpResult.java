package com.telek.telekmath.special.distributions;


public final class ExpResult {

    private double E, E_2, VAR;

    ExpResult(){
        this(0d, 0d, 0d);
    }

    ExpResult(double E, double E_2, double VAR){
        this.E = E;
        this.E_2 = E_2;
        this.VAR = VAR;
    }

    protected void setE(double e) { E = e; }
    protected void setE2(double e_2) { E_2 = e_2; }
    protected void setVar(double VAR) { this.VAR = VAR; }

    public double getE() { return E; }
    public double getE2() { return E_2; }
    public double getVar() { return VAR; }
}
