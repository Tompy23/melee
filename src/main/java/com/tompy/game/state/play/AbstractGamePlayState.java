package com.tompy.game.state.play;

import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import javafx.event.ActionEvent;

public class AbstractGamePlayState extends AbstractGameState {

    @Override
    public void onClickTestPointer(ActionEvent event) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.TEST_POINTER).build());
    }

    @Override
    public void onClickDrawLine(ActionEvent event) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.DRAW_LINE).build());
    }

    @Override
    public void onClickHandleFindPath(ActionEvent event) {
        GameStateMachine.get().changeState((GameStateFactory.buidler().type(GameStateType.FIND_PATH).build()));
    }
}
