package com.telek.telekmath.special.cryptography;

import com.telek.telekmath.special.DiscreteMath;

import java.util.ArrayList;


public class AsciiCipher {


    public static String codeAsciiCipher(String numberSeq, int base){
        String[] strArr = numberSeq.split("\\s+");
        ArrayList<Integer> numArr = new ArrayList<>();
        for(String s : strArr) numArr.add( Integer.parseInt(DiscreteMath.getInBase10(s, base)) );
        StringBuilder sb = new StringBuilder();
        for(int i : numArr) sb.append( String.valueOf((char) i) + " ");
        return sb.toString();
    }


    public static String decodeAsciiCipher(String charSeq, int base){
        ArrayList<Integer> numArr = new ArrayList<>();
        for(int i = 0; i < charSeq.length(); i++) numArr.add(Integer.parseInt(DiscreteMath.getNumberInBase((int) charSeq.charAt(i), base)));
        StringBuilder sb = new StringBuilder();
        for(int i : numArr) sb.append( String.valueOf(i) + " ");
        return sb.toString();
    }



}
