package com.telek.telekmath.special;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.CryptoConstants;
import com.telek.telekmath.utils.TelekMathException.*;
import java.util.HashMap;


public class DiscreteMath {

    /* No constructor */
    private DiscreteMath(){}

    /**
     * Returns the prime factorization of num as a HashMap object, use TUtils.stringifyFactors() to
     * get this map as a String object
     * @param num any number
     * @return The prime factorization of num
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
        if(base == 10) return String.valueOf(numberInBase10);
        HashMap<Integer, String> dict = getHashMapWithBase(base);
        StringBuilder sb = new StringBuilder();
        int num = numberInBase10;
        int divided = (int) Math.floor( num / base );
        int remainder = num - base * divided;
        sb.append( dict.get(remainder) );
        while(divided != 0){
            num = divided;
            divided = (int) Math.floor( num / base );
            remainder = num - base * divided;
            sb.append( dict.get(remainder) );
        }
        return sb.reverse().toString();
    }




    /*  HELPERS  */

    private static HashMap<Integer, String> getHashMapWithBase(int base){
        if(base <= 10){
            HashMap<Integer, String> mapToReturn = new HashMap<>();
            for(int i = 0; i < base; i++)  mapToReturn.put(i, String.valueOf(i));
            return mapToReturn;
        }
        else if(base == 11) return CryptoConstants.baseEleven;
        else if(base == 12) return CryptoConstants.baseTwelve;
        else if(base == 13) return CryptoConstants.baseThirteen;
        else if(base == 14) return CryptoConstants.baseFourteen;
        else if(base == 15) return CryptoConstants.baseFifteen;
        else if(base == 16) return CryptoConstants.baseSixteen;
        else throw new InvalidValueException("base", base);
    }




}
