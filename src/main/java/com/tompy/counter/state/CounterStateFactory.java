package com.tompy.counter.state;

import com.tompy.counter.Counter;

public class CounterStateFactory {
    private static CounterStateFactory singletonFactory;

    public static CounterStateFactory get() {
        if (singletonFactory == null) {
            singletonFactory = new CounterStateFactory();
        }

        return singletonFactory;
    }

    public static CounterStateBuilder builder() {
        return new CounterStateBuilder();
    }


    public CounterState create(CounterStateBuilder builder) {
        switch (builder.type) {
            case COMMON:
                return new CounterStateCommonImpl(builder.counter);
            case NO_CLICK_WRAPPER:
                return new CounterStateNoClickWrapperStateImpl(builder.counter, builder.previousState);
            default:
                return null;
        }
    }

    public static class CounterStateBuilder {
        private CounterStateFactory factory;
        private CounterStateType type;
        private Counter counter;
        private CounterState previousState;

        public CounterStateBuilder() {
            this.factory = CounterStateFactory.get();
        }

        public CounterStateBuilder type(CounterStateType type) {
            this.type = type;
            return this;
        }

        public CounterStateBuilder counter(Counter counter) {
            this.counter = counter;
            return this;
        }

        public CounterStateBuilder previousState(CounterState previousState) {
            this.previousState = previousState;
            return this;
        }

        public CounterState build() {
            return factory.create(this);
        }
    }
}
