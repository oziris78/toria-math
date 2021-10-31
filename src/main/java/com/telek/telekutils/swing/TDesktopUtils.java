package com.telek.telekutils.swing;

public class TDesktopUtils {

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
