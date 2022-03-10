package com.telek.tests.functions;

import com.telek.telekmath.core.functions.amnfuncs.*;
import com.telek.telekmath.core.functions.other.*;
import com.telek.telekmath.core.numbers.TRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FuncEqualityTest {



    @Test
    @DisplayName("amnFuncTest")
    void amnFuncTest() {
        Assertions.assertEquals(new TCeil(new TRange(1, 2), 1.5, -2.5, 3), new TCeil(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TCeil(new TRange(1, 2), 1.5, -2.5), new TCeil(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TCeil(new TRange(1, 2), 1.5), new TCeil(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TCeil(new TRange(1, 2)), new TCeil(new TRange(1, 2)));
        Assertions.assertEquals(new TCeil(1.5, -2.5, 3), new TCeil(1.5, -2.5, 3));
        Assertions.assertEquals(new TCeil(1.5, -2.5), new TCeil(1.5, -2.5));
        Assertions.assertEquals(new TCeil(1.5), new TCeil(1.5));
        Assertions.assertEquals(new TCeil(), new TCeil());


        Assertions.assertEquals(new TRectFunc(new TRange(1, 2), 1.5, -2.5, 3), new TRectFunc(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TRectFunc(new TRange(1, 2), 1.5, -2.5), new TRectFunc(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TRectFunc(new TRange(1, 2), 1.5), new TRectFunc(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TRectFunc(new TRange(1, 2)), new TRectFunc(new TRange(1, 2)));
        Assertions.assertEquals(new TRectFunc(1.5, -2.5, 3), new TRectFunc(1.5, -2.5, 3));
        Assertions.assertEquals(new TRectFunc(1.5, -2.5), new TRectFunc(1.5, -2.5));
        Assertions.assertEquals(new TRectFunc(1.5), new TRectFunc(1.5));
        Assertions.assertEquals(new TRectFunc(), new TRectFunc());


        Assertions.assertEquals(new TSin(new TRange(1, 2), 1.5, -2.5, 3), new TSin(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TSin(new TRange(1, 2), 1.5, -2.5), new TSin(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TSin(new TRange(1, 2), 1.5), new TSin(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TSin(new TRange(1, 2)), new TSin(new TRange(1, 2)));
        Assertions.assertEquals(new TSin(1.5, -2.5, 3), new TSin(1.5, -2.5, 3));
        Assertions.assertEquals(new TSin(1.5, -2.5), new TSin(1.5, -2.5));
        Assertions.assertEquals(new TSin(1.5), new TSin(1.5));
        Assertions.assertEquals(new TSin(), new TSin());


        Assertions.assertEquals(new TTan(new TRange(1, 2), 1.5, -2.5, 3), new TTan(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TTan(new TRange(1, 2), 1.5, -2.5), new TTan(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TTan(new TRange(1, 2), 1.5), new TTan(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TTan(new TRange(1, 2)), new TTan(new TRange(1, 2)));
        Assertions.assertEquals(new TTan(1.5, -2.5, 3), new TTan(1.5, -2.5, 3));
        Assertions.assertEquals(new TTan(1.5, -2.5), new TTan(1.5, -2.5));
        Assertions.assertEquals(new TTan(1.5), new TTan(1.5));
        Assertions.assertEquals(new TTan(), new TTan());


        Assertions.assertEquals(new TDiracDeltaFunc(new TRange(1, 2), 1.5, -2.5, 3), new TDiracDeltaFunc(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TDiracDeltaFunc(new TRange(1, 2), 1.5, -2.5), new TDiracDeltaFunc(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TDiracDeltaFunc(new TRange(1, 2), 1.5), new TDiracDeltaFunc(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TDiracDeltaFunc(new TRange(1, 2)), new TDiracDeltaFunc(new TRange(1, 2)));
        Assertions.assertEquals(new TDiracDeltaFunc(1.5, -2.5, 3), new TDiracDeltaFunc(1.5, -2.5, 3));
        Assertions.assertEquals(new TDiracDeltaFunc(1.5, -2.5), new TDiracDeltaFunc(1.5, -2.5));
        Assertions.assertEquals(new TDiracDeltaFunc(1.5), new TDiracDeltaFunc(1.5));
        Assertions.assertEquals(new TDiracDeltaFunc(), new TDiracDeltaFunc());


        Assertions.assertEquals(new TCos(new TRange(1, 2), 1.5, -2.5, 3), new TCos(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TCos(new TRange(1, 2), 1.5, -2.5), new TCos(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TCos(new TRange(1, 2), 1.5), new TCos(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TCos(new TRange(1, 2)), new TCos(new TRange(1, 2)));
        Assertions.assertEquals(new TCos(1.5, -2.5, 3), new TCos(1.5, -2.5, 3));
        Assertions.assertEquals(new TCos(1.5, -2.5), new TCos(1.5, -2.5));
        Assertions.assertEquals(new TCos(1.5), new TCos(1.5));
        Assertions.assertEquals(new TCos(), new TCos());


        Assertions.assertEquals(new TSinh(new TRange(1, 2), 1.5, -2.5, 3), new TSinh(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TSinh(new TRange(1, 2), 1.5, -2.5), new TSinh(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TSinh(new TRange(1, 2), 1.5), new TSinh(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TSinh(new TRange(1, 2)), new TSinh(new TRange(1, 2)));
        Assertions.assertEquals(new TSinh(1.5, -2.5, 3), new TSinh(1.5, -2.5, 3));
        Assertions.assertEquals(new TSinh(1.5, -2.5), new TSinh(1.5, -2.5));
        Assertions.assertEquals(new TSinh(1.5), new TSinh(1.5));
        Assertions.assertEquals(new TSinh(), new TSinh());


        Assertions.assertEquals(new TCosh(new TRange(1, 2), 1.5, -2.5, 3), new TCosh(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TCosh(new TRange(1, 2), 1.5, -2.5), new TCosh(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TCosh(new TRange(1, 2), 1.5), new TCosh(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TCosh(new TRange(1, 2)), new TCosh(new TRange(1, 2)));
        Assertions.assertEquals(new TCosh(1.5, -2.5, 3), new TCosh(1.5, -2.5, 3));
        Assertions.assertEquals(new TCosh(1.5, -2.5), new TCosh(1.5, -2.5));
        Assertions.assertEquals(new TCosh(1.5), new TCosh(1.5));
        Assertions.assertEquals(new TCosh(), new TCosh());


        Assertions.assertEquals(new TFloor(new TRange(1, 2), 1.5, -2.5, 3), new TFloor(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TFloor(new TRange(1, 2), 1.5, -2.5), new TFloor(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TFloor(new TRange(1, 2), 1.5), new TFloor(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TFloor(new TRange(1, 2)), new TFloor(new TRange(1, 2)));
        Assertions.assertEquals(new TFloor(1.5, -2.5, 3), new TFloor(1.5, -2.5, 3));
        Assertions.assertEquals(new TFloor(1.5, -2.5), new TFloor(1.5, -2.5));
        Assertions.assertEquals(new TFloor(1.5), new TFloor(1.5));
        Assertions.assertEquals(new TFloor(), new TFloor());


        Assertions.assertEquals(new THeavisideFunc(new TRange(1, 2), 1.5, -2.5, 3), new THeavisideFunc(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new THeavisideFunc(new TRange(1, 2), 1.5, -2.5), new THeavisideFunc(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new THeavisideFunc(new TRange(1, 2), 1.5), new THeavisideFunc(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new THeavisideFunc(new TRange(1, 2)), new THeavisideFunc(new TRange(1, 2)));
        Assertions.assertEquals(new THeavisideFunc(1.5, -2.5, 3), new THeavisideFunc(1.5, -2.5, 3));
        Assertions.assertEquals(new THeavisideFunc(1.5, -2.5), new THeavisideFunc(1.5, -2.5));
        Assertions.assertEquals(new THeavisideFunc(1.5), new THeavisideFunc(1.5));
        Assertions.assertEquals(new THeavisideFunc(), new THeavisideFunc());


        Assertions.assertEquals(new TSign(new TRange(1, 2), 1.5, -2.5, 3), new TSign(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TSign(new TRange(1, 2), 1.5, -2.5), new TSign(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TSign(new TRange(1, 2), 1.5), new TSign(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TSign(new TRange(1, 2)), new TSign(new TRange(1, 2)));
        Assertions.assertEquals(new TSign(1.5, -2.5, 3), new TSign(1.5, -2.5, 3));
        Assertions.assertEquals(new TSign(1.5, -2.5), new TSign(1.5, -2.5));
        Assertions.assertEquals(new TSign(1.5), new TSign(1.5));
        Assertions.assertEquals(new TSign(), new TSign());
    }

    @Test
    @DisplayName("otherFuncTest")
    void otherFuncTest() {
        Assertions.assertEquals(new TConstantFunc(new TRange(1,2), 20), new TConstantFunc(new TRange(1,2), 20));
        Assertions.assertEquals(new TConstantFunc(20), new TConstantFunc(20));

        Assertions.assertEquals(new TInverseFunc(new TRange(1, 2), 2d, 1), new TInverseFunc(new TRange(1, 2), 2d, 1));
        Assertions.assertEquals(new TInverseFunc(new TRange(1, 2), 2d), new TInverseFunc(new TRange(1, 2), 2d));
        Assertions.assertEquals(new TInverseFunc(new TRange(1, 2)), new TInverseFunc(new TRange(1, 2)));
        Assertions.assertEquals(new TInverseFunc(2d, 1), new TInverseFunc(2d, 1));
        Assertions.assertEquals(new TInverseFunc(2d), new TInverseFunc(2d));
        Assertions.assertEquals(new TInverseFunc(), new TInverseFunc());

        Assertions.assertEquals(new TLog(new TRange(1,2), 3, 2, 1), new TLog(new TRange(1,2), 3, 2, 1));
        Assertions.assertEquals(new TLog(new TRange(1,2), 3, 2), new TLog(new TRange(1,2), 3, 2));
        Assertions.assertEquals(new TLog(new TRange(1,2), 3), new TLog(new TRange(1,2), 3));
        Assertions.assertEquals(new TLog(new TRange(1,2)), new TLog(new TRange(1,2)));
        Assertions.assertEquals(new TLog(3, 2, 1), new TLog(3, 2, 1));
        Assertions.assertEquals(new TLog(3, 2), new TLog(3, 2));
        Assertions.assertEquals(new TLog(3), new TLog(3));
        Assertions.assertEquals(new TLog(), new TLog());


        Assertions.assertEquals(new TPolynomial(new TRange(1,2), 1, 2, 3, 4, 5, 6, 7),
                new TPolynomial(new TRange(1,2), 1, 2, 3, 4, 5, 6, 7));
        Assertions.assertEquals(new TPolynomial(1, 2, 3, 4, 5, 6, 7), new TPolynomial(1, 2, 3, 4, 5, 6, 7));

        Assertions.assertEquals(new TPowerFunc(new TRange(1, 2), 1.5, -2.5, 3), new TPowerFunc(new TRange(1, 2), 1.5, -2.5, 3));
        Assertions.assertEquals(new TPowerFunc(new TRange(1, 2), 1.5, -2.5), new TPowerFunc(new TRange(1, 2), 1.5, -2.5));
        Assertions.assertEquals(new TPowerFunc(new TRange(1, 2), 1.5), new TPowerFunc(new TRange(1, 2), 1.5));
        Assertions.assertEquals(new TPowerFunc(new TRange(1, 2)), new TPowerFunc(new TRange(1, 2)));
        Assertions.assertEquals(new TPowerFunc(1.5, -2.5, 3), new TPowerFunc(1.5, -2.5, 3));
        Assertions.assertEquals(new TPowerFunc(1.5, -2.5), new TPowerFunc(1.5, -2.5));
        Assertions.assertEquals(new TPowerFunc(1.5), new TPowerFunc(1.5));
        Assertions.assertEquals(new TPowerFunc(), new TPowerFunc());


    }

}
