package com.tompy.gladiator;

import com.tompy.game.state.GameState;
import com.tompy.state.AbstractStateMachine;

public class GladiatorStateMachine extends AbstractStateMachine<GameState> {
    @Override
    public boolean stopThread() {
        return false;
    }
}
