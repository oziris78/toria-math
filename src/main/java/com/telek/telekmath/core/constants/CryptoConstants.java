package com.telek.telekmath.core.constants;


import com.telek.telekutils.singular.TCollectionUtils;

import java.util.HashMap;

public final class CryptoConstants {

    public static final StringBuilder ENGLISH_ALPHABET = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");


    public static final HashMap<Integer, String> baseEleven = TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A"}
    );

    public static final HashMap<Integer, String> baseTwelve = TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B"}
    );

    public static final HashMap<Integer, String> baseThirteen = TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C"}
    );

    public static final HashMap<Integer, String> baseFourteen = TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D"}
    );

    public static final HashMap<Integer, String> baseFifteen =  TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E"}
    );

    public static final HashMap<Integer, String> baseSixteen = TCollectionUtils.createHashMap(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15},
            new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"}
    );


}
