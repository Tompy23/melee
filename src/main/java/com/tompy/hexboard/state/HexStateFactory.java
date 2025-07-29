package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;
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
                HexState commonState = new HexStateCommonImpl(builder.hex, builder.switch1);
                HexState selectedState = new HexStateSelectedImpl(builder.hex, commonState, builder.initialColor, builder.secondaryColor, builder.opaqueness);
                return new HexStateShowCoordinatesImpl(builder.hex, selectedState);
            case COMMON:
                return new HexStateCommonImpl(builder.hex, builder.switch1);
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
        private boolean switch1;

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

        public HexStateBuilder switch1(boolean switch1) {
            this.switch1 = switch1;
            return this;
        }

        public HexState build() {
            return hexStateFactory.create(this);
        }
    }
}
