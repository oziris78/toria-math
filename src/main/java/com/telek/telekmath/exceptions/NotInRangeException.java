package com.telek.telekmath.exceptions;

import com.telek.telekmath.core.functions.TRange;

public class NotInRangeException extends RuntimeException {

    public NotInRangeException(TRange range, double value) {
        super("The value " + value + " is not in the range: " + range.toString());
    }

    public NotInRangeException(TRange range, int value) {
        super("The value " + value + " is not in the range: " + range.toString());
    }

    public NotInRangeException(TRange range, float value) {
        super("The value " + value + " is not in the range: " + range.toString());
    }

    public NotInRangeException(TRange range, long value) {
        super("The value " + value + " is not in the range: " + range.toString());
    }



}
