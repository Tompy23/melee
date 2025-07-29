package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;

public class HexStateCommonImpl extends AbstractHexState {
    private final boolean selected;

    public HexStateCommonImpl(Hex hex, boolean selected) {
        this.hex = hex;
        this.selected = selected;
    }

    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {
        if (hex.isSelected() && !selected) {
            hex.changeState(HexStateFactory.get().builder().type(HexStateType.SELECTED).hex(hex).switch1(true).initialColor(Color.GREEN).secondaryColor(Color.TRANSPARENT).opaqueness(0.5).build());
        } else if (!hex.isSelected() && selected) {
            hex.changeState(HexStateFactory.get().builder().type(HexStateType.COMMON).hex(hex).switch1(false).build());
        }
    }

    @Override
    public void handleEnter() {
        hex.setOpacity(.5);
        hex.setFill(Color.BLUE);
    }

    @Override
    public void handleExit() {
        hex.setOpacity(1.0);
        hex.setFill(Color.TRANSPARENT);
    }
}
