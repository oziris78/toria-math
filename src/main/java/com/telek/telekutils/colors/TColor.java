package com.telek.telekutils.colors;


import java.util.Objects;

public class TColor {

    /** Values in range [0,255] */
    private final int r,g,b,a;

    private String rAsHex = null, gAsHex = null, bAsHex = null, aAsHex = null;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TColor(int r, int g, int b, int a){
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public TColor(int r, int g, int b){
        this(r, g, b, 255);
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


    public String getRGBString(){
        return String.format("rgb(%d, %d, %d)", r, g, b);
    }

    public String getRGBAString(){
        return String.format("rgba(%d, %d, %d, %f)", r, g, b, a / 255f);
    }

    public String getHexString(){
        return String.format("%s%s%s", getRedAsHex(), getGreenAsHex(), getBlueAsHex());
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public int getAlpha() {return a;}
    public int getBlue() {return b;}
    public int getGreen() {return g;}
    public int getRed() {return r;}

    public String getAlphaAsHex() {
        if(bAsHex == null) {
            aAsHex = Integer.toHexString(a);
            if(aAsHex.length() == 1) aAsHex = "0" + aAsHex;
        }
        return aAsHex;
    }

    public String getRedAsHex() {
        if(rAsHex == null) {
            rAsHex = Integer.toHexString(r);
            if(rAsHex.length() == 1) rAsHex = "0" + rAsHex;
        }
        return rAsHex;
    }

    public String getBlueAsHex() {
        if(bAsHex == null) {
            bAsHex = Integer.toHexString(b);
            if(bAsHex.length() == 1) bAsHex = "0" + bAsHex;
        }
        return bAsHex;
    }

    public String getGreenAsHex() {
        if(gAsHex == null) {
            gAsHex = Integer.toHexString(g);
            if(gAsHex.length() == 1) gAsHex = "0" + gAsHex;
        }
        return gAsHex;
    }

    ////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "TColor{" + "r=" + r + ", g=" + g + ", b=" + b + ", a=" + a + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TColor tColor = (TColor) o;
        return r == tColor.r && g == tColor.g && b == tColor.b && a == tColor.a;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }

}
