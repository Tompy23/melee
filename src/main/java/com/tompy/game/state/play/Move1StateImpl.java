package com.tompy.game.state.play;

import com.tompy.counter.Counter;
import com.tompy.counter.state.CounterStateFactory;
import com.tompy.counter.state.CounterStateType;
import com.tompy.game.GameData;
import com.tompy.game.GameProperty;
import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import java.util.List;

public class Move1StateImpl extends AbstractGameState {
    private final Hex originHex;
    private final List<Counter> counters;
    private Hex currentHex;
    private List<Hex> neighbors;
    private GameProperty<Hex> movedHexProperty;
    private long pause;

    public Move1StateImpl(Hex originHex) {
        this.originHex = originHex;
        this.counters = originHex.getCounters().stream().filter(Counter::isSelected).toList();
        this.currentHex = originHex;
        this.neighbors = HexFunction.getNeighbors(originHex);
        this.movedHexProperty = new GameProperty<>();
    }

    @Override
    public void beginState() {
        counters.forEach(Counter::resetMovementExpended);
        GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(HexStateFactory.builder().hex(h).type(HexStateType.MOVE1).build()));
        neighbors.forEach(n -> n.changeState(HexStateFactory.builder().hex(n).type(HexStateType.MOVE1_NEIGHBOR).initialColor(Color.GRAY).opaqueness(0.5).secondaryColor(Color.CHOCOLATE).gameState(this).hex2(movedHexProperty).style("-fx-font: 16 arial;").xOffset(-2).yOffset(20).display(addMovementPointText(n)).build()));
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            hex.getCounters().forEach(c -> c.changeState(CounterStateFactory.builder().type(CounterStateType.NO_CLICK_WRAPPER).counter(c).previousState(c.getCurrentState()).build()));
        }
    }

    @Override
    public void process(long l) {
        if (movedHexProperty.isChanged()) {
            Hex movedHex = movedHexProperty.get();
            currentHex.stackCounters();
            movedHex.stackCounters();
            counters.forEach(currentHex::removeCounter);
            counters.forEach(movedHex::addCounter);
            currentHex = movedHex;
            neighbors = HexFunction.getNeighbors(currentHex);
            counters.forEach(c -> c.expendMovement(currentHex.getEntryCost()));
            boolean movementEnds = false;
            for (Counter counter : counters) {
                if (counter.getMovement() - counter.getMovementExpended() == 0) {
                    movementEnds = true;
                    break;
                }
            }
            if (movementEnds) {
                onClickMove1(null);
//                GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.COMMON).build());
//                GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(HexStateFactory.builder().hex(h).type(HexStateType.COMMON).switch1(false).build()));
            } else {
                GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(HexStateFactory.builder().hex(h).type(HexStateType.MOVE1).build()));
                neighbors.forEach(n -> n.changeState(HexStateFactory.builder().hex(n).type(HexStateType.MOVE1_NEIGHBOR).initialColor(Color.GRAY).secondaryColor(Color.CHOCOLATE).opaqueness(0.5).gameState(this).hex2(movedHexProperty).style("-fx-font: 16 arial;").xOffset(-2).yOffset(20).display(addMovementPointText(n)).build()));
            }
        }
    }

    private String addMovementPointText(Hex hex) {
        StringBuilder sb = new StringBuilder();
        Long movement = counters.stream().map(Counter::getMovement).min(Long::compare).orElseThrow();
        long movementExpended = counters.stream().findFirst().map(Counter::getMovementExpended).orElseThrow();

        sb.append(movement - movementExpended);
        sb.append("/");
        sb.append(hex.getEntryCost());
        return sb.toString();
    }

    @Override
    public void onClickMove1(ActionEvent event) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.COMMON).build());
        GameData.get().getHexBoard().getHexes().forEach(h -> h.changeState(HexStateFactory.builder().hex(h).type(HexStateType.COMMON).switch1(false).build()));
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            hex.getCounters().forEach(c -> c.changeState(c.getPreviousState()));
        }
    }
}
