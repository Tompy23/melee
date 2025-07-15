package com.tompy.game.counter;

import com.tompy.hexboard.Hex;
import javafx.scene.image.Image;

public class CounterImpl implements Counter {
    private final Image image;
    private Hex hex;

    private CounterImpl(Builder builder) {
        this.image = builder.image;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Image getImage() {
        return image;
    }

    @Override
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

        public CounterImpl build() {
            return new CounterImpl(this);
        }
    }

}
