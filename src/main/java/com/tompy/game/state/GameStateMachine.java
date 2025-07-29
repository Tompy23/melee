package com.tompy.game.state;

import com.tompy.state.AbstractStateMachine;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class GameStateMachine extends AbstractStateMachine<GameState> implements GameHandler {
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
    public void onMouseEnterCounter(MouseEvent event) {
        currentState.onMouseEnterCounter(event);
    }

    @Override
    public void onMouseLeaveCounter(MouseEvent event) {
        currentState.onMouseLeaveCounter(event);
    }

    @Override
    public void onClickCounter(MouseEvent event) {
        currentState.onClickCounter(event);
    }

    @Override
    public void onClickMove1(ActionEvent event) {
        currentState.onClickMove1(event);
    }

    @Override
    public void onClickTestPointer(ActionEvent event) {
        changeState(GameStateFactory.get().buidler().type(GameStateType.TEST_POINTER).build());
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        currentState.onMouseMove(event);
    }
}
