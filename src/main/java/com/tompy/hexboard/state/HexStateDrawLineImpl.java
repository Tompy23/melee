package com.tompy.hexboard.state;

import com.tompy.game.GameProperty;
import com.tompy.hexboard.Hex;

public class HexStateDrawLineImpl extends AbstractHexState {
    GameProperty<Hex> originHex;
    GameProperty<Hex> hexWithMouse;

    public HexStateDrawLineImpl(Hex hex, GameProperty<Hex> originHex, GameProperty<Hex> hexWithMouse) {
        this.hex = hex;
        this.originHex = originHex;
        this.hexWithMouse = hexWithMouse;
    }

    @Override
    public void handleClick() {
        originHex.set(hex);
    }

    @Override
    public void handleEnter() {
        hexWithMouse.set(hex);
    }

    @Override
    public void handleExit() {
        //hexWithMouse.set(null);
    }
}
