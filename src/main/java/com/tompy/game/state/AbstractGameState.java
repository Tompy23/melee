package com.tompy.game.state;

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
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        GameFunction.fillHexGreenOrTransparent((Hex) ((Polygon) event.getTarget()).getUserData());
    }

    @Override
    public void onMouseEnterCounter(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getTarget();

        rectangle.setStrokeWidth(6);
        rectangle.setStroke(Color.BLACK);
    }

    @Override
    public void onMouseLeaveCounter(MouseEvent event) {
        Rectangle rectangle = (Rectangle) event.getTarget();
        Counter counter = (Counter) rectangle.getUserData();

        if (counter.isSelected()) {
            rectangle.setStrokeWidth(6.0);
            rectangle.setStroke(Color.GREEN);
        } else {
            rectangle.setStrokeWidth(0.0);
        }
    }

    @Override
    public void onMouseClickCounter(MouseEvent event) {
        System.out.println("one click");
        Rectangle rectangle = (Rectangle) event.getTarget();
        Counter counter = (Counter) rectangle.getUserData();

        if (counter.isSelected()) {
            rectangle.setStrokeWidth(0.0);
            counter.unselect();
        } else {
            rectangle.setStrokeWidth(6);
            rectangle.setStroke(Color.GREEN);
            counter.select();
        }
    }

    @Override
    public void onMouseDoubleClickCounter(MouseEvent event) {
        System.out.println("two clicks");
        Rectangle rectangle = (Rectangle) event.getTarget();

        Counter counter = (Counter) rectangle.getUserData();

        Hex hex = (Hex) counter.getHex();
        if (hex.isCountersStacked()) {
            hex.unstackCounters();
        } else {
            hex.stackCounters();
        }
    }
}
