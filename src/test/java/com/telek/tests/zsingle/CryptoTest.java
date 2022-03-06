package com.telek.tests.zsingle;

import com.telek.telekmath.advanced.cryptography.AsciiCipher;
import com.telek.telekmath.advanced.cryptography.BaconCipher;
import com.telek.telekmath.advanced.cryptography.CaesarCipher;
import com.telek.telekmath.advanced.cryptography.VigenereCipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Locale;

public class CryptoTest {

    @Test
    @DisplayName("cryptoTest")
    void cryptoTest() throws Throwable {

        // ASCII CIPHER
        Assertions.assertEquals(AsciiCipher.encode("OGUZHAN", 2),
                "1001111 1000111 1010101 1011010 1001000 1000001 1001110");
        Assertions.assertEquals(AsciiCipher.decode("1001111 1000111 1010101 1011010 1001000 1000001 1001110", 2),
                "O G U Z H A N");


        // BACON CIPHER
        Assertions.assertEquals(
                BaconCipher.decode("I WENt TO SCHOOL tODay, iT Was RaInING. nO I dO NOt lIKe it WHen iT RAIns"),
                "BACONISGOOD"
        );


        // VIGENERE CIPHER
        Assertions.assertEquals(VigenereCipher.encode("DCODE CAN DECRYPT VIGENERE WITHOUT KEY", "KEY"),
                "nGmni akr bogpitr fmeorcbi usxfyyr uiw".toUpperCase(Locale.ROOT));
        Assertions.assertEquals(VigenereCipher.decode("nGmni akr bogpitr fmeorcbi usxfyyr uiw", "KEY"),
                "DCODE CAN DECRYPT VIGENERE WITHOUT KEY");


        // CAESAR CIPHER
        Assertions.assertEquals(CaesarCipher.decode("zpsf kmkclr", -2), "bruh moment");
        Assertions.assertEquals(CaesarCipher.encode("bruh moment", -2), "zpsf kmkclr");
        Assertions.assertEquals(CaesarCipher.decode("dtwj oqogpv", 2), "bruh moment");
        Assertions.assertEquals(CaesarCipher.encode("bruh moment", 2), "dtwj oqogpv");


    }



}
