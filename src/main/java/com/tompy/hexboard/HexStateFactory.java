package com.tompy.hexboard;

import javafx.scene.paint.Color;

public class HexStateFactory {
    private static HexStateFactory singletonHexStateFactory;

    public static HexStateFactory get() {
        if (singletonHexStateFactory == null) {
            singletonHexStateFactory = new HexStateFactory();
        }
        return singletonHexStateFactory;
    }

    public HexStateBuilder builder() {
        return new HexStateBuilder(singletonHexStateFactory);
    }

    public HexState create(HexStateBuilder builder) {
        switch (builder.type) {
            case SELECTED:
                return new HexStateSelectedImpl(builder.hex, builder.initialColor, builder.secondaryColor, builder.opaqueness);
            default:
                return null;
        }
    }

    public static final class HexStateBuilder {
        private HexStateFactory hexStateFactory;
        private Hex hex;
        private HexStateType type;
        private Color initialColor;
        private Color secondaryColor;
        private double opaqueness;

        public HexStateBuilder(HexStateFactory factory) {
            hexStateFactory = factory;
        }

        public HexStateBuilder hex(Hex hex) {
            this.hex = hex;
            return this;
        }

        public HexStateBuilder type(HexStateType type) {
            this.type = type;
            return this;
        }

        public HexStateBuilder initialColor(Color initialColor) {
            this.initialColor = initialColor;
            return this;
        }

        public HexStateBuilder secondaryColor(Color secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

        public HexStateBuilder opaqueness(double opaqueness) {
            this.opaqueness = opaqueness;
            return this;
        }

        public HexState build() {
            return hexStateFactory.create(this);
        }
    }
}
