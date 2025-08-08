package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.hexboard.DoubleCubeCoordinate;
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
        DoubleCubeCoordinate dcc = new DoubleCubeCoordinate(GameData.get().getMouseX(), GameData.get().getMouseY());
        Hex hex = HexFunction.pixelToHex(GameData.get().getMouseX(), GameData.get().getMouseY());
        //System.out.println((currentHex != null ? currentHex.toString() : "NULL") + " /// " + hex.toString());
        if (hex != currentHex) {
            if (currentHex != null) {
                currentHex.setFill(Color.TRANSPARENT);
            }
            hex.setOpacity(1.0);
            hex.setFill(Color.BLACK);
            currentHex = hex;
        }


        //        StringBuilder sb = new StringBuilder();
        //        sb.append(GameData.get().getMouseX()).append(" : ").append(GameData.get().getMouseY());
        //        System.out.println(sb.toString());

        if (start == 0) {
            start = l;
        }
        if (l - start > 10000) {
            //GameStateMachine.get().changeState(StateFactory.get().buidler().type(StateType.COMMON).build());
        }

    }

    @Override
    public void onMouseMove(MouseEvent event) {
        System.out.println("TEST HEX");
        //GameData.get().setMousePointer(event.getSceneX(), event.getSceneY());
        Point2D point = GameData.get().getController().getHexBoardPane()
                .sceneToLocal(event.getSceneX(), event.getSceneY());
        GameData.get().setMousePointer(point.getX(), point.getY());
        StringBuilder sb = new StringBuilder();
        sb.append("MOUSE:  ").append(GameData.get().getMouseX()).append(" : ").append(GameData.get().getMouseY());
        System.out.println(sb.toString());
    }
}
