package com.tompy.hexboard;

public class HexCoordinate {
    private final int q;
    private final int r;

    public HexCoordinate(Builder builder) {
        this.q = builder.q;
        this.r = builder.r;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getS() {
        return -r-q;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof HexCoordinate o)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return this.q == o.q && this.r == o.r;
    }

    @Override
    public int hashCode() {
        return q * r;
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

        public HexCoordinate build() {
            return new HexCoordinate(this);
        }
    }
}
