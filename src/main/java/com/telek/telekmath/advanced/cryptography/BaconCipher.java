package com.telek.telekmath.advanced.cryptography;

import com.telek.telekmath.core.constants.CryptoConstants;
import com.telek.telekmath.utils.TelekMathException.*;

import java.util.Locale;

public class BaconCipher {

    /* No constructor */
    private BaconCipher(){}

    // look here for more info: https://www.youtube.com/watch?v=ctA38iXUpYg
    public static String decode(String code){
        final StringBuilder alphabetString = CryptoConstants.ENGLISH_ALPHABET;
        StringBuilder answer = new StringBuilder();
        String text = code.replaceAll("[^a-zA-Z]", ""); // remove all non-alphabetic words
        if(text.length() % 5 != 0) throw new NotAMultiplyOfNException("code's length", 5);
        for(int i = 0; i < text.length(); i++) {
            if( !alphabetString.toString().contains(String.valueOf(text.charAt(i)).toUpperCase(Locale.ROOT) ) )
                throw new HasNonLetterCharactersException();
        }
        for(int i = 0; i != text.length(); i+=5){
            StringBuilder currentCode = new StringBuilder();
            currentCode.append(alphabetString.toString().contains(String.valueOf(text.charAt(i))) ? "0" : "1");
            currentCode.append(alphabetString.toString().contains(String.valueOf(text.charAt(i+1))) ? "0" : "1");
            currentCode.append(alphabetString.toString().contains(String.valueOf(text.charAt(i+2))) ? "0" : "1");
            currentCode.append(alphabetString.toString().contains(String.valueOf(text.charAt(i+3))) ? "0" : "1");
            currentCode.append(alphabetString.toString().contains(String.valueOf(text.charAt(i+4))) ? "0" : "1");
            answer.append( alphabetString.toString().charAt( Integer.parseInt(currentCode.toString(), 2) ) );
        }
        return answer.toString();
    }




}
