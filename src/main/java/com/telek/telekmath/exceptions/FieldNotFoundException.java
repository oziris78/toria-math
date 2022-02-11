package com.telek.telekmath.exceptions;


public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(String className, String fieldName) {
        super(String.format("%s field not found in class %s", fieldName, className));
    }

}
