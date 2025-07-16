package com.tompy.game.counter;

import com.tompy.hexboard.Hex;
import javafx.scene.image.Image;

public class CounterFactory {
    private static CounterFactory factorySingleton;

    public static CounterFactory get() {
        if (factorySingleton == null) {
            factorySingleton = new CounterFactory();
        }
        return factorySingleton;
    }

    public static CounterBuilder counterBuilder() {
        return new CounterBuilder();
    }

    private Counter create(CounterBuilder builder) {
        switch (builder.type) {
            case GLADIATOR:
                Counter newCounter = CounterImpl.builder().image(new Image(builder.imageName)).build();
                if (builder.hex != null) {
                    builder.hex.addCounter(newCounter);
                }
                return newCounter;
            default:
                return null;
        }
    }


    public static class CounterBuilder {
        private CounterType type;
        private String imageName;
        private Hex hex;

        public CounterBuilder type(CounterType type) {
            this.type = type;
            return this;
        }

        public CounterBuilder imageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public CounterBuilder hex(Hex hex) {
            this.hex = hex;
            return this;
        }

        public Counter build() {
            return CounterFactory.get().create(this);
        }
    }
}
