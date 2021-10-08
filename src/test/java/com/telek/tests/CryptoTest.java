package com.telek.tests;

import com.telek.telekmath.special.Cryptography;

public class CryptoTest {

    public static void main(String[] args) {

        String text = Cryptography.codeCaesarCipher("Never gonna give you up, never gonna let you down!", 7);
        String decodedText = Cryptography.decodeCaesarCipher(text, 7);
        System.out.println(text);
        System.out.println(decodedText);

        System.out.println( Cryptography.decodeBaconCypher("I WENt TO SCHOOL tODay, iT Was RaInING. nO I dO NOt lIKe it WHen iT RAIns") );

        System.out.println(Cryptography.getNumberInBase(5555, 16));

        System.out.println( Cryptography.codeAsciiCipher("1110011 1100101 1101100 1100001 1101101", 2)); // s e l a m
        System.out.println( Cryptography.decodeAsciiCipher("selam", 2)); // 1110011 1100101 1101100 1100001 1101101

        System.out.println( Cryptography.codeVigenereCipher("Make it happen", "MATH")); // YADL UT AHBPXU
        System.out.println( Cryptography.decodeVigenereCipher("yaDl uT AhbPxU", "MATH")); // MAKE IT HAPPEN

    }


}
