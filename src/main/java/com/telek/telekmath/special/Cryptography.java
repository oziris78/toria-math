package com.telek.telekmath.special;

import static com.telek.telekmath.exceptions.TelekMathException.*;
import com.telek.telekmath.core.constants.CryptoConstants;
import com.telek.telekmath.exceptions.*;
import java.util.*;


// Crpytography
public class Cryptography {

    private static StringBuilder alphabetString = CryptoConstants.ENGLISH_ALPHABET;


    public static String codeCaesarCipher(String textToCode, int shift){
        return caesarCipher(textToCode, shift, true);
    }

    public static String decodeCaesarCipher(String textToDecode, int shift){
        return caesarCipher(textToDecode, shift, false);
    }

    public static String decodeBaconCypher(String code){
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


    public static String codeVigenereCipher(String text, String key) {
        if(vigenereError(text, key)) return "Invalid key, try entering a one-word key that is shorter than the text";
        StringBuilder answer = new StringBuilder();
        String newText = text.toUpperCase(Locale.ROOT), newKey = key.toUpperCase(Locale.ROOT);
        for (int i = 0, j = 0; i < newText.length(); i++) {
            char c = newText.charAt(i);
            if (c < 'A' || c > 'Z') {
                answer.append(" ");
                continue;
            }
            answer.append( (char) ((c + newKey.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % newKey.length();
        }
        return answer.toString();
    }


    public static String decodeVigenereCipher(String text, String key) {
        if(vigenereError(text, key)) return "Invalid key, try entering a one-word key that is shorter than the text";
        StringBuilder answer = new StringBuilder();
        String newText = text.toUpperCase(Locale.ROOT), newKey = key.toUpperCase(Locale.ROOT);
        for (int i = 0, j = 0; i < newText.length(); i++) {
            char c = newText.charAt(i);
            if (c < 'A' || c > 'Z') {
                answer.append(" ");
                continue;
            }
            answer.append( (char) ((c - newKey.charAt(j) + 26) % 26 + 'A'));
            j = ++j % newKey.length();
        }
        return answer.toString();
    }


    public static String codeAsciiCipher(String numberSeq, int base){
        String[] strArr = numberSeq.split("\\s+");
        ArrayList<Integer> numArr = new ArrayList<>();
        for(String s : strArr) numArr.add( Integer.parseInt(getInBase10(s, base)) );
        StringBuilder sb = new StringBuilder();
        for(int i : numArr) sb.append( String.valueOf((char) i) + " ");
        return sb.toString();
    }


    public static String decodeAsciiCipher(String charSeq, int base){
        ArrayList<Integer> numArr = new ArrayList<>();
        for(int i = 0; i < charSeq.length(); i++) numArr.add(Integer.parseInt(getNumberInBase((int) charSeq.charAt(i), base)));
        StringBuilder sb = new StringBuilder();
        for(int i : numArr) sb.append( String.valueOf(i) + " ");
        return sb.toString();
    }


    public static String getInBase10(int numberStrInAnyBase, int baseOfTheNumber){
        return Integer.toString( numberStrInAnyBase, baseOfTheNumber ) ;
    }


    public static String getInBase10(String numberStrInAnyBase, int baseOfTheNumber){
        return getInBase10(Integer.parseInt(numberStrInAnyBase), baseOfTheNumber);
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



    /*  HELPER  FUNCTIONS  */

    private static boolean vigenereError(String text, String key) {
        return key.length() >= text.length() || key.matches("^.*[^a-zA-Z0-9 ].*$") || key.contains(" ");
    }

    private static String caesarCipher(String textToCode, int shift, boolean willBeCoded){
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



    /*  LANGUAGE SETTINGS  */

    public static void setLanguageToEnglish(){ alphabetString = CryptoConstants.ENGLISH_ALPHABET; }

    public static void setCustomAlphabet(String customAlphabet){ alphabetString = new StringBuilder(customAlphabet); }

}