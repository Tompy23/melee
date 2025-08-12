package com.tompy.counter.state;

import javafx.scene.input.MouseEvent;

public abstract class AbstractCounterStateDecorator extends AbstractCounterState {
    protected CounterState wrappedState;

    @Override
    public void handleClick(MouseEvent event) {
        wrappedState.handleClick(event);
    }

    @Override
    public void handleEnter(MouseEvent event) {
        wrappedState.handleEnter(event);
    }

    @Override
    public void handleExit(MouseEvent event) {
        wrappedState.handleExit(event);
    }
}
