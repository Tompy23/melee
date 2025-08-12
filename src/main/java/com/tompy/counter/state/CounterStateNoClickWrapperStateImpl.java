package com.tompy.counter.state;

import com.tompy.counter.Counter;
import javafx.scene.input.MouseEvent;

public class CounterStateNoClickWrapperStateImpl extends AbstractCounterStateDecorator {

    public CounterStateNoClickWrapperStateImpl(Counter counter, CounterState previousState) {
        this.counter = counter;
        this.counter.setPreviousState(previousState);
        this.wrappedState = previousState;
    }

    @Override
    public void handleClick(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();
        counter.getHex().handleClick(event);
    }

    @Override
    public void handleEnter(MouseEvent event) {

    }

    @Override
    public void handleExit(MouseEvent event) {

    }
}
