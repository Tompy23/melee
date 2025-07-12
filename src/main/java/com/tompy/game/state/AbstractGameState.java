package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

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
        System.out.println("Mouse Down");
        Polygon target = (Polygon) event.getTarget();
        Hex hex = (Hex) target.getUserData();
        if (hex.isSelected()) {
            hex.unselect();
        } else {
            hex.select();
            target.setOpacity(.5);
            target.setFill(Color.GREEN);
        }
    }

    @Override
    public void onMouseEnterHex(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        p.setOpacity(.5);
        p.setFill(Color.BLUE);
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        Hex h = (Hex) p.getUserData();
        if (!h.isSelected()) {
            p.setFill(Color.TRANSPARENT);
        } else {
            p.setOpacity(.5);
            p.setFill(Color.GREEN);
        }
    }
}
