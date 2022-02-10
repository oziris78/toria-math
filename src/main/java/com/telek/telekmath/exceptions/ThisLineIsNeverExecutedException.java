package com.telek.telekmath.exceptions;


/**
 * Used to get rid of compiler errors, this is not an actual exception.
 */
public class ThisLineIsNeverExecutedException extends RuntimeException {

    public ThisLineIsNeverExecutedException() {
        super("An unexpected error has occured");
    }

}



