package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class AbstractGameState implements GameState {
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
    public void onClickHex(MouseEvent event) {
        // If selected, unselect and turn half-blue, else select and turn green
        GameFunction.selectHexGreenOrBlue((Hex) event.getTarget());
    }

    @Override
    public void onMouseEnterHex(MouseEvent event) {
        Hex hex = (Hex) event.getTarget();
        GameFunction.fillHexHalfBlue(hex);

        GameData.get().setHexWithMouse(hex);
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        Hex hex = (Hex) event.getTarget();
        GameFunction.fillHexGreenOrTransparent(hex);

        GameData.get().setHexWithMouse(null);
    }

    @Override
    public void onMouseEnterCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();

        counter.setStroke(Color.BLACK);

        Hex hex = counter.getHex();
        GameFunction.fillHexHalfBlue(hex);
        GameData.get().setHexWithMouse(hex);
    }

    @Override
    public void onMouseLeaveCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();

        if (counter.isSelected()) {
            counter.setStroke(Color.GREEN);
        } else {
            counter.setStroke(Color.TRANSPARENT);
        }
        GameFunction.fillHexGreenOrTransparent(counter.getHex());

        GameData.get().setHexWithMouse(null);
    }

    @Override
    public void onMouseClickCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();
        if (event.getClickCount() == 1) {
            System.out.println("one click");
            GameFunction.unselectAllCountersInHex(GameData.get().getHexWithMouse());

            if (counter.isSelected()) {
                counter.setStroke(Color.TRANSPARENT);
                counter.unselect();
            } else {
                counter.setStroke(Color.GREEN);
                counter.select();
            }
        } else {
            if (event.getClickCount() == 2) {
                System.out.println("two clicks");

                Hex hex = counter.getHex();
                if (hex.isCountersStacked()) {
                    hex.unstackCounters();
                } else {
                    hex.stackCounters();
                }
            }
        }
        GameFunction.displayCountersInHex(counter.getHex());
    }
}
