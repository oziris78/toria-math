package com.telek.telekmath.exceptions;

public class InvalidValueException extends IllegalArgumentException{

    public InvalidValueException(String fieldStr, double value) {
        super("Invalid value for " + fieldStr + " : " + value);
    }

    public InvalidValueException(String fieldStr, float value) {
        super("Invalid value for " + fieldStr + " : " + value);
    }

    public InvalidValueException(String fieldStr, int value) {
        super("Invalid value for " + fieldStr + " : " + value);
    }

    public InvalidValueException(String fieldStr, long value) {
        super("Invalid value for " + fieldStr + " : " + value);
    }





}
