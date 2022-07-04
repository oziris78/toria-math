package com.twistral.toriamath.core.functions.oned;


import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriautils.containers.TArrays;
import java.util.ArrayList;
import java.util.Objects;



/**
 * A mutable function class to multiply and add functions together <br>
 * Use this class with elementary functions to create more complex functions. <br>
 */
public class CompositeFunc extends AbstractSingleVarFunc {

    private ArrayList<AbstractSingleVarFunc[]> innerProducts; // (f11 * f21 * ...) + (f21 * f22 * ...) + (f31 * f32 * ...) + ...


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public CompositeFunc(ArrayList<AbstractSingleVarFunc[]> innerProducts){
        super(TRange.REEL_NUMBERS);
        this.innerProducts = innerProducts;
    }


    public CompositeFunc(AbstractSingleVarFunc... functions){
        this(new ArrayList<>());
        this.addProduct(functions);
    }


    public CompositeFunc(){
        this(new ArrayList<>());
    }


    /**
     * Copy constructor
     * @param other other object to copy
     */
    public CompositeFunc(CompositeFunc other){
        super(TRange.REEL_NUMBERS);
        ArrayList<AbstractSingleVarFunc[]> innerProducts = new ArrayList<>();
        for(AbstractSingleVarFunc[] arr : other.innerProducts){
            AbstractSingleVarFunc[] copy = TArrays.getCopyOf(arr, AbstractSingleVarFunc.class);
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
    public void addProduct(AbstractSingleVarFunc... functions){
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
        // if this.innerProducts.size() is 0 it will skip it
        for(int j = 0; j < this.innerProducts.size(); j++){
            AbstractSingleVarFunc[] funcArr = this.innerProducts.get(j);

            double productResult = 1d;
            for (int i = 0; i < funcArr.length; i++) {
                productResult *= funcArr[i].value(x);
            }
            result += productResult;
        }
        return result;
    }


    @Override
    public CompositeFunc derivative() {
        CompositeFunc derivedFunction = new CompositeFunc();

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


    private CompositeFunc deriveProduct(AbstractSingleVarFunc[] product) {
        CompositeFunc derivedProduct = new CompositeFunc();
        int numberOfProducts = product.length;
        for (int i = 0; i < numberOfProducts; i++) {
            AbstractSingleVarFunc[] deriveToI = new AbstractSingleVarFunc[numberOfProducts];

            for (int j = 0; j < numberOfProducts; j++) {
                if(i == j)
                    deriveToI[j] = product[j].derivative();
                else
                    deriveToI[j] = product[j];
            }

            derivedProduct.addProduct( new CompositeFunc(deriveToI) );
        }

        return derivedProduct;
    }


    //////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return ">>> toString() method for TCompositeFunc has not been implemented yet... <<<";
//        StringBuilder sb = new StringBuilder();
//        int innerProductsSize = this.innerProducts.size();
//        for(int j = 0; j < innerProductsSize; j++){
//            SingleVarFunc[] funcArr = this.innerProducts.get(j);
//            int funcArrSize = funcArr.length;
//            sb.append("(");
//            for (int i = 0; i < funcArrSize; i++) {
//                sb.append(funcArr[i]);
//                if( i != funcArrSize - 1) sb.append(" * ");
//            }
//            sb.append(")");
//            if( j != innerProductsSize - 1) sb.append(" + ");
//        }
//        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeFunc that = (CompositeFunc) o;
        if(this.innerProducts.size() != that.innerProducts.size())
            return false;
        // (sinx + cosx) won't be equal to (cosx + sinx)
        return this.toString().equals(that.toString());
    }


    @Override
    public int hashCode() {
        return Objects.hash(innerProducts);
    }


}
