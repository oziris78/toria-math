package com.telek.tests.old;

import com.telek.telekmath.special.DiscreteMath;
import com.telek.telekmath.special.cryptography.AsciiCipher;
import com.telek.telekmath.special.cryptography.BaconCipher;
import com.telek.telekmath.special.cryptography.CaesarCipher;
import com.telek.telekmath.special.cryptography.VigenereCipher;

public class CryptoTest {

    public static void main(String[] args) {

        String text = CaesarCipher.codeCaesarCipher("Never gonna give you up, never gonna let you down!", 7);
        String decodedText = CaesarCipher.decodeCaesarCipher(text, 7);
        System.out.println(text);
        System.out.println(decodedText);

        System.out.println( BaconCipher.decodeBaconCypher("I WENt TO SCHOOL tODay, iT Was RaInING. nO I dO NOt lIKe it WHen iT RAIns") );

        System.out.println( DiscreteMath.getNumberInBase(5555, 16));

        System.out.println( AsciiCipher.codeAsciiCipher("1110011 1100101 1101100 1100001 1101101", 2)); // s e l a m
        System.out.println( AsciiCipher.decodeAsciiCipher("selam", 2)); // 1110011 1100101 1101100 1100001 1101101

        System.out.println( VigenereCipher.codeVigenereCipher("Make it happen", "MATH")); // YADL UT AHBPXU
        System.out.println( VigenereCipher.decodeVigenereCipher("yaDl uT AhbPxU", "MATH")); // MAKE IT HAPPEN

    }


}
