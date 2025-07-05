package com.tompy.game.state;

import javafx.scene.input.MouseEvent;

public class GameStateMachine {
    private static GameStateMachine stateMahcine;
    private GameState currentState;

    public static GameStateMachine get() {
        if (stateMahcine == null) {
            stateMahcine = new GameStateMachine();
        }
        return stateMahcine;
    }

    public void changeState(GameState newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        currentState.beginState();
    }

    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
    }

    public void onClickHex(MouseEvent event) {
        currentState.onClickHex(event);
    }

    public void onMouseEnterHex(MouseEvent event) {
        currentState.onMouseEnterHex(event);
    }

    public void onMouseLeaveHex(MouseEvent event) {
        currentState.onMouseLeaveHex(event);
    }
}
