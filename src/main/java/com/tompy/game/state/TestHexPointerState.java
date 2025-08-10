package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TestHexPointerState extends AbstractGameState {
    public static long start = 0;

    public Hex currentHex = null;

    @Override
    public void beginState() {
        GameData.get().getHexBoard().getHexes()
                .forEach(h -> h.changeState(HexStateFactory.builder().type(HexStateType.NOTHING).build()));
    }

    @Override
    public void endState() {
        GameData.get().getHexBoard().getHexes()
                .forEach(h -> h.changeState(HexStateFactory.builder().type(HexStateType.COMMON).build()));
    }

    @Override
    public void process(long l) {
        Hex hex = HexFunction.pixelToHex(GameData.get().getMouseX(), GameData.get().getMouseY());

        if (hex != currentHex && hex != null) {
            if (currentHex != null) {
                currentHex.setFill(Color.TRANSPARENT);
            }
            hex.setOpacity(1.0);
            hex.setFill(Color.BLACK);
            currentHex = hex;
        }
    }

    @Override
    public void onMouseMove(MouseEvent event) {
        Point2D point = GameData.get().getController().getHexBoardPane()
                .sceneToLocal(event.getSceneX(), event.getSceneY());
        GameData.get().setMousePointer(point.getX(), point.getY());
    }
}
