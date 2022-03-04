package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.regression.SimpleRegression;
import com.telek.telekmath.advanced.statistics.regression.RegressionResult;
import com.telek.telekmath.utils.TMath;
import com.telek.telekutils.plain.TClassUtils;
import com.telek.tests.stats.exampledata.RegData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


public class SimpleRegressionTest {



    @Test
    @DisplayName("regressionTest")
    void regressionTest() {
        // double[]
        RegressionResult res = SimpleRegression.getResult(RegData.getDoubleArr(), 0.05d);
        check(res);
        // float[]
        res = SimpleRegression.getResult(RegData.getFloatArr(), 0.05d);
        check(res);
        // double[][]
        res = SimpleRegression.getResult(RegData.getDouble2Arr(), 0.05d);
        check(res);
        // float[][]
        res = SimpleRegression.getResult(RegData.getFloat2Arr(), 0.05d);
        check(res);
        // TPoint[]
        res = SimpleRegression.getResult(RegData.getPointArr(), 0.05d);
        check(res);
        // Number[]
        res = SimpleRegression.getResult(RegData.getNumberArr(), 0.05d);
        check(res);
        // any class array
        RegData.YearResult[] classArray = RegData.getYearResultArr();
        Field xField = TClassUtils.getField(RegData.YearResult.class, "numOfTourists");
        Field yField = TClassUtils.getField(RegData.YearResult.class, "income");
        xField.setAccessible(true);
        yField.setAccessible(true);
        res = SimpleRegression.getResult(classArray, xField, yField, 0.05d);
        check(res);

    }


    private void check(RegressionResult result){
        Assertions.assertTrue(TMath.areEqual(result.n, 10.0));
        Assertions.assertTrue(TMath.areEqual(result.sumX, 123.416));
        Assertions.assertTrue(TMath.areEqual(result.sumY, 84.966));
        Assertions.assertTrue(TMath.areEqual(result.sumX2, 1686.450104));
        Assertions.assertTrue(TMath.areEqual(result.sumY2, 789.8720940000001));
        Assertions.assertTrue(TMath.areEqual(result.sumXY, 1153.13759));
        Assertions.assertTrue(TMath.areEqual(result.sumE2, 1.0501878543865237));
        Assertions.assertTrue(TMath.areEqual(result.line.getCoefficientOfDegree(0), 0.597242));
        Assertions.assertTrue(TMath.areEqual(result.line.getCoefficientOfDegree(1), 0.640060));
        Assertions.assertTrue(TMath.areEqual(result.S2, 0.13127348179831547));
        Assertions.assertTrue(TMath.areEqual(result.S, 0.362316825165925));
        Assertions.assertTrue(TMath.areEqual(result.SRS, 66.89979054561368));
        Assertions.assertTrue(TMath.areEqual(result.STS, 67.94997840000019));
        Assertions.assertTrue(TMath.areEqual(result.SES, 1.0501878543865075));
        Assertions.assertTrue(TMath.areEqual(result.R2, 0.9845446918584082));
        Assertions.assertTrue(TMath.areEqual(result.r, 0.9922422546225333));
        Assertions.assertTrue(TMath.areEqual(result.SR, 0.04395353817043015));
        Assertions.assertTrue(TMath.areEqual(result.confIntOfCorrelationCoef.left, 0.890885));
        Assertions.assertTrue(TMath.areEqual(result.confIntOfCorrelationCoef.right, 1.093599));
        Assertions.assertTrue(result.hasPositiveDirection);
        Assertions.assertTrue(result.areTheyCorrelated);
    }



}
