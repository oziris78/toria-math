package com.telek.tests;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekutils.plain.TCollections;
import com.telek.telekmath.special.IAF;
import java.util.ArrayList;


public class IAFTest {


    public static void main(String[] args) throws Exception {


        ArrayList<double[]> resTriangle1 = IAF.mastersFormula(TCollections.doubleArr(10,20,30,40,50,60));
        ArrayList<double[]> resTriangle2 = IAF.mastersFormula(TCollections.doubleArr(TMathConstants.PI, TMathConstants.E, TMathConstants.GOLDEN_RATIO));
        ArrayList<double[]> resTriangle3 = IAF.mastersFormula(TCollections.doubleArr(789,29814,30650,484851,591981));
        ArrayList<double[]> resTriangle4 = IAF.mastersFormula(TCollections.doubleArr(1,4,9,16,25,36,49,64,81));

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
