package com.telek.telekmath.advanced.statistics.measures;



public class DataDescription {


    //////////////
    /*  FIELDS  */
    //////////////

    // values without javadocs (self-explanatory variable names)
    public final double count, mean, geomean, sum, interquartileRange, mode, modeCount, variance;

    /** The value that divides the whole data set into 2 equal parts, also it's equal to {@link #quartile2}. */
    public final double median;

    /** Quartiles are calculated with exclusive percentages. <br> Same as QUARTILE.EXC(arr, i) in Excel. */
    public final double quartile1, quartile2, quartile3;

    /** Maximum value */
    public final double min;

    /** Minimum value */
    public final double max;

    /** Specifies (maxValue - minValue) value. */
    public final double range;

    /** Standard deviation */
    public final double stddev;


    /** A value in range [-1,1] to estimate the skewness of this data set. <br>
     * skewness > 0 means it's skewed to right. <br>
     * skewness = 0 means it's symmetrical. <br>
     * skewness < 0 means it's skewed to left.
     * */
    public final double pearsonSkewCoef, bowleySkewCoef;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    DataDescription(double variance, double mean, double geomean,
                    double sum, double interquartileRange, double count,
                    double quartile1, double mode, double median, double modeCount,
                    double quartile2, double quartile3, double min, double max,
                    double range, double stddev, double pearsonSkewCoef, double bowleySkewCoef)
    {
        this.variance = variance;
        this.mean = mean;
        this.geomean = geomean;
        this.sum = sum;
        this.interquartileRange = interquartileRange;
        this.count = count;
        this.quartile1 = quartile1;
        this.mode = mode;
        this.median = median;
        this.modeCount = modeCount;
        this.quartile2 = quartile2;
        this.quartile3 = quartile3;
        this.min = min;
        this.max = max;
        this.range = range;
        this.stddev = stddev;
        this.pearsonSkewCoef = pearsonSkewCoef;
        this.bowleySkewCoef = bowleySkewCoef;
    }

    /////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "DataDescription{" + "count=" + count + ", mean=" + mean + ", geomean=" + geomean +
                ", sum=" + sum + ", interquartileRange=" + interquartileRange + ", mode=" + mode +
                ", modeCount=" + modeCount + ", variance=" + variance + ", median=" + median +
                ", quartile1=" + quartile1 + ", quartile2=" + quartile2 + ", quartile3=" + quartile3 +
                ", min=" + min + ", max=" + max + ", range=" + range + ", stddev=" + stddev +
                ", pearsonSkewCoef=" + pearsonSkewCoef + ", bowleySkewCoef=" + bowleySkewCoef + '}';
    }

}