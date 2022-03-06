package com.telek.telekmath.special;


import java.util.ArrayList;


/**  INTENSE ALGORITHM FINDER  */
public class IAF {

    /* No constructor */
    private IAF(){}


    public static ArrayList<double[]> mastersFormula(double... sequence){
        int elemCount = sequence.length;
        ArrayList<double[]> triangle = new ArrayList<>();
        triangle.add(sequence.clone());
        for (int i = 0; i < elemCount-1; i++) {
            double[] last = triangle.get( triangle.size()-1 );
            double[] down = new double[elemCount-i-1];
            for (int j = 0; j < down.length; j++) {
                down[j] = last[j+1] - last[j];
            }
            triangle.add(down);
        }
        return triangle;
    }


    public static String stringifyMastersFormula(ArrayList<double[]> triangle){
        int elemCount = triangle.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elemCount; i++) {
            double coefficient = triangle.get(i)[0];
            if(coefficient != 0){
                sb.append(String.format("%f * nCr(n,%d)", coefficient, i));
                if(i+1 != elemCount) sb.append(" + ");
            }
        }
        if(sb.toString().endsWith(" + ")) sb.replace( sb.length()-3, sb.length(),"");
        return sb.toString();
    }



}

