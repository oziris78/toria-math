package com.telek.tests;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.amnfuncs.TCos;
import com.telek.telekmath.core.functions.amnfuncs.TSin;
import com.telek.telekmath.core.functions.other.TLog;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekutils.plain.TCollections;


public class FuncDerivativeTest {

    public static void main(String[] args) {

        TFunction func = new TFunction();
        func.addProduct(new TLog(), new TSin(), new TCos(50));
        func.addProduct(new TLog(10), new TSin(10), new TCos(2));
        func.addProduct(new TPolynomial(TCollections.doubleArr(1, 1, 1, 1, 1, 1, 1)), new TLog());

        TFunction f1 = func.derivative();
        TFunction f2 = f1.derivative();
        TFunction f3 = f2.derivative();
        TFunction f4 = f3.derivative();

        System.out.println(func.value(10));
        System.out.println(f1.value(10));
        System.out.println(f2.value(10));
        System.out.println(f3.value(10));
        System.out.println(f4.value(10));

    }

}
