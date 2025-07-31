package com.tompy.hexboard.state;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;

public class HexStateCommonImpl extends AbstractHexState {

    public HexStateCommonImpl(Hex hex) {
        this.hex = hex;
    }

    @Override
    public void handleClick() {
        if (hex.isSelected()) {
            hex.unselect();
            hex.changeState(
                    HexStateFactory.get().builder().type(HexStateType.COMMON).hex(hex).switch1(false).build());
        } else {
            hex.select();
            hex.changeState(HexStateFactory.get().builder().type(HexStateType.SELECTED).hex(hex).switch1(true)
                    .initialColor(Color.GREEN).secondaryColor(Color.TRANSPARENT).opaqueness(0.5)
                    .style("-fx-font: 10 arial;").xOffset(-2).yOffset(20).display(hex.getCoordinate().toString())
                    .build());
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
