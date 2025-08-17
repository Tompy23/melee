package com.tompy.counter.state;

import com.tompy.counter.Counter;
import com.tompy.game.GameHexBoardData;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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

    protected void unselectAllCountersOutsideHex(Hex otherHex) {
        for (Hex hex : GameHexBoardData.get().getHexBoard().getHexes()) {
            if (otherHex != null && !hex.equals(otherHex)) {
                hex.getCounters().forEach(Counter::unselect);
                hex.getCounters().forEach(c -> c.setStroke(Color.TRANSPARENT));
            }
        }
    }

    protected void selectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(Counter::select);
            hex.getCounters().forEach(c -> c.setStroke(Color.GREEN));
            hex.getCounters().forEach(c -> c.setStrokeWidth(4.0));
        }
    }

    protected void unselectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(Counter::unselect);
        }
    }

}
