package com.telek.telekmath.core.functions;


import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekutils.containers.TArrays;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This class is the only mutable function class in telek-math. <br>
 * Use this class with elementary functions to create more complex functions.
 */
public class TFunction extends AbstractFunction {

    private ArrayList<AbstractFunction[]> innerProducts; // (f11 * f21 * ...) + (f21 * f22 * ...) + (f31 * f32 * ...) + ...


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TFunction(ArrayList<AbstractFunction[]> innerProducts){
        super(TRange.REEL_NUMBERS);
        this.innerProducts = innerProducts;
    }

    public TFunction(AbstractFunction... functions){
        this(new ArrayList<>());
        this.addProduct(functions);
    }

    public TFunction(){
        this(new ArrayList<>());
    }


    /**
     * Copy constructor
     * @param other other object to copy
     */
    public TFunction(TFunction other){
        super(TRange.REEL_NUMBERS);
        ArrayList<AbstractFunction[]> innerProducts = new ArrayList<>();
        for(AbstractFunction[] arr : other.innerProducts){
            AbstractFunction[] copy = TArrays.getCopyOf(arr, AbstractFunction.class);
            innerProducts.add(copy);
        }
        this.innerProducts = innerProducts;
    }



    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * Adds f1 * f2 * f3 * ...  to this function.
     * @param functions f1,f2,...
     */
    public void addProduct(AbstractFunction... functions){
        if(functions.length != 0){
            this.innerProducts.add(functions);
        }
    }



    /**
     * @param x any double
     * @return the value of ( f11(x) * f12(x) * ... ) + ( f21(x) * f22(x) * ... ) + ...
     */
    @Override
    public double value(double x){
        double result = 0d;
        for(int j = 0; j < this.innerProducts.size(); j++){
            AbstractFunction[] funcArr = this.innerProducts.get(j);

            double productResult = 1d;
            for (int i = 0; i < funcArr.length; i++) {
                productResult *= funcArr[i].value(x);
            }
            result += productResult;
        }
        return result;
    }


    @Override
    public TFunction derivative() {
        TFunction derivedFunction = new TFunction();

        int productSize = this.innerProducts.size();
        for (int i = 0; i < productSize; i++) {
            derivedFunction.addProduct(
                deriveProduct(this.innerProducts.get(i))
            );
        }

        return derivedFunction;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private TFunction deriveProduct(AbstractFunction[] product) {
        TFunction derivedProduct = new TFunction();
        int numberOfProducts = product.length;
        for (int i = 0; i < numberOfProducts; i++) {
            AbstractFunction[] deriveToI = new AbstractFunction[numberOfProducts];

            for (int j = 0; j < numberOfProducts; j++) {
                if(i == j)
                    deriveToI[j] = product[j].derivative();
                else
                    deriveToI[j] = product[j];
            }

            derivedProduct.addProduct( new TFunction(deriveToI) );
        }

        return derivedProduct;
    }


    //////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int innerProductsSize = this.innerProducts.size();
        for(int j = 0; j < innerProductsSize; j++){
            AbstractFunction[] funcArr = this.innerProducts.get(j);
            int funcArrSize = funcArr.length;
            sb.append("(");
            for (int i = 0; i < funcArrSize; i++) {
                sb.append(funcArr[i]);
                if( i != funcArrSize - 1) sb.append(" * ");
            }
            sb.append(")");
            if( j != innerProductsSize - 1) sb.append(" + ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TFunction that = (TFunction) o;
        int thisLen = this.innerProducts.size();
        int thatLen = that.innerProducts.size();
        if(thisLen != thatLen)
            return false;
        // (sinx + cosx) won't be equal to (cosx + sinx)
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(innerProducts);
    }
}
