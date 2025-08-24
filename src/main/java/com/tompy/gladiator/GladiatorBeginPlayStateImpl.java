package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.counter.CounterFactory;
import com.tompy.counter.CounterType;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.event.GameFunction;
import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.gladiator.details.Armor;
import com.tompy.gladiator.details.PhysicalCharacteristics;
import com.tompy.gladiator.details.Shield;

import java.util.Random;

public class GladiatorBeginPlayStateImpl extends AbstractGameState {
    private Random rand;
    private PhysicalCharacteristics characteristics;

    public GladiatorBeginPlayStateImpl() {
        rand = new Random(System.currentTimeMillis());
        characteristics = new PhysicalCharacteristics();
    }

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
        // TODO create a player factory to handle random attributes, etc.
        Player player = GladiatorData.get().getPlayer();
        Player npc = GladiatorData.get().getNpc();
        player.setCounter(playerCounter);
        npc.setCounter(npcCounter);

        player.setCharacteristics(characteristics.getCharacteristics(rand.nextInt(1, 32)));
        npc.setCharacteristics(characteristics.getCharacteristics(rand.nextInt(1,32)));
        setArmor(player);
        setArmor(npc);
    }

    private void setArmor(Player player) {
        String[] armorString = Armor.getArmorEntry(player.getType(), rand.nextInt(1, 6));
        for (int i = 0; i < 5; i++) {
            player.setBodyArmor(Player.BodyArea.getBodyArea(i), parse(armorString[i]));
        }

        if (player.getType().equals(Player.GladiatorType.LIGHT)) {
            if (rand.nextInt(1, 6) == 1) {
                player.setShield(new Shield(Shield.ShieldType.LARGE));
            } else {
                player.setShield(new Shield(Shield.ShieldType.SMALL));
            }
        } else {
            player.setShield(new Shield(Shield.ShieldType.LARGE));
        }
    }

    private Armor parse(String s) {
        if (s.equals("-")) {
            return new Armor(Armor.ArmorType.NONE, 0);
        }
        int coverage = Integer.parseInt(s.substring(1));
        if (coverage == 0) {
            coverage = 99;
        }
        return new Armor(Armor.ArmorType.getArmorType(s.substring(0)), coverage);
    }

    @Override
    public void process(long l) {
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_PREPARE_MOVE).build());
    }
}
