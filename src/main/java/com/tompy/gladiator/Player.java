package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.hexboard.Hex;

public class Player {
    private final Counter counter;
    private Hex moveToHex;
    private int moveToRotation;

    public Player(Builder builder) {
        this.counter = builder.counter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Counter getCounter() {
        return counter;
    }

    public Hex getMoveToHex() {
        return moveToHex;
    }

    public void setMoveToHex(Hex moveToHex) {
        this.moveToHex = moveToHex;
    }

    public int getMoveToRotation() {
        return moveToRotation;
    }

    public void setMoveToRotation(int moveToRotation) {
        this.moveToRotation = moveToRotation;
    }

    public static class Builder {
        private Counter counter;

        public Builder counter(Counter counter) {
            this.counter = counter;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
