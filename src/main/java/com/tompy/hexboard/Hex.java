package com.tompy.hexboard;

public class Hex {
    private final HexCoordinate coordinate;
    private boolean selected;

    private Hex(Builder builder) {
        coordinate = HexCoordinate.builder().setCol(builder.col).setRow(builder.row).build();
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
        private int col;
        private int row;

        public Builder setCol(int col) {
            this.col = col;
            return this;
        }

        public Builder setRow(int row) {
            this.row = row;
            return this;
        }

        public Hex build() {
            return new Hex(this);
        }
    }
}
