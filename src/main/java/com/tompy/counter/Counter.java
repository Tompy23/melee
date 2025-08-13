package com.tompy.counter;

import com.tompy.counter.state.CounterState;
import com.tompy.counter.state.CounterStateFactory;
import com.tompy.counter.state.CounterStateType;
import com.tompy.hexboard.Hex;
import com.tompy.state.StateMachine;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Counter extends Rectangle implements StateMachine<CounterState> {
    private static long idIndex = 0;
    private CounterState currentState;
    private CounterState previousState;
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

        this.setFill(new ImagePattern(image));
        this.setStrokeWidth(6.0);
        this.setStroke(Color.TRANSPARENT);

        setOnMouseClicked(this::handleClick);
        setOnMouseEntered(this::handleEnter);
        setOnMouseExited(this::handleExit);

        changeState(CounterStateFactory.builder().type(CounterStateType.COMMON).counter(this).build());
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

    public void unselect() {
        selected = false;
    }

    public void toggleSelect() {
        selected = !selected;
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

    @Override
    public void changeState(CounterState newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        if (currentState != null) {
            currentState.beginState();
        }
    }

    @Override
    public void continueState(CounterState newState){
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
    }

    @Override
    public CounterState getCurrentState() {
        return currentState;
    }

    @Override
    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
    }

    @Override
    public boolean stopThread() {
        return false;
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

    public CounterState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(CounterState previousState) {
        this.previousState = previousState;
    }

    public void handleClick(MouseEvent event) {
        currentState.handleClick(event);
    }

    public void handleEnter(MouseEvent event) {
        currentState.handleEnter(event);
    }

    public void handleExit(MouseEvent event) {
        currentState.handleExit(event);
    }
}
