package com.tompy.game.marker;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexCoordinate;
import javafx.scene.image.Image;

public class Counter implements Marker {
    private final Image image;
    private Hex hex;

    private Counter(Builder builder) {
        this.image = builder.image;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Image getImage() {
        return image;
    }

    public void addToHex(Hex hex) {
        this.hex = hex;
    }

    public Hex getHex() {
        return hex;
    }

    public static class Builder {
        private Image image;

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Counter build() {
            return new Counter(this);
        }
    }

}
