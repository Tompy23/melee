package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;

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
}
