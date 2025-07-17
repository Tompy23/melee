package com.tompy.game.counter;

import com.tompy.hexboard.Hex;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Counter extends Rectangle {
    private static long idIndex = 0;
    private final long id;
    private final Image image;
    private Hex hex;
    private boolean selected;

    private Counter(Builder builder) {
        this.id = ++idIndex;
        setId("COUNTER" + this.id);
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

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void select(boolean selected) {
        this.selected = selected;
    }

    public void unselect() {
        selected = false;
    }

    public Hex getHex() {
        return hex;
    }

    public Node getStyleableNode() {
        return super.getStyleableNode();
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
