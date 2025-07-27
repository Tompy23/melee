package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.event.ActionEvent;

public class CommonGameStateImpl extends AbstractGameState {

    @Override
    public void process(long l) {
        int hexesSelected = 0;
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            long selected = hex.getCounters().stream().filter(Counter::isSelected).count();
            if (selected > 0) {
                hexesSelected++;
            }
        }
        GameData.get().getController().enableMove1Button(hexesSelected == 1);
    }

    @Override
    public void onClickMove1(ActionEvent event) {
        Hex originHex = null;
        long hexCount = 0;
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            if (hex.getCounters().stream().anyMatch(Counter::isSelected)) {
                hexCount++;
                originHex = hex;
            }
        }
        if (hexCount == 1) {
            GameStateMachine.get().changeState(GameStateFactory.get().buidler().type(GameStateType.MOVE_1).originHex(originHex).build());
        }
    }
}
