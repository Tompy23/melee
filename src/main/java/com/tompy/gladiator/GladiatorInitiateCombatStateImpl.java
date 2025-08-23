package com.tompy.gladiator;

import com.tompy.game.state.AbstractGameState;
import javafx.scene.layout.Pane;

public class GladiatorInitiateCombatStateImpl extends AbstractGameState {

    public GladiatorInitiateCombatStateImpl() {

    }

    @Override
    public void beginState() {
        GladiatorData.get().getController().getPaneCombat().setVisible(true);
    }
}
