package com.telek.telekmath.advanced.cryptography;

import com.telek.telekmath.special.DiscreteMath;

import java.util.ArrayList;


public class AsciiCipher {


    /* No constructor */
    private AsciiCipher(){}

    public static String decode(String numberSeq, int base){
        String[] strArr = numberSeq.split("\\s+");
        ArrayList<Integer> numArr = new ArrayList<>();
        for(String s : strArr) numArr.add(DiscreteMath.getInBase10(s, base));
        StringBuilder sb = new StringBuilder();
        for(int i : numArr) sb.append( ((char) i) + " ");
        return sb.toString().trim();
    }


    public static String encode(String charSeq, int base){
        ArrayList<Integer> numArr = new ArrayList<>();
        for(int i = 0; i < charSeq.length(); i++)
            numArr.add(Integer.parseInt(DiscreteMath.getNumberInBase(charSeq.charAt(i), base)));
        StringBuilder sb = new StringBuilder();
        for(int i : numArr)
            sb.append( i + " ");
        return sb.toString().trim();
    }



}
