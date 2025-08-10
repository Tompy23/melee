package com.tompy.game;

public class PaneCoordinates {
    private final double x;
    private final double y;

    public PaneCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x + " : " + y);
        return sb.toString();
    }
}
