package com.tompy.game.state;

import javafx.animation.AnimationTimer;

public class GameTimer extends AnimationTimer {
    @Override
    public void handle(long l) {
        GameStateMachine.get().process(l);
    }
}
