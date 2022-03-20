package com.telek.telekmath.utils;

import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.geometry.TLine2D;


public final class TelekMathException extends RuntimeException {


    public TelekMathException(String message) {
        super(message);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class EqualBoundsException extends RuntimeException {
        public EqualBoundsException() {
            super("Left bound of a range can't be greater or equal to the right bound.");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class IsNegativeException extends RuntimeException {

        public IsNegativeException(String varName) {
            super(String.format("%s can't be negative", varName));
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class UnsupportedDistributionTypeString extends RuntimeException {
        public UnsupportedDistributionTypeString() {
            super("Your distType string should be one of these: \"uniform\", \"poisson\", \"normal\"");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class ExpectedValueAssumptionException extends RuntimeException {
        public ExpectedValueAssumptionException(double val) {
            super("All expected values must be higher than 5, your value is: " + val);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotEqualArrayLengthException extends RuntimeException {
        public NotEqualArrayLengthException(String arrayName, String otherThingLengthName) {
            super(String.format("Your %s array's length should be equal to %s's length",
                    arrayName, otherThingLengthName));
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class SampleSizeIsGreaterThanPopulationSizeException extends RuntimeException {

        public SampleSizeIsGreaterThanPopulationSizeException() {
            super("Sample size has to be less than population size.");
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotEqualKeyAndValueException extends RuntimeException {

        public NotEqualKeyAndValueException() {
            super("Number of keys and values aren't equal");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotGreaterThanZeroException extends RuntimeException {

        public NotGreaterThanZeroException(String fieldStr) {
            super(fieldStr + " value has to be greater than zero.");
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotGreaterThanNException extends RuntimeException {
        public NotGreaterThanNException(String fieldStr, String valStr) {
            super(fieldStr + " is not greater than " + valStr);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class LinesAreNotParallelException extends RuntimeException {

        public LinesAreNotParallelException(TLine2D line1, TLine2D line2) {
            super("These lines aren't parallel to each other: " + line1.toString() + " , " + line2.toString());
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class MatricesCantBeMultipliedException extends RuntimeException {

        public MatricesCantBeMultipliedException(int m1Col, int m2Row) {
            super(String.format(
                    "These matrices can't be multiplied because first matrix's column size isn't equal to " +
                            "the second matrix's row size, %d != %d", m1Col, m2Row
            ));
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class DifferentMatrixDimensionException extends RuntimeException {
        public DifferentMatrixDimensionException() {
            super("Both matrices must have the same dimension.");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class UnnecessaryZeroException extends RuntimeException {
        public UnnecessaryZeroException() {
            super("Your array has an unnecessary 0 at the end.");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class HasNonLetterCharactersException extends RuntimeException {
        public HasNonLetterCharactersException() {
            super("Write the code without non-letter characters");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotASquareMatrixException extends RuntimeException {
        public NotASquareMatrixException() {
            super("This matrix is not a square matrix.");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class PermutationException extends RuntimeException {
        public PermutationException(int n, int r) {
            super(String.format("Invalid values for permutation for perm(%d,%d)", n, r));
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class CombinationException extends RuntimeException {
        public CombinationException(int n, int r) {
            super(String.format("Invalid values for combination for comb(%d,%d)", n, r));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class RepeatedCombinationException extends RuntimeException {
        public RepeatedCombinationException(int n, int r) {
            super(String.format("Invalid values for repeated combination for comb(%d,%d)", n, r));
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class DivisionByZeroException extends RuntimeException {
        public DivisionByZeroException() {
            super("You can't divide by zero.");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class VigenereCipherException extends RuntimeException {
        public VigenereCipherException() {
            super("Invalid key, try entering a one-word key that is shorter than the text");
        }
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class InvalidBaseException extends RuntimeException {
        public InvalidBaseException() {
            super("Base of a logarithm must be greater than 0 and not equal to 1");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class RepeatedPermutationException extends IllegalArgumentException {

        public RepeatedPermutationException(int n, int... rValues){
            super( getErrString(n, rValues) );
        }

        private static String getErrString(int n, int... rValues){
            StringBuilder sbError = new StringBuilder( String.format("Invalid values for repeated permutation for perm(%d,", n) );
            for(int i = 0; i < rValues.length; i++) {
                if(i+1 == rValues.length) sbError.append(rValues[i] + ")");
                else sbError.append(rValues[i] + ",");
            }
            return sbError.toString();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static class NotInRangeException extends RuntimeException {

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotAMultiplyOfNException extends RuntimeException {

        public NotAMultiplyOfNException(String fieldStr, int value) {
            super(fieldStr + " isn't a multiply of " + value + " (like " + value + "n)");
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class InvalidSignStringException extends RuntimeException {

        public InvalidSignStringException() {
            super("The signStr parameter should be >=, <= or =");
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class InvalidValueException extends IllegalArgumentException{

        public InvalidValueException(String fieldStr, double value) {
            super("Invalid value for " + fieldStr + " = " + value);
        }

        public InvalidValueException(String fieldStr, float value) {
            super("Invalid value for " + fieldStr + " = " + value);
        }

        public InvalidValueException(String fieldStr, int value) {
            super("Invalid value for " + fieldStr + " = " + value);
        }

        public InvalidValueException(String fieldStr, long value) {
            super("Invalid value for " + fieldStr + " = " + value);
        }

        public InvalidValueException(String fieldStr) {
            super("Invalid value for " + fieldStr + " = " + "null");
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Used to get rid of compiler errors, this is not an actual exception.
     */
    public static class ThisLineIsNeverExecutedException extends RuntimeException {

        public ThisLineIsNeverExecutedException() {
            super("An unexpected error has occured");
        }

    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static class WrongFunctionException extends IllegalArgumentException{

        public WrongFunctionException(String theValue, String forValue, String cantBeValue) {
            super(theValue + " value for " + forValue + " can't be " + cantBeValue
                    + ", use TConstantFunc for constant functions.");
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}
