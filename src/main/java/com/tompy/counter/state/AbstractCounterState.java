package com.tompy.counter.state;

import com.tompy.counter.Counter;
import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import kotlin.OptIn;

public abstract class AbstractCounterState implements CounterState {
    protected Counter counter;

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
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {

    }

    @Override
    public void select() {

    }

    @Override
    public void unselect() {

    }

    @Override
    public boolean isSelected() {
        return false;
    }

    public void unselectAllCountersOutsideHex(Hex otherHex) {
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            if (otherHex != null && !hex.equals(otherHex)) {
                hex.getCounters().forEach(Counter::unselect);
            }
        }
    }

    public void selectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(Counter::select);
        }
    }

    public void unselectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(Counter::unselect);
        }
    }
}
