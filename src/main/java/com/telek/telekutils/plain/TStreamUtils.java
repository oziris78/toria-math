package com.telek.telekutils.plain;

import java.util.stream.IntStream;

public class TStreamUtils {

    /* No Constructor */
    private TStreamUtils(){}


    public static String intStreamToString(IntStream intStream){
        return intStream.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static IntStream stringToIntStream(String string){
        return string.codePoints();
    }

}
