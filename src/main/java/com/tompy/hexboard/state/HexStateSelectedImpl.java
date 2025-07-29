package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;

public class HexStateSelectedImpl extends AbstractHexStateDecorator {
    private final Color selected;
    private final double selectedOpaqueness;
    private final Color unselected;
    

    public HexStateSelectedImpl(Hex hex, HexState wrappedState, Color selected, Color unselected, double selectedOpaqueness) {
        this.hex = hex;
        this.wrappedState = wrappedState;
        this.selected = selected;
        this.unselected = unselected;
        this.selectedOpaqueness = selectedOpaqueness;
    }

    @Override
    public void beginState() {
        super.beginState();
        hex.setOpacity(selectedOpaqueness);
        hex.setFill(selected);
    }

    @Override
    public void endState() {
        super.endState();
        hex.setOpacity(1.0);
        hex.setFill(unselected);
    }

    @Override
    public void process(long l) {
        super.process(l);
    }
}
