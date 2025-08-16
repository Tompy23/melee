package com.tompy.hexboard.state.play;

import com.tompy.game.GameData;
import com.tompy.game.play.GamePlayData;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.state.AbstractHexStateDecorator;
import com.tompy.hexboard.state.HexState;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HexStateDisplayTextImpl extends AbstractHexStateDecorator {
    private Text text;
    private final String style;
    private final long xOffset;
    private final long yOffset;
    private final String display;

    public HexStateDisplayTextImpl(Hex hex, HexState wrappedState, String style, long xOffset, long yOffset, String display) {
        this.hex = hex;
        this.wrappedState = wrappedState;
        this.style = style;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.display = display;
    }

    @Override
    public void beginState() {
        Pane hexTextPane = GameData.get().getPaneText();
        text = new Text();
        text.setStyle(style);
        text.setText(display);
        text.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (xOffset + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() + yOffset);
        text.setId(hex.getCoordinate().toString());
        hexTextPane.getChildren().add(text);
        super.beginState();
    }

    @Override
    public void endState() {
        Pane hexTextPane = GameData.get().getPaneText();
        hexTextPane.getChildren().remove(text);
        super.endState();
    }
}
