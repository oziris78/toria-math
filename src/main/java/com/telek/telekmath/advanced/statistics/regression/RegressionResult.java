package com.telek.telekmath.advanced.statistics.regression;


import com.telek.telekmath.core.functions.other.TPolynomial;

public class RegressionResult {

    /**
     * The estimator line of exact Y, also written as "y hat" (y^).
     */
    public final TPolynomial line;

    /** Variance of the estimator (y hat) */
    public final double variance;

    /** Standard error of the estimator (y hat) */
    public final double standardError;

    public final double varianceOfB0, varianceOfB1;

    /** Sum of Regression Squares */
    public final double SRS;

    /** Sum of Total Squares */
    public final double STS;

    /** Sum of Error Squares */
    public final double SES;

    /** The determination coefficient */
    public final double R2;

    /** The correlation coefficient */
    public final double r;






    RegressionResult(TPolynomial line, double variance, double standardError, double varianceOfB0,
                     double varianceOfB1, double SRS, double STS, double SES, double R2, double r)
    {
        this.line = line;
        this.variance = variance;
        this.standardError = standardError;
        this.varianceOfB0 = varianceOfB0;
        this.varianceOfB1 = varianceOfB1;
        this.SRS = SRS;
        this.STS = STS;
        this.SES = SES;
        this.R2 = R2;
        this.r = r;
    }



}
