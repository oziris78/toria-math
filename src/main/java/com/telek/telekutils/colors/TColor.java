package com.telek.telekutils.colors;


import com.telek.telekutils.plain.TStringUtils;

import java.util.Objects;


/**
 * An immutable Color class with few utility functions.
 */
public class TColor {

    /** Values in range [0,255] */
    public final int red, green, blue, alpha;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TColor(int red, int green, int blue, int alpha){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public TColor(int red, int green, int blue){
        this(red, green, blue, 255);
    }



    /**
     * Creates a tcolor object using the hex string.
     * @param hexString a string like "#ffaacc" with an hashtag in the beginning.
     */
    public TColor(String hexString){
        this(
          /* r */ Integer.valueOf( hexString.substring( 1, 3 ), 16 ),
          /* g */ Integer.valueOf( hexString.substring( 3, 5 ), 16 ),
          /* b */ Integer.valueOf( hexString.substring( 5, 7 ), 16 ),
          /* a */ 255
        );
    }

    ///////////////
    /*  METHODS  */
    ///////////////



    public String getAsRGB(){
        return String.format("rgb(%d, %d, %d)", red, green, blue);
    }

    public String getAsRGBA(){
        return String.format("rgba(%d, %d, %d, %f)", red, green, blue, alpha / 255f);
    }

    public String getAsHex(){
        return String.format("%s%s%s", getRedAsHex(), getGreenAsHex(), getBlueAsHex());
    }


    public String getRedAsHex() {
        return TStringUtils.getAsHex(red);
    }

    public String getGreenAsHex() {
        return TStringUtils.getAsHex(green);
    }

    public String getBlueAsHex() {
        return TStringUtils.getAsHex(blue);
    }

    public String getAlphaAsHex() {
        return TStringUtils.getAsHex(alpha);
    }

    ////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "TColor{" + "r=" + red + ", g=" + green + ", b=" + blue + ", a=" + alpha + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TColor tColor = (TColor) o;
        return red == tColor.red && green == tColor.green && blue == tColor.blue && alpha == tColor.alpha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, alpha);
    }

}
