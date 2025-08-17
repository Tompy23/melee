package com.tompy.game.state;

import com.tompy.state.AbstractStateMachine;

public class GameStateMachine extends AbstractStateMachine<GameState> {
    private static GameStateMachine stateMahcine;
    private GameState currentState;

    public static GameStateMachine get() {
        if (stateMahcine == null) {
            stateMahcine = new GameStateMachine();
        }
        return stateMahcine;
    }

    @Override
    public void changeState(GameState newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        currentState.beginState();
    }

    @Override
    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
    }

    @Override
    public boolean stopThread() {
        return false;
    }

    @Override
    public GameState getCurrentState() {
        return currentState;
    }
}
