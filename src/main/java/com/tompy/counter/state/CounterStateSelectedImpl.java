package com.tompy.counter.state;

import com.tompy.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CounterStateSelectedImpl extends AbstractCounterState {

    public CounterStateSelectedImpl(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void beginState() {
        counter.setStrokeWidth(5);
        counter.setStroke(Color.GREEN);
    }

    public void endState() {
        counter.setStroke(Color.TRANSPARENT);
    }

    @Override
    public void handleClick(MouseEvent event) {
        returnToCommon();

        Hex hex = counter.getHex();

        int clicks = event.getClickCount();
        boolean control = event.isControlDown();

        if (clicks == 1) {
            if (!control) {
                unselectAllCountersOutsideHex(hex);
            }

            if (hex.isCountersStacked()) {
                unselectAllCountersInHex(hex);
            } else {
                if (!control) {
                    unselectAllCountersInHex(hex);
                }
                counter.unselect();
            }
        } else {
            if (clicks == 2) {
                if (hex.isCountersStacked()) {
                    hex.unstackCounters();
                } else {
                    hex.stackCounters();
                    selectAllCountersInHex(hex);
                }
            }
        }
        GameFunction.displayCountersInHex(hex);
    }

    @Override
    public void unselect() {
        returnToCommon();
    }

    private void returnToCommon() {
        counter.changeState(CounterStateFactory.builder().type(CounterStateType.COMMON).counter(counter).build());
    }

    @Override
    public boolean isSelected() {
        return true;
    }
}
