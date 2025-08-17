package com.tompy.counter.state.play;

import com.tompy.counter.Counter;
import com.tompy.counter.state.AbstractCounterState;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CounterStateCommonImpl extends AbstractCounterState {
    private boolean selected;

    public CounterStateCommonImpl(Counter counter) {
        this.counter = counter;
        this.selected = counter.isSelected();
    }

    @Override
    public void process(long l) {
        if (counter.isSelected() != selected) {
            selected = counter.isSelected();
            if (counter.isSelected()) {
                counter.setStroke(Color.GREEN);
                counter.setStrokeWidth(4.0);
            } else {
                counter.setStroke(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void handleClick(MouseEvent event) {
        counter.toggleSelect();

        if (counter.isSelected()) {
            showSelected();
        } else {
            showUnselected();
        }

        Hex hex = counter.getHex();

        int clicks = event.getClickCount();
        boolean control = event.isControlDown();

        if (clicks == 1) {
            if (!control) {
                unselectAllCountersOutsideHex(hex);
            }

            if (hex.isCountersStacked()) {
                if (counter.isSelected()) {
                    selectAllCountersInHex(hex);
                } else {
                    unselectAllCountersInHex(hex);
                }
            }
        } else {
            if (clicks == 2) {
                if (hex.isCountersStacked()) {
                    hex.unstackCounters();
                } else {
                    hex.stackCounters();
                }
                GameFunction.displayCountersInHex(hex, GameHexBoardData.get().getPaneHexBoard());
            }
        }
   }

    @Override
    public void handleEnter(MouseEvent event) {
        showUnselected();
    }

    @Override
    public void handleExit(MouseEvent event) {
        if (counter.isSelected()) {
            showSelected();
        } else {
            counter.setStroke(Color.TRANSPARENT);
        }
    }

    private void showSelected() {
        counter.setStroke(Color.GREEN);
        counter.setStrokeWidth(4.0);
    }

    private void showUnselected() {
        counter.setStrokeWidth(3.0);
        counter.setStroke(Color.BLACK);
    }
}
