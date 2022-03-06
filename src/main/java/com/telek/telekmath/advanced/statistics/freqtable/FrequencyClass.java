package com.telek.telekmath.advanced.statistics.freqtable;


import java.util.Objects;

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

    @Override
    public String toString() {
        return "FrequencyClass{" +
                "cLeft=" + cLeft +
                ", cRight=" + cRight +
                ", midpoint=" + midpoint +
                ", freq=" + freq +
                ", relFreq=" + relFreq +
                ", incCumFreq=" + incCumFreq +
                ", incRelFreq=" + incRelFreq +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequencyClass that = (FrequencyClass) o;
        return Double.compare(that.cLeft, cLeft) == 0 &&
                Double.compare(that.cRight, cRight) == 0 &&
                Double.compare(that.midpoint, midpoint) == 0 &&
                Double.compare(that.freq, freq) == 0 &&
                Double.compare(that.relFreq, relFreq) == 0 &&
                Double.compare(that.incCumFreq, incCumFreq) == 0 &&
                Double.compare(that.incRelFreq, incRelFreq) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
    }
}
