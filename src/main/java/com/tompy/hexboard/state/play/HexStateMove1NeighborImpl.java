package com.tompy.hexboard.state.play;

import com.tompy.game.GameProperty;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.state.AbstractHexState;
import javafx.scene.paint.Color;

public class HexStateMove1NeighborImpl extends AbstractHexState {
    private GameProperty<Hex> movedHex;
    private final Color color;
    private final double opaqueness;
    private final Color enterColor;

    public HexStateMove1NeighborImpl(Hex hex, Color color, double opaqueness, Color enterColor, GameProperty<Hex> movedHex) {
        this.hex = hex;
        this.color = color;
        this.opaqueness = opaqueness;
        this.enterColor = enterColor;
        this.movedHex = movedHex;
    }

    @Override
    public void beginState() {
        hex.setFill(color);
        hex.setOpacity(opaqueness);
    }

    @Override
    public void endState() {
        hex.setFill(Color.TRANSPARENT);
    }

    @Override
    public void handleEnter() {
        hex.setFill(enterColor);
        hex.setOpacity(opaqueness);
    }

    @Override
    public void handleExit() {
        hex.setFill(color);
        hex.setOpacity(opaqueness);
    }

    @Override
    public void handleClick() {
        movedHex.set(hex);
    }
}
