package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;

public class HexStateMove1Impl extends AbstractHexState {

    public HexStateMove1Impl(Hex hex) {
        this.hex = hex;
    }

    @Override
    public void beginState() {
        hex.setFill(Color.TRANSPARENT);
    }
}
