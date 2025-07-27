package com.tompy.hexboard;

import javafx.scene.paint.Color;

public class HexStateSelectedImpl extends AbstractHexState {
    private final Color selected;
    private final double selectedOpaqueness;
    private final Color unselected;
    

    public HexStateSelectedImpl(Hex hex, Color selected, Color unselected, double selectedOpaqueness) {
        this.hex = hex;
        this.selected = selected;
        this.unselected = unselected;
        this.selectedOpaqueness = selectedOpaqueness;
    }

    @Override
    public void beginState() {
        hex.setOpacity(selectedOpaqueness);
        hex.setFill(selected);
    }

    @Override
    public void endState() {
        hex.setOpacity(1.0);
        hex.setFill(unselected);
    }

    @Override
    public void process(long l) {
        if (!hex.isSelected()) {
            hex.changeState(null);
        }
    }
}
