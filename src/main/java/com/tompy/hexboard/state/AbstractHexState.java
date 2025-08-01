package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;

public abstract class AbstractHexState implements HexState {
    protected Hex hex;

    @Override
    public void handleClick() {

    }

    @Override
    public void handleEnter() {

    }

    @Override
    public void handleExit() {

    }

    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {

    }

    @Override
    public boolean isSelected() {
        return false;
    }
}
