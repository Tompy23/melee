package com.tompy.hexboard.state;

import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HexStateShowCoordinatesImpl extends AbstractHexStateDecorator {
    private Text text;

    public HexStateShowCoordinatesImpl(Hex hex, HexState wrappedState) {
        this.hex = hex;
        this.wrappedState = wrappedState;
    }

    @Override
    public void beginState() {
        Pane hexTextPane = GameData.get().getController().getTextPane();
        text = new Text();
        text.setStyle("-fx-font: 10 arial;");
        text.setText(hex.getCoordinate().toString());
        text.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (-2 + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() + 20);
        text.setId(hex.getCoordinate().toString());
        hexTextPane.getChildren().add(text);
        super.beginState();
    }

    @Override
    public void endState() {
        Pane hexTextPane = GameData.get().getController().getTextPane();
        hexTextPane.getChildren().remove(text);
        super.endState();
    }


    @Override
    public void handleEnter() {
        wrappedState.handleEnter();
    }

    @Override
    public void handleExit() {
        wrappedState.handleExit();
    }
}
