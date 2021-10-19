package com.telek.telekmath.special;

import com.telek.telekmath.core.numbers.ComplexNumber;

public class EquationSolver {



    /**
     * @param a any double
     * @param b any double
     * @return The solution of ax + b = 0
     */
    public static double solveLinear(double a, double b){
        return -b / a;
    }


    /**
     * @param a any double
     * @param b any double
     * @param c any double
     * @return The solution of ax^2 + bx + c = 0 as a ComplexNumber[]
     */
    public static ComplexNumber[] solveQuadratic(double a, double b, double c){
        double det = (b * b) - (4 * a * c);
        double a2 = 2 * a;
        if(det > 0){
            double sqrtDet = Math.sqrt(det);
            return new ComplexNumber[]{ new ComplexNumber( (-b + sqrtDet) / a2, 0d), new ComplexNumber( (-b - sqrtDet) / a2, 0d) };
        }
        else if(det == 0){
            return new ComplexNumber[]{ new ComplexNumber(-b / a2, 0d) };
        }
        else{ // det < 0
            double sqrtDet = Math.sqrt(-det);
            double ba2 = -b / a2;
            return new ComplexNumber[]{ new ComplexNumber( ba2, sqrtDet / a2 ), new ComplexNumber( ba2, -sqrtDet / a2 ) };
        }
    }




    /**
     *
     * @param a any double
     * @param b any double
     * @param c any double
     * @param d any double
     * @return The solution of ax^3 + bx^2 + cx + d = 0
     */
    public static double solveCubic(double a, double b, double c, double d){
        return 0;
    }



    /**
     *
     * @param a any double
     * @param b any double
     * @param c any double
     * @param d any double
     * @param e any double
     * @return The solution of ax^4 + bx^3 + cx^2 + dx + e = 0
     */
    public static double solveQuartic(double a, double b, double c, double d, double e){
        return 0;
    }


}
