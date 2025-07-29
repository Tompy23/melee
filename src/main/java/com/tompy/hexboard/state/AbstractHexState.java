package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;

public abstract class AbstractHexState implements HexState {
    protected Hex hex;

    @Override
    public void handleClick() {
        if (hex.isSelected()) {
            hex.unselect();
        } else {
            hex.select();
        }
    }

    @Override
    public void handleEnter() {

    }

    @Override
    public void handleExit() {

    }
}
