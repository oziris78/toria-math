package com.telek.telekutils.colors;


public class TPalettes {

    /* No Constructor */
    private TPalettes(){}

    public static final TPalette TELEK_STUDIOS = new TPalette(
            new TColor(36, 37, 42),
            new TColor(107, 185, 240)
    );

    public static final TPalette RAINBOW = new TPalette(
            new TColor(255, 0 , 0),
            new TColor(255, 127, 0),
            new TColor(255, 255, 0),
            new TColor(0, 255, 0),
            new TColor(0, 0, 255),
            new TColor(75, 0, 130),
            new TColor(148, 0, 211)
    );

    public static final TPalette BLACK_AND_WHITE = new TPalette(
            new TColor(0, 0 , 0),
            new TColor(255, 255, 255)
    );


}
