package com.tompy.gladiator;

import com.tompy.counter.Counter;

public class Player {
    private final Counter counter;

    public Player(Builder builder) {
        this.counter = builder.counter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Counter getCounter() {
        return counter;
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
