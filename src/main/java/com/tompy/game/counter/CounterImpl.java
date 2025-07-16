package com.tompy.game.counter;

import com.tompy.hexboard.Hex;
import javafx.scene.image.Image;

public class CounterImpl implements Counter {
    private static long idIndex = 0;
    private final long id;
    private final Image image;
    private Hex hex;
    private boolean selected;

    private CounterImpl(Builder builder) {
        this.id = ++idIndex;
        this.image = builder.image;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void addToHex(Hex hex) {
        this.hex = hex;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void select(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void unselect() {
        selected = false;
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
