package com.tompy.gladiator;

import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import javafx.concurrent.Task;

import java.util.concurrent.Future;

public class GladiatorPrepareMoveStateImpl extends AbstractGameState {
    private Future<Boolean> npcMove;

    public GladiatorPrepareMoveStateImpl() {
   }

    @Override
    public void beginState() {
        GladiatorData.get().getController().getPaneMove().setVisible(true);
        GladiatorData.get().setPlayerMoveChoice(false);
        // Set up NPC moving function/strategy
        npcMove = new Task<Boolean>() {
            @Override
            public Boolean call() {
                return true;
            }
        };
    }

    @Override
    public void process(long l) {
        if (npcMove.isDone() && GladiatorData.get().isPlayerMoveChoice()) {
            GameStateMachine.get()
                    .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).build());
        }
    }


}
