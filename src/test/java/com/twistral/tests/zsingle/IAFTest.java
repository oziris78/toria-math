package com.twistral.tests.zsingle;

import com.twistral.toriamath.core.constants.TMathConsts;
import com.twistral.toriamath.special.IAF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class IAFTest {

    @Test
    @DisplayName("iafTest")
    void iafTest() {

        ArrayList<double[]> resTriangle1 = IAF.mastersFormula(10,20,30,40,50,60);
        ArrayList<double[]> resTriangle2 = IAF.mastersFormula(TMathConsts.PI, TMathConsts.E, TMathConsts.GOLDEN_RATIO);
        ArrayList<double[]> resTriangle3 = IAF.mastersFormula(789,29814,30650,484851,591981);
        ArrayList<double[]> resTriangle4 = IAF.mastersFormula(1,4,9,16,25,36,49,64,81);

        String res1 = IAF.stringifyMastersFormula(resTriangle1);
        String res2 = IAF.stringifyMastersFormula(resTriangle2);
        String res3 = IAF.stringifyMastersFormula(resTriangle3);
        String res4 = IAF.stringifyMastersFormula(resTriangle4);

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);



    }

}
