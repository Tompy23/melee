package com.tompy.game.state;

import com.tompy.game.GameHexBoardData;
import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    public static GameTimer singleton;

    public static GameTimer get() {
        if (singleton == null) {
            singleton = new GameTimer();
        }
        return singleton;
    }

    @Override
    public void handle(long l) {
        GameStateMachine.get().process(l);
        if (GameHexBoardData.isInitialized()) {
            GameHexBoardData.get().getHexBoard().process(l);
        }
    }
}
