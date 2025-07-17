package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

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
        GameFunction.selectHexGreenOrBlue((Hex) ((Polygon) event.getTarget()).getUserData());
    }

    @Override
    public void onMouseEnterHex(MouseEvent event) {
        GameFunction.fillHexHalfBlue((Hex) ((Polygon) event.getTarget()).getUserData());

        Polygon p = (Polygon) event.getTarget();
        GameData.get().setHexWithMouse((Hex) p.getUserData());
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        GameFunction.fillHexGreenOrTransparent((Hex) ((Polygon) event.getTarget()).getUserData());

        GameData.get().setHexWithMouse(null);
    }

    @Override
    public void onMouseEnterCounter(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getTarget();

        rectangle.setStroke(Color.BLACK);

        Hex hex = GameFunction.getHex(rectangle);
        GameFunction.fillHexHalfBlue(hex);
        GameData.get().setHexWithMouse(hex);
    }

    @Override
    public void onMouseLeaveCounter(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getTarget();
        Counter counter = (Counter) rectangle.getUserData();

        if (counter.isSelected()) {
            rectangle.setStroke(Color.GREEN);
        } else {
            rectangle.setStroke(Color.TRANSPARENT);
        }
        GameFunction.fillHexGreenOrTransparent(counter.getHex());

        GameData.get().setHexWithMouse(null);
    }

    @Override
    public void onMouseClickCounter(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getTarget();
        Counter counter = (Counter) rectangle.getUserData();
        if (event.getClickCount() == 1) {
            System.out.println("one click");
            GameFunction.unselectAllCountersInHex(GameData.get().getHexWithMouse());

            if (counter.isSelected()) {
                rectangle.setStroke(Color.TRANSPARENT);
                counter.unselect();
            } else {
                rectangle.setStroke(Color.GREEN);
                counter.select();
            }
        } else if (event.getClickCount() == 2) {
            System.out.println("two clicks");

            Hex hex = counter.getHex();
            if (hex.isCountersStacked()) {
                hex.unstackCounters();
            } else {
                hex.stackCounters();
            }
        }
        GameFunction.displayCountersInHex(counter.getHex());
    }
}
