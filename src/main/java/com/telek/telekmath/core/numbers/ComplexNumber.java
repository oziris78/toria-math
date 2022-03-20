package com.telek.telekmath.core.numbers;


import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * An immutable complex number class. <br>
 */
public class ComplexNumber {

    private final double real, imaginary;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }




    ///////////////
    /*  METHODS  */
    ///////////////


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

    public ComplexNumber divide(ComplexNumber z2){
        ComplexNumber up = this.multiply(z2.conjugate()); // z1 * z2conj
        final double divid = z2.getImaginary() * z2.getImaginary() + z2.getReal() * z2.getReal();
        return new ComplexNumber(up.real / divid, up.imaginary / divid);
    }

    public ComplexNumber reciprocal(){
        double distFromOrigin = this.distanceFromOrigin();
        double distFromOrigin2 = distFromOrigin * distFromOrigin;
        return new ComplexNumber( this.getReal() / distFromOrigin2, -this.getImaginary() / distFromOrigin2);
    }

    public double distanceFromOrigin(){
        return Math.sqrt( (this.getImaginary() * this.getImaginary()) + (this.getReal() * this.getReal()) );
    }


    /*  GETTERS AND SETTERS  */
    public double getReal() { return real; }
    public double getImaginary() { return imaginary; }


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return TMath.areEqual(that.real, real) && TMath.areEqual(that.imaginary, imaginary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }



}
