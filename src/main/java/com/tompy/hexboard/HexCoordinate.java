package com.tompy.hexboard;

public class HexCoordinate {
    private final int col;
    private final int row;

    public HexCoordinate(Builder builder) {
        this.col = builder.col;
        this.row = builder.row;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getQ() {
        return (col - row) / 2;
    }

    public int getR() {
        return row;
    }

    public int getS() {
        return -getQ()-getR();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof HexCoordinate o)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return this.col == o.col && this.row == o.row;
    }

    @Override
    public int hashCode() {
        return col * row;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQ()).append(" : ").append(getR()).append(" : " ).append(getS());
        sb.append(System.lineSeparator());
        sb.append(col).append(" : ").append(row);
        return sb.toString();
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

        public HexCoordinate build() {
            return new HexCoordinate(this);
        }
    }
}
