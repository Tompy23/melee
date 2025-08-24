package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;

public class GladiatorMoveStateImpl extends AbstractGameState {
    private final Player player;
    private final Player npc;

    public GladiatorMoveStateImpl() {
        player = GladiatorData.get().getPlayer();
        npc = GladiatorData.get().getNpc();
    }

    @Override
    public void beginState() {
        moveGladiator(player);
        //moveGladiator(npc);
        GladiatorData.get().getController().getPaneCombat().setVisible(true);
    }
    @Override
    public void endState() {
        GladiatorData.get().getController().getPaneMove().setVisible(false);
    }

    private void moveGladiator(Player player) {
        Counter counter = player.getCounter();
        counter.setFacing(player.getMoveToRotation() + 1);
        counter.getHex().removeCounter(counter);
        player.getMoveToHex().addCounter(counter);
    }

    @Override
    public void process(long l) {
        // Determine the next step
        // 1. Collision
        // 2. Kick/recover, etc
        // 3. Combat
        // 4. ???

        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_COMBAT_SETUP).build());
    }

}
