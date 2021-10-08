package com.telek.telekmath.special.distributions;


public abstract class Experiment {

    private ExpResult expResult = new ExpResult();

    protected void setE(double E) { expResult.setE(E); }
    protected void setE2(double E_2) { expResult.setE2(E_2);}
    protected void setVar(double Var) { expResult.setVar(Var);}

    public double getE(){ return expResult.getE(); }
    public double getE2(){ return expResult.getE2(); }
    public double getVar(){ return expResult.getVar(); }

}
