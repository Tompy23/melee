package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.event.ActionEvent;

public class CommonGameStateImpl extends AbstractGameState {
    private static long pause = 0;
    private static final long WAIT = 10000;
    @Override
    public void process(long l) {
        if (l > pause) {
            pause += WAIT;

            int hexesSelected = 0;
            for (Hex hex : GameData.get().getHexBoard().getHexes()) {
                long selected = hex.getCounters().stream().filter(Counter::isSelected).count();
                if (selected > 0) {
                    hexesSelected++;
                }
            }
            GameData.get().getController().enableMove1Button(hexesSelected == 1);
        }
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
            GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.MOVE_1).originHex(originHex).build());
        }
    }

    @Override
    public void onClickHandleFindPath(ActionEvent event) {

    }
}
