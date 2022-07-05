package com.twistral.toriamath.special;


import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.TMathConsts;
import com.twistral.toriamath.utils.ToriaMathException.*;
import com.twistral.toriautils.plain.TStringUtils;

import java.util.HashMap;


public class DiscreteMath {

    /* No constructor */
    private DiscreteMath(){}


    /**
     * @param num any number
     * @return the prime factorization of num
     */
    public static String primeFactorization(long num){
        HashMap<Long, Long> factors = new HashMap<Long, Long>();
        if(num == 1) return "1";

        long myNum = num;
        while(myNum != 1){
            for(long i = 2; i <= myNum; i++){
                if( myNum % i == 0){
                    if(!factors.containsKey(i)) factors.put(i, 1L);
                    else factors.put(i, factors.get(i)+1 );
                    myNum /= i;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Long key : factors.keySet()) sb.append(String.format("%d^%d * ", key, factors.get(key)));
        String text = sb.substring(0, sb.length()-3);
        return text.replaceAll("\\^1", "");
    }





    /**
     * This functions exists only for educational purposes. <br>
     * If you want a gcd(a,b) function use {@link TMath#gcd(int, int)}
     * @param a any integer
     * @param b any integer
     * @return gcd(a,b) using the euclidean algorithm
     */
    public static int euclideanAlgorithm(int a, int b){
        int num1 = Math.max(a,b);
        int num2 = Math.min(a,b);
        int divided = (int) Math.floor(num1 / num2);
        int remainder = num1 - divided * num2;
        while(remainder != 0){
            num1 = num2;
            num2 = remainder;
            divided = (int) Math.floor(num1 / num2);
            remainder = num1 - divided * num2;
        }
        return num2;
    }




    public static int getInBase10(String numberStrInAnyBase, int baseOfTheNumber){
        return Integer.parseInt(numberStrInAnyBase, baseOfTheNumber);
    }


    public static String getNumberInBase(int numberInBase10, int base){
        if(base == 10)
            return String.valueOf(numberInBase10);
        StringBuilder sb = new StringBuilder();
        int num = numberInBase10;
        int divided = (int) Math.floor( num / base );
        int remainder = num - base * divided;
        sb.append(TStringUtils.convertNumberToUppercaseBaseString(remainder));
        while(divided != 0){
            num = divided;
            divided = (int) Math.floor( num / base );
            remainder = num - base * divided;
            sb.append(TStringUtils.convertNumberToUppercaseBaseString(remainder));
        }
        return sb.reverse().toString();
    }








}
