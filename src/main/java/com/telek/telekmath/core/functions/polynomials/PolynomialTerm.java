package com.telek.telekmath.core.functions.polynomials;

public class PolynomialTerm{

    private int degree;
    private double coefficient;


    public PolynomialTerm(){
        this(0d, 0);
    }

    public PolynomialTerm(double coefficient, int degree){
        this.degree = degree;
        this.coefficient = coefficient;
    }

    /**
     * @param coefficient coefficient of the term
     * @param degree degree of the term
     * @return A new PolynomialTerm using the parameters
     */
    public static PolynomialTerm term(double coefficient, int degree){
        return new PolynomialTerm(coefficient, degree);
    }

    public int getDegree() { return degree; }
    public void setDegree(int degree) { this.degree = degree; }
    public double getCoefficient() { return coefficient; }
    public void setCoefficient(double coefficient) { this.coefficient = coefficient; }

}