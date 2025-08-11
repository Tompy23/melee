package com.tompy.hexboard;

public class HexCoordinate {
    private final long col;
    private final long row;

    private HexCoordinate(Builder builder) {
        this.col = builder.col;
        this.row = builder.row;
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getCol() {
        return col;
    }

    public long getRow() {
        return row;
    }

    public long getQ() {
        return (col - row) / 2;
    }

    public long getR() {
        return row;
    }

    public long getS() {
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
        return (int) (col * row);
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
        private long col;
        private long row;

        public Builder setCol(long col) {
            this.col = col;
            return this;
        }

        public Builder setRow(long row) {
            this.row = row;
            return this;
        }

        public HexCoordinate build() {
            return new HexCoordinate(this);
        }
    }
}
