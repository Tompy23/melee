package com.tompy.hexboard;

public class CubeCoordinate {
    private final double q;
    private final double r;
    private final double s;

    public CubeCoordinate(double q, double r, double s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public double getQ() {
        return q;
    }

    public double getR() {
        return r;
    }

    public double getS() {
        return s;
    }
}
