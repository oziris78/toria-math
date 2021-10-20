package com.telek.telekutils.colors;


import java.awt.Color;


public class TColorTheme {

    private final Color[] colors;

    public TColorTheme(Color... colors){
        this.colors = colors;
    }

    public int getColorCount(){
        return colors.length;
    }

    public Color[] getColors() {
        return colors;
    }

}