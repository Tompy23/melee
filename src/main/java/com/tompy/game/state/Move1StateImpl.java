package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

public class Move1StateImpl extends AbstractGameState {
    private final Hex originHex;
    private final List<Counter> counters;
    private Hex movedHex;
    private Hex currentHex;
    private List<Hex> neighbors;

    public Move1StateImpl(Hex originHex) {
        this.originHex = originHex;
        this.counters = originHex.getCounters().stream().filter(Counter::isSelected).toList();
        this.movedHex = null;
        this.currentHex = originHex;
        this.neighbors = HexFunction.getNeighbors(originHex);
        this.showPossibleHexes();
    }

    @Override
    public void beginState() {
        counters.forEach(Counter::resetMovementExpended);
        clearPossibleHexes();
        showPossibleHexes();
    }

    @Override
    public void process(long l) {
        if (movedHex != null && !movedHex.equals(currentHex)) {
            clearPossibleHexes();
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
                GameStateMachine.get().changeState(StateFactory.get().buidler().type(StateType.COMMON).build());
            } else {
                showPossibleHexes();
            }
        }
    }

    private void clearPossibleHexes() {
        neighbors.forEach(h -> h.setFill(Color.TRANSPARENT));
        neighbors.stream().map(Hex::getCoordinate).map(Objects::toString).forEach(GameFunction::removeText);
    }

    private void showPossibleHexes() {
        neighbors.forEach(h -> h.setOpacity(0.5));
        neighbors.forEach(h -> h.setFill(Color.GRAY));
        neighbors.forEach(this::addMovementPointText);
    }

    private void addMovementPointText(Hex hex) {
        StringBuilder sb = new StringBuilder();
        Long movement = counters.stream().map(Counter::getMovement).min(Long::compare).orElseThrow();
        long movementExpended = counters.stream().findFirst().map(Counter::getMovementExpended).orElseThrow();

        sb.append(movement - movementExpended);
        sb.append("/");
        sb.append(hex.getEntryCost());
        GameFunction.showTextHex(hex, sb.toString());
    }

    @Override
    public void onClickMove1(ActionEvent event) {
        clearPossibleHexes();
        GameStateMachine.get().changeState(StateFactory.get().buidler().type(StateType.COMMON).build());
    }

    @Override
    public void onClickCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();
        clickHexAction(counter.getHex(), event.getButton().equals(MouseButton.SECONDARY));
    }

    @Override
    public void onClickHex(MouseEvent event) {
        clickHexAction((Hex) event.getTarget(), event.getButton().equals(MouseButton.SECONDARY));
    }

    private void clickHexAction(Hex hex, boolean rightClick) {
        if (!rightClick) {
            if (neighbors.contains(hex)) {
                movedHex = hex;
            }
        }
    }

    @Override
    public void onMouseEnterCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();
        enterHexAction(counter.getHex());
    }

    @Override
    public void onMouseEnterHex(MouseEvent event) {
        enterHexAction((Hex) event.getTarget());
    }

    private void enterHexAction(Hex hex) {
        GameData.get().setHexWithMouse(hex);
    }

    @Override
    public void onMouseLeaveCounter(MouseEvent event) {
        Counter counter = (Counter) event.getTarget();
        leaveHexAction();
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        leaveHexAction();
    }

    private void leaveHexAction() {
        GameData.get().setHexWithMouse(null);
    }
}
