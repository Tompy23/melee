package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.counter.CounterFactory;
import com.tompy.counter.CounterType;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.event.GameFunction;
import com.tompy.game.state.AbstractGameState;

public class PlayGladiatorBeginStateImpl extends AbstractGameState {

    @Override
    public void beginState() {
        // Give the Gladiator a counter and put the counter on the map
        Counter playerCounter = CounterFactory.counterBuilder().hex(GameHexBoardData.get().getHexBoard().getHex(10, 2))
                .type(CounterType.GLADIATOR).imageName("gladiator.png").build();
        Counter npcCounter = CounterFactory.counterBuilder().hex(GameHexBoardData.get().getHexBoard().getHex(6, 6))
                .type(CounterType.GLADIATOR).imageName("gladiator2.png").build();
        playerCounter.setFacing(3);
        GameFunction.displayCountersInHex(playerCounter.getHex(), GameHexBoardData.get().getPaneHexBoard());
        GameFunction.displayCountersInHex(npcCounter.getHex(), GameHexBoardData.get().getPaneHexBoard());

        // Create the individual Gladiators
        Player player = Player.builder().counter(playerCounter).build();
        Player npc = Player.builder().counter(npcCounter).build();

        // Build the global data
        GladiatorData.builder().player(player).npc(npc).init();
    }
}
