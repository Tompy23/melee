package com.tompy.game.state;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public abstract class AbstractGameState implements GameState, GamePlayHandler {
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
    public void onClickMove1(ActionEvent event) {

    }

    @Override
    public void onClickTestPointer(ActionEvent event) {

    }

    @Override
    public void onMouseMove(MouseEvent event) {

    }

    @Override
    public void onClickDrawLine(ActionEvent event) {

    }

    @Override
    public void onClickHandleFindPath(ActionEvent event) {
        
    }
}
