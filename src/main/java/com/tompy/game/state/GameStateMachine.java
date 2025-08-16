package com.tompy.game.state;

import com.tompy.state.AbstractStateMachine;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class GameStateMachine extends AbstractStateMachine<GameState> implements GamePlayHandler {
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
    public void onClickMove1(ActionEvent event) {
        currentState.onClickMove1(event);
    }

    @Override
    public void onClickTestPointer(ActionEvent event) {
        changeState(GameStateFactory.buidler().type(GameStateType.TEST_POINTER).build());
    }

    @Override
    public void onClickDrawLine(ActionEvent event) {
        changeState(GameStateFactory.buidler().type(GameStateType.DRAW_LINE).build());
    }

    @Override
    public void onClickHandleFindPath(ActionEvent event) {
        changeState((GameStateFactory.buidler().type(GameStateType.FIND_PATH).build()));
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        currentState.onMouseMove(event);
    }
}
