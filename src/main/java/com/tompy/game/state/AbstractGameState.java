package com.tompy.game.state;

import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public abstract class AbstractGameState implements GameHandler {
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
        // if selected, turn green, else turn transparent
        GameFunction.fillHexGreenOrTransparent((Hex) ((Polygon) event.getTarget()).getUserData());
    }
}
