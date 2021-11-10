package com.telek.telekutils.swing;

import java.awt.*;
import java.net.URI;


public class TDesktopUtils {


    public static void openOnBrowser(URI linkURI){
        try{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                Desktop.getDesktop().browse(linkURI);
        }
        catch (Exception e){ e.printStackTrace(); }
    }


    public static void openOnBrowser(String link){
        try{ openOnBrowser(new URI(link)); }
        catch (Exception e){ e.printStackTrace(); }
    }



    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").startsWith("Linux");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").startsWith("Mac");
    }


}
