package com.tompy.game.state;

import com.tompy.game.GameHexBoardData;
import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    @Override
    public void handle(long l) {
        GameStateMachine.get().process(l);
        if (GameHexBoardData.isInitialized()) {
            GameHexBoardData.get().getHexBoard().process(l);
        }
    }
}
