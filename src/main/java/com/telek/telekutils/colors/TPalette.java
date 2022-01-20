package com.telek.telekutils.colors;


public class TPalette {

    private final TColor[] colors;

    public TPalette(TColor... colors){
        this.colors = colors;
    }

    public int getColorCount(){
        return colors.length;
    }

    public TColor[] getColors() {
        return colors;
    }


    /*  EXPORTING CODE  */


    /**
     * Gives you the contents of a .gpl file as a string, you can write
     * this string into a .gpl file and then use that file in GIMP or Aseprite.
     * @return the contents of a .gpl file as a string
     */
    public String getFileContentForGIMP(){
        int len = colors.length;

        StringBuilder content = new StringBuilder("GIMP Palette\n" +
                "#Palette Name: ?\n" +
                "#Description: Exported from telek-math.\n" +
                String.format("#Colors: %s \n", len)
        );

        for(int i = 0; i < len; i++){
            TColor curColor = colors[i];
            content.append(String.format("%d\t%d\t%d\t%s", curColor.getRed(), curColor.getGreen(),
                    curColor.getBlue(), curColor.getRedAsHex() + curColor.getGreenAsHex() + curColor.getBlueAsHex()));
            if(i+1 != len) content.append("\n");
        }

        return content.toString();
    }


    /**
     * Write the string you get from this function to a .txt file and then use that file in paint.net to import your palette.
     * @return the contents of a pdn palette file as a string
     */
    public String getFileContentForPDN(){
        StringBuilder content = new StringBuilder("; paint.net Palette File\n");

        int len = colors.length;
        for(int i = 0; i < len; i++){
            TColor curColor = colors[i];
            content.append(curColor.getAlphaAsHex() + curColor.getRedAsHex() + curColor.getGreenAsHex() + curColor.getBlueAsHex());
            if(i+1 != len) content.append("\n");
        }

        return content.toString();
    }



}