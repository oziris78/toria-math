package com.telek.telekutils.singular;

import java.awt.Desktop;
import java.net.URI;


public final class TWebUtils {


    public static void openOnBrowser(URI linkURI){
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                Desktop.getDesktop().browse(linkURI);
        }
        catch (Exception e){ e.printStackTrace(); }
    }


    public static void openOnBrowser(String link){
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                Desktop.getDesktop().browse(new URI(link));
        }
        catch (Exception e){ e.printStackTrace(); }
    }


}
