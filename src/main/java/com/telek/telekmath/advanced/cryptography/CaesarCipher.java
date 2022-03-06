package com.telek.telekmath.advanced.cryptography;


import com.telek.telekmath.core.constants.CryptoConstants;

import java.util.Locale;

public class CaesarCipher {

    /* No constructor */
    private CaesarCipher(){}

    /*  METHODS  */

    public static String encode(String textToCode, int shift){
        return caesarCipher(textToCode, shift, true);
    }

    public static String decode(String textToDecode, int shift){
        return caesarCipher(textToDecode, shift, false);
    }

    /*  HELPERS  */

    private static String caesarCipher(String textToCode, int shift, boolean willBeCoded){
        final StringBuilder alphabetString = CryptoConstants.ENGLISH_ALPHABET;
        if(shift <= 0){ while (shift < 0){ shift += alphabetString.length(); } }
        if(shift == 0) return textToCode;
        String code = textToCode.toUpperCase(Locale.ROOT);
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < code.length(); i++){
            String currentLetter = String.valueOf(code.charAt(i));
            if(alphabetString.indexOf(currentLetter) == -1){
                if(Character.isLetter(currentLetter.charAt(0)))
                    answer.append(" ");
                else
                    answer.append(currentLetter);
                continue;
            }
            int index = alphabetString.indexOf(currentLetter);
            index = willBeCoded ? index + shift : index - shift;
            index = (index < 0) ? index + alphabetString.length() : index;
            index %= alphabetString.length();
            if(Character.isUpperCase(textToCode.charAt(i))) answer.append( Character.toUpperCase(alphabetString.charAt(index)) );
            else answer.append( Character.toLowerCase(alphabetString.charAt(index)) );
        }
        return answer.toString();
    }



}
