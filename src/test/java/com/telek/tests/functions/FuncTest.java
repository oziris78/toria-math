package com.telek.tests.functions;

import com.telek.telekmath.core.functions.TPolynomial;
import com.telek.telekmath.core.functions.TCompositeFunc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class FuncTest {

    @Test
    @DisplayName("tfunctionTest")
    void tfunctionTest() {
        TCompositeFunc f1 = new TCompositeFunc(new TPolynomial(1, 2));
        TCompositeFunc f2 = new TCompositeFunc(new TPolynomial(1, 5));
        f2.addProduct(new TPolynomial(1, 7));
        f2.addProduct(new TPolynomial(1, 7));
        System.out.println(f1);
        System.out.println(f2);

        TCompositeFunc cf1 = new TCompositeFunc(f1);
        TCompositeFunc cf2 = new TCompositeFunc(f2);

    }



}

