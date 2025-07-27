package com.tompy.game.state;

import com.tompy.game.GameData;
import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    @Override
    public void handle(long l) {
        GameStateMachine.get().process(l);
        GameData.get().getHexBoard().process(l);
    }
}
