package com.tompy.game.state.play;

import com.tompy.game.GameHexBoardData;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TestHexPointerState extends AbstractGamePlayState {
    public static long start = 0;

    public Hex currentHex = null;

    @Override
    public void beginState() {
        GameHexBoardData.get().getHexBoard().getHexes()
                .forEach(h -> h.changeState(HexStateFactory.builder().type(HexStateType.NOTHING).build()));
    }

    @Override
    public void endState() {
        GameHexBoardData.get().getHexBoard().getHexes()
                .forEach(h -> h.changeState(HexStateFactory.builder().type(HexStateType.COMMON).build()));
    }

    @Override
    public void process(long l) {
        Hex hex = HexFunction.pixelToHex(GameHexBoardData.get().getMouseX(), GameHexBoardData.get().getMouseY());

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
        Point2D point = GameHexBoardData.get().getPaneHexBoard()
                .sceneToLocal(event.getSceneX(), event.getSceneY());
        GameHexBoardData.get().setMousePointer(point.getX(), point.getY());
    }
}
