package com.tompy.game.state;

import com.tompy.game.event.GameFunction;
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
        GameFunction.selectHexGreenOrBlue((Polygon) event.getTarget());
    }

    @Override
    public void onMouseEnterHex(MouseEvent event) {
        GameFunction.fillHexHalfBlue((Polygon) event.getTarget());
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        // if selected, turn green, else turn transparent
        GameFunction.fillHexGreenOrTransparent((Polygon) event.getTarget());
    }
}
