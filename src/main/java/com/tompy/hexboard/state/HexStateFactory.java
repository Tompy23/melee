package com.tompy.hexboard.state;

import com.tompy.game.GameProperty;
import com.tompy.game.state.GameState;
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

    public static HexStateBuilder builder() {
        return new HexStateBuilder();
    }

    public HexState create(HexStateBuilder builder) {
        switch (builder.type) {
            case SELECTED:
                HexState commonState = new HexStateCommonImpl(builder.hex);
                HexState selectedState = new HexStateSelectedImpl(builder.hex, commonState, builder.initialColor,
                        builder.secondaryColor, builder.opaqueness);
                return new HexStateDisplayTextImpl(builder.hex, selectedState, builder.style, builder.xOffset,
                        builder.yOffset, builder.display);
            case COMMON:
                return new HexStateCommonImpl(builder.hex);
            case MOVE1:
                return new HexStateMove1Impl(builder.hex);
            case MOVE1_NEIGHBOR:
                HexState move1State = new HexStateMove1NeighborImpl(builder.hex, builder.initialColor,
                        builder.opaqueness, builder.secondaryColor, builder.hex2);
                return new HexStateDisplayTextImpl(builder.hex, move1State, builder.style, builder.xOffset,
                        builder.yOffset, builder.display);
            default:
                return null;
        }
    }

    public static final class HexStateBuilder {
        private HexStateFactory hexStateFactory;
        private Hex hex;
        private GameProperty<Hex> hex2;
        private HexStateType type;
        private Color initialColor;
        private Color secondaryColor;
        private double opaqueness;
        private boolean switch1;
        private String style;
        private long xOffset;
        private long yOffset;
        private String display;
        private GameState gameState;

        public HexStateBuilder() {
            hexStateFactory = HexStateFactory.get();
        }

        public HexStateBuilder hex(Hex hex) {
            this.hex = hex;
            return this;
        }

        public HexStateBuilder hex2(GameProperty<Hex> hex2) {
            this.hex2 = hex2;
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

        public HexStateBuilder style(String style) {
            this.style = style;
            return this;
        }

        public HexStateBuilder xOffset(long xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public HexStateBuilder yOffset(long yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public HexStateBuilder display(String display) {
            this.display = display;
            return this;
        }

        public HexStateBuilder gameState(GameState gameState) {
            this.gameState = gameState;
            return this;
        }

        public HexState build() {
            return hexStateFactory.create(this);
        }
    }
}
