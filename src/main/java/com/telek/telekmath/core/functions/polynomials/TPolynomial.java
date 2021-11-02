package com.telek.telekmath.core.functions.polynomials;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;

import java.util.*;



public class TPolynomial extends AbstractFunction {

    private ArrayList<PolynomialTerm> terms;

    /* CONSTRUCTORS */

    public TPolynomial(TRange range, ArrayList<PolynomialTerm> terms){
        super(range);
        this.terms = terms;
        this.sortPolynomial();
    }

    public TPolynomial(TRange range, PolynomialTerm... termsOfPolynomial){
        super(range);
        this.terms = new ArrayList<PolynomialTerm>();
        for(PolynomialTerm term : termsOfPolynomial)
            this.terms.add(term);
        this.sortPolynomial();
    }


    public TPolynomial(ArrayList<PolynomialTerm> terms){
        this(TRange.REEL_NUMBERS, terms);
    }

    public TPolynomial(PolynomialTerm... termsOfPolynomial){
        this(TRange.REEL_NUMBERS, termsOfPolynomial);
    }



    /* FUNCTIONS */


    /**
     * @param p2 any polynomial
     * @return thisPolynomial + p2
     */
    public TPolynomial add(TPolynomial p2){
        TPolynomial newPoly = new TPolynomial();
        int maxDegree = Math.max( this.getMaxDegree(), p2.getMaxDegree() );
        for(int i = 0; i <= maxDegree; i++){
            PolynomialTerm termToAdd = new PolynomialTerm();
            double newCoefficient = 0;
            if( this.hasTerm(i) ) newCoefficient += this.getCoefficientOfDegree(i); // check if p1 has coef for degree
            if( p2.hasTerm(i) ) newCoefficient += p2.getCoefficientOfDegree(i); // check for p2
            if( newCoefficient != 0) { // if any of them exists, add the term to the new poly
                termToAdd.setCoefficient(newCoefficient);
                termToAdd.setDegree(i);
                newPoly.terms.add(termToAdd);
            }
        }
        return getSortedPolynomial(newPoly);
    }


    /**
     * @param p2 any polynomial
     * @return thisPolynomial * p2
     */
    public TPolynomial multiply(TPolynomial p2){
        TPolynomial newPoly = new TPolynomial();
        for(int i = 0; i < this.terms.size(); i++){
            PolynomialTerm curTerm = this.terms.get(i);
            TPolynomial curPoly = multiplyTermWithPoly(p2, curTerm);
            newPoly = curPoly.add(newPoly);
        }
        return getSortedPolynomial(newPoly);
    }

    /**
     * @return P(x = value) which is the value of this polynomial
     */
    @Override
    public double value(double x){
        if( !this.range.isInRange(x) ) return 0;
        if(x==0) return this.getCoefficientOfDegree(0); // P(0) = a0
        double sum = 0;
        for(PolynomialTerm term : this.terms)
            sum += term.getCoefficient() * Math.pow(x, term.getDegree());
        return sum;
    }

    /**
     * This method searches if the polynomial term "m x^degree" exists in the polynomial.
     * If it does, it returns m. If it doesn't it returns 0
     * @param degree degree to search for
     * @return the coefficitent of m x^degree
     */
    public double getCoefficientOfDegree(int degree){
        double coef = 0;
        for(PolynomialTerm term : terms)
            if(term.getDegree() == degree) coef = term.getCoefficient();
        return coef;
    }


    // TODO: 02/11/2021 uncomment this for derivatives
//    /**
//     * @return the derivative of this polynomial, dP/dx or P'(x)
//     */
//    @Override
//    public TPolynomial derivative(){
//        TPolynomial newPoly = new TPolynomial();
//        for(PolynomialTerm term : this.terms){
//            PolynomialTerm derivedTerm = new PolynomialTerm( term.getCoefficient() * term.getDegree(), term.getDegree() - 1 );
//            if(derivedTerm.getCoefficient() != 0)
//                newPoly.terms.add(derivedTerm);
//        }
//        return getSortedPolynomial(newPoly);
//    }



    public ArrayList<PolynomialTerm> getTerms(){ return this.terms; }


    /* HELPER FUNCTIONS */

    private static TPolynomial multiplyTermWithPoly(TPolynomial poly, PolynomialTerm term){
        TPolynomial newPoly = new TPolynomial();
        for(int i = 0; i < poly.terms.size(); i++){
            PolynomialTerm temp = poly.terms.get(i);
            PolynomialTerm termToAdd = new PolynomialTerm( term.getCoefficient() * temp.getCoefficient(), term.getDegree() + temp.getDegree() );
            if(termToAdd.getCoefficient() != 0)
                newPoly.terms.add( termToAdd );
        }
        return newPoly;
    }

    private boolean hasTerm(int degree){
        boolean hasTerm = false;
        for(PolynomialTerm term : this.terms){
            if(term.getDegree() == degree){
                hasTerm = true;
                break;
            }
        }
        return hasTerm;
    }

    private int getMaxDegree(){
        int maxDegree = 0;
        for(PolynomialTerm term : this.terms){
            int currentDegree = term.getDegree();
            maxDegree = (currentDegree > maxDegree) ? currentDegree : maxDegree;
        }
        return maxDegree;
    }

    private TPolynomial getSortedPolynomial(TPolynomial poly){
        poly.terms.sort(new Comparator<PolynomialTerm>() {
            @Override
            public int compare(PolynomialTerm o1, PolynomialTerm o2) {
                return o1.getDegree() - o2.getDegree();
            }
        });
        return poly;
    }

    private void sortPolynomial(){
        this.terms.sort(new Comparator<PolynomialTerm>() {
            @Override
            public int compare(PolynomialTerm o1, PolynomialTerm o2) {
                return o1.getDegree() - o2.getDegree();
            }
        });
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuffer content = new StringBuffer();
        if(this.getTerms().isEmpty()) return "0";
        int arrSize = this.getTerms().size();
        for(int i = 0; i < arrSize; i++){
            PolynomialTerm curTerm = this.getTerms().get(i);
            if(curTerm.getDegree() == 0)
                content.append( getFormattedStringForTPolynomial(curTerm.getCoefficient(), -78) ); // special case
            else if(curTerm.getDegree() == 1)
                content.append( " " + getFormattedStringForTPolynomial(curTerm.getCoefficient(), 5) + " x" );
            else
                content.append( " " + getFormattedStringForTPolynomial(curTerm.getCoefficient(), 5) + " x^" + curTerm.getDegree() );
            if(i+1 != arrSize) {
                if( this.getTerms().get(i+1).getCoefficient() > 0) content.append(" +");
                else content.append("");
            }
        }
        return content.toString().trim();
    }


    private static String getFormattedStringForTPolynomial(double valToFormat, int decsAfterComma){
        if( decsAfterComma == -78) { // special case for terms with degree 0 aka "ax^0"
            if(valToFormat > 0) return ( (decsAfterComma <= 0) ? String.valueOf(valToFormat) :
                    String.format("%."+decsAfterComma+"f", valToFormat) );
            else return ( (decsAfterComma <= 0) ? String.valueOf(valToFormat) : String.format("%."+decsAfterComma+"f", valToFormat) );
        }
        if(valToFormat > 0) return ( (decsAfterComma <= 0) ? String.valueOf(valToFormat) :
                String.format("%."+decsAfterComma+"f", valToFormat) );
        else return ( (decsAfterComma <= 0) ? "- " + String.valueOf(-valToFormat) :
                "- " + String.format("%."+decsAfterComma+"f", -valToFormat) );
    }


}
