package com.telek.telekmath.advanced.statistics.measures;


/**
 * A class containing all measures inside it.
 */
public class DataDescription {


    public static class Mode{
        public final double value, count;

        public Mode(double modeValue, double modeCount) {
            this.value = modeValue;
            this.count = modeCount;
        }

        @Override
        public String toString() {
            return "Mode{" + "value=" + value + ", count=" + count + '}';
        }
    }

    //////////////
    /*  FIELDS  */
    //////////////

    public final Mode mode;

    // values without javadocs (self-explanatory variable names)
    public final double count, mean, sum, interquartileRange, variance;

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

    DataDescription(double variance, double mean,
                    double sum, double interquartileRange, double count,
                    double quartile1, Mode mode, double median,
                    double quartile2, double quartile3, double min, double max,
                    double range, double stddev, double pearsonSkewCoef, double bowleySkewCoef)
    {
        this.interquartileRange = interquartileRange;
        this.pearsonSkewCoef = pearsonSkewCoef;
        this.bowleySkewCoef = bowleySkewCoef;
        this.quartile1 = quartile1;
        this.quartile2 = quartile2;
        this.quartile3 = quartile3;
        this.variance = variance;
        this.median = median;
        this.stddev = stddev;
        this.range = range;
        this.count = count;
        this.mean = mean;
        this.mode = mode;
        this.sum = sum;
        this.min = min;
        this.max = max;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "DataDescription{" + "mode=" + mode + ", count=" + count + ", mean="
                + mean + ", sum=" + sum + ", interquartileRange=" + interquartileRange
                + ", variance=" + variance + ", median=" + median + ", quartile1="
                + quartile1 + ", quartile2=" + quartile2 + ", quartile3=" + quartile3 +
                ", min=" + min + ", max=" + max + ", range=" + range +
                ", stddev=" + stddev + ", pearsonSkewCoef=" + pearsonSkewCoef
                + ", bowleySkewCoef=" + bowleySkewCoef + '}';
    }


}