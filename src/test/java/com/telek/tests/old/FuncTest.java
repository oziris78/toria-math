package com.telek.tests.old;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.other.TLogFunc;
import com.telek.telekmath.core.functions.amnfuncs.TCos;
import com.telek.telekmath.core.functions.amnfuncs.TSin;

public class FuncTest {


    public static void main(String[] args) {

        TLogFunc log = new TLogFunc(); // ln(x)
        TSin sin = new TSin(5); // sin(5x)
        TCos cos = new TCos(-5); // cos(-5x)

        TFunction myFunc = new TFunction(
            log, sin, cos
        );

        myFunc.addProduct(sin, cos);
        // myFunc =  ln(x) * sin(5x) * cos(-5x) + sin(5x) * cos(-5x)

        System.out.println(myFunc);

        System.out.println(myFunc.value(0)); // 0
        System.out.println(myFunc.value(TMathConstants.PI / 10d)); // 0



    }


}
