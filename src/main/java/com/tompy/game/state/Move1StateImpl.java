package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class Move1StateImpl extends AbstractGameState {
    private final Hex originHex;
    private final List<Counter> counters;

    public Move1StateImpl(Hex originHex) {
        this.originHex = originHex;
        this.counters = originHex.getCounters().stream().filter(Counter::isSelected).toList();
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
        leaveHexAction(counter.getHex());
    }

    @Override
    public void onMouseLeaveHex(MouseEvent event) {
        leaveHexAction((Hex) event.getTarget());
    }

    private void leaveHexAction(Hex hex) {
        GameData.get().setHexWithMouse(null);
    }
}
