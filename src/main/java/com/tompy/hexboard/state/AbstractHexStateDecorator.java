package com.tompy.hexboard.state;

public abstract class AbstractHexStateDecorator extends AbstractHexState {
    protected HexState wrappedState;

    @Override
    public void beginState() {
        wrappedState.beginState();
    }

    @Override
    public void endState() {
        wrappedState.endState();
    }

    @Override
    public void process(long l) {
        wrappedState.process(l);
    }

    @Override
    public void handleClick() {
        wrappedState.handleClick();
    }

    @Override
    public void handleEnter() {
        wrappedState.handleEnter();
    }

    @Override
    public void handleExit() {
        wrappedState.handleExit();
    }

    @Override
    public boolean isSelected() {
        return wrappedState.isSelected();
    }
}
