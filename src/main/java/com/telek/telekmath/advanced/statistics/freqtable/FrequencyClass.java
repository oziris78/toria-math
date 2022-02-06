package com.telek.telekmath.advanced.statistics.freqtable;


public class FrequencyClass {

    public final double cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq;

    FrequencyClass(double cl, double cr, double mid, double f, double rf, double icf, double irf) {
        this.cLeft = cl;
        this.cRight = cr;
        this.midpoint = mid;
        this.freq = f;
        this.relFreq = rf;
        this.incCumFreq = icf;
        this.incRelFreq = irf;
    }


}
