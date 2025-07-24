package com.tompy.hexboard;

public class DoubleCubeCoordinate {
    private final double q;
    private final double r;
    private final double s;

    public DoubleCubeCoordinate(double q, double r, double s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public DoubleCubeCoordinate(double q, double r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(q).append(" : ").append(r).append(" : ").append(s);
        return sb.toString();
    }
}
