package com.telek.telekmath.special;


import java.util.HashMap;


public class DiscreteMath {


    /**
     * Returns the prime factorization of num as a HashMap object, use TUtils.stringifyFactors() to
     * get this map as a String object
     * @param num any number
     * @return The prime factorization of num
     */
    public static HashMap<Long, Long> primeFactorization(long num){
        HashMap<Long, Long> factors = new HashMap<Long, Long>();
        if(num == 1) {
            factors.put(1L,1L);
            return factors;
        }
        long myNum = new Long(num).longValue();
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
        return factors;
    }


    /**
     * Returns gcd(a,b) while printing the output
     * @param a any integer
     * @param b any integer
     * @return greatest common divisor of a and b using the Euclidean Algorithm
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



}
