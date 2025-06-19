package com.tompy.hexboard;

public class Hex {
    private final HexCoordinate coordinate;
    private boolean selected;

    private Hex(Builder builder) {
        coordinate = HexCoordinate.builder().setCol(builder.q).setRow(builder.r).build();
        selected = false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

    public int getCol() {
        return coordinate.getCol();
    }

    public int getRow() {
        return coordinate.getRow();
    }

    public int getQ() {
        return coordinate.getQ();
    }

    public int getR() {
        return coordinate.getR();
    }

    public int getS() {
        return coordinate.getS();
    }

    public HexCoordinate getCoordinate() {
        return coordinate;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Hex o)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return this.coordinate.equals(o.coordinate);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }

    public static final class Builder {
        private int q;
        private int r;

        public Builder setQ(int q) {
            this.q = q;
            return this;
        }

        public Builder setR(int r) {
            this.r = r;
            return this;
        }

        public Hex build() {
            return new Hex(this);
        }
    }
}
