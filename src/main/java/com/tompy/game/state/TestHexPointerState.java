package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.DoubleCubeCoordinate;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TestHexPointerState extends AbstractGameState {
    public static long start = 0;

    public Hex currentHex = null;

    @Override
    public void process(long l) {
        DoubleCubeCoordinate dcc = new DoubleCubeCoordinate(GameData.get().getMouseX(), GameData.get().getMouseY());
        Hex hex = HexFunction.pixelToHex(GameData.get().getMouseX(), GameData.get().getMouseY());
        if (hex != currentHex) {
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
        GameData.get().setMousePointer(event.getSceneX(), event.getSceneY());
        StringBuilder sb = new StringBuilder();
        sb.append("MOUSE:  ").append(GameData.get().getMouseX()).append(" : ").append(GameData.get().getMouseY());
        System.out.println(sb.toString());
    }
}
