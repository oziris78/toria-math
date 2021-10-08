package com.telek.telekmath.core.numbers;


public class ComplexNumber {

    private double real, imaginary;

    public ComplexNumber(double real, double imaginary){
        this.setReal(real);
        this.setImaginary(imaginary);
    }

    public boolean hasImaginaryPart(){ return this.imaginary != 0; }
    public boolean hasRealPart(){ return this.real != 0; }

    public ComplexNumber add(ComplexNumber z2){
        return new ComplexNumber( this.real + z2.real, this.imaginary + z2.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber z2){
        return new ComplexNumber( this.real - z2.real, this.imaginary - z2.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber z2){
        return new ComplexNumber(
            (this.real * z2.real) - (this.imaginary * z2.imaginary),
            (this.real * z2.imaginary) + (this.imaginary * z2.real)
        );
    }

    public ComplexNumber conjugate(){
        return new ComplexNumber( this.getReal(), -this.getImaginary());
    }

    public ComplexNumber reciprocal(){
        double distFromOrigin = this.distanceFromOrigin();
        double distFromOrigin2 = distFromOrigin * distFromOrigin;
        return new ComplexNumber( this.getReal() / distFromOrigin2, -this.getImaginary() / distFromOrigin2);
    }

    public double distanceFromOrigin(){ return Math.sqrt( (this.getImaginary() * this.getImaginary()) + (this.getReal() * this.getReal()) ); }


    /*  GETTERS AND SETTERS  */
    public double getReal() { return real; }
    public double getImaginary() { return imaginary; }
    public void setReal(double real) { this.real = real; }
    public void setImaginary(double imaginary) { this.imaginary = imaginary; }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        double real = this.getReal();
        double imag = this.getImaginary();
        if(real != 0 && imag != 0) return String.format("%.5f + %.5f i", real, imag);
        else if(real != 0 && imag == 0) return String.format("%.5f", real);
        else if(real == 0 && imag != 0) return String.format("%.5f i", imag);
        else return "0";
    }


}
