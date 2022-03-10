package com.telek.tests.zsingle;

import com.telek.telekutils.colors.TColor;
import com.telek.telekutils.colors.TPalette;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ColorTest {

    @Test
    @DisplayName("colorEqualsTest")
    void colorEqualsTest() {
        TPalette palette1 = new TPalette(
                new TColor("#aaaaaa"),
                new TColor("#bbbbbb")
        );
        TPalette palette2 = new TPalette(
                new TColor("#aaaaaa"),
                new TColor("#bbbbbb")
        );
        TPalette palette3 = new TPalette(
                new TColor("#bbbbbb"),
                new TColor("#aaaaaa")
        );
        Assertions.assertTrue(palette1.equals(palette2));
        Assertions.assertFalse(palette1.equals(palette3));
    }


    public static void main(String[] args) throws IOException {
        TPalette myPalette = new TPalette(
                new TColor("#be4a2f"), new TColor("#d77643"), new TColor("#ead4aa"), new TColor("#e4a672"), new TColor("#b86f50"), new TColor("#733e39"), new TColor("#3e2731"), new TColor("#a22633"), new TColor("#e43b44"), new TColor("#f77622"), new TColor("#feae34"), new TColor("#fee761"), new TColor("#63c74d"), new TColor("#3e8948"),
                new TColor("#265c42"), new TColor("#193c3e"), new TColor("#124e89"), new TColor("#0099db"), new TColor("#2ce8f5"), new TColor("#ffffff"), new TColor("#c0cbdc"), new TColor("#8b9bb4"), new TColor("#5a6988"), new TColor("#3a4466"),
                new TColor("#262b44"), new TColor("#181425"), new TColor("#ff0044"), new TColor("#68386c"), new TColor("#b55088"), new TColor("#f6757a"), new TColor("#e8b796"), new TColor("#c28569")
        );



        BufferedWriter bf = new BufferedWriter(new FileWriter("assets/gimpPalette.gpl"));
        bf.write(myPalette.getFileContentForGIMP());
        bf.close();
    }

}
