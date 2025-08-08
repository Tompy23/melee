package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

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
    public void onClickMove1(ActionEvent event) {

    }

    @Override
    public void onClickTestPointer(ActionEvent event) {

    }

    @Override
    public void onMouseMove(MouseEvent event) {

    }
}
