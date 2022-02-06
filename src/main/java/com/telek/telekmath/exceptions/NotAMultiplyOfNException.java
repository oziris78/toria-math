package com.telek.telekmath.exceptions;

public class NotAMultiplyOfNException extends RuntimeException {

    public NotAMultiplyOfNException(String fieldStr, int value) {
        super(fieldStr + " isn't a multiply of " + value + " (like " + value + "n)");
    }

}
