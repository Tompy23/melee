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

    private final long movement;
    private long movementExpended;

    private Counter(Builder builder) {
        this.id = ++idIndex;
        setId("COUNTER" + this.id);
        this.image = builder.image;
        this.movement = builder.movement;
        this.movementExpended = 0;
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

    public void setHex(Hex hex) {
        this.hex = hex;
    }

    public long getCounterId() {
        return id;
    }

    public long getMovement() {
        return movement;
    }

    public long getMovementExpended() {
        return movementExpended;
    }

    public void expendMovement(long count) {
        movementExpended += count;
    }

    public void resetMovementExpended() {
        movementExpended = 0;
    }

    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public static class Builder {
        private Image image;
        private long movement;

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Builder movement(long movement) {
            this.movement = movement;
            return this;
        }

        public Counter build() {
            return new Counter(this);
        }
    }

}
