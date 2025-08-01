package com.tompy.counter.state;

import com.tompy.counter.Counter;
import javafx.scene.input.MouseEvent;

public class CounterStateNoClickWrapperStateImpl extends AbstractCounterStateDecorator {
    private CounterState previousState;

    public CounterStateNoClickWrapperStateImpl(Counter counter, CounterState previousState) {
        this.counter = counter;
        this.previousState = previousState;
        this.wrappedState = previousState;
    }

    @Override
    public void process(long l) {

    }

    @Override
    public void handleClick(MouseEvent event) {

    }

    @Override
    public void handleEnter(MouseEvent event) {

    }

    @Override
    public void handleExit(MouseEvent event) {

    }

    @Override
    public void select() {

    }

    @Override
    public void unselect() {

    }

}
