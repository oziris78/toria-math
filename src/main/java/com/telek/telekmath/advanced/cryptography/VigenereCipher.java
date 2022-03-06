package com.telek.telekmath.advanced.cryptography;

import com.telek.telekmath.utils.TelekMathException.*;

import java.util.Locale;

public class VigenereCipher {


    /* No constructor */
    private VigenereCipher(){}

    public static String encode(String text, String key) {
        return vigenereCipher(text, key, true);
    }


    public static String decode(String text, String key) {
        return vigenereCipher(text, key, false);
    }



    /*  HELPERS  */

    private static String vigenereCipher(String text, String key, boolean doCode) {
        if(vigenereError(text, key)) throw new VigenereCipherException();
        StringBuilder answer = new StringBuilder();
        String newText = text.toUpperCase(Locale.ROOT), newKey = key.toUpperCase(Locale.ROOT);
        for (int i = 0, j = 0; i < newText.length(); i++) {
            char c = newText.charAt(i);
            if (c < 'A' || c > 'Z') {
                answer.append(" ");
                continue;
            }
            if(doCode) answer.append( (char) ((c + newKey.charAt(j) - 2 * 'A') % 26 + 'A'));
            else answer.append( (char) ((c - newKey.charAt(j) + 26) % 26 + 'A'));
            j = ++j % newKey.length();
        }
        return answer.toString();
    }


    private static boolean vigenereError(String text, String key) {
        return key.length() >= text.length() || key.matches("^.*[^a-zA-Z0-9 ].*$") || key.contains(" ");
    }



}
