package com.telek.telekmath.exceptions;

import com.telek.telekmath.core.geometry.lines.TLine2D;
import com.telek.telekmath.core.geometry.lines.TLine3D;

public class TelekMathException extends RuntimeException {


    public TelekMathException(String message) {
        super(message);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class InvalidExtensionException extends RuntimeException {

        public InvalidExtensionException() {
            super("The extension must be either \"PNG\" or \"JPG\"");
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

    public static class InvalidEquationCountException extends RuntimeException {

        public InvalidEquationCountException() {
            super("Invalid matrices or equationCount");
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class LinesAreNotParallelException extends RuntimeException {

        public LinesAreNotParallelException(TLine2D line1, TLine2D line2) {
            super("These lines aren't parallel to each other: " + line1.toString() + " , " + line2.toString());
        }


        public LinesAreNotParallelException(TLine3D line1, TLine3D line2) {
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

    public static class HasNonLetterCharactersException extends RuntimeException {
        public HasNonLetterCharactersException() {
            super("Write the code without non-letter characters");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotAHorizontalLineException extends RuntimeException{
        public NotAHorizontalLineException(TLine2D line) {
            super("This line isn't horizontal line: " + line);
        }
        public NotAHorizontalLineException(TLine3D line) {
            super("This line isn't horizontal line: " + line);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static class NotAVerticalLineException extends RuntimeException{
        public NotAVerticalLineException(TLine2D line) {
            super("This line isn't vertical line: " + line);
        }
        public NotAVerticalLineException(TLine3D line) {
            super("This line isn't vertical line: " + line);
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


}
