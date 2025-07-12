package com.tompy.game.event;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;

public class GameFunction {

    public static void selectHexGreenOrBlue(Hex hex) {
        if (hex.isSelected()) {
            hex.unselect();
            fillHexHalfBlue(hex);
        } else {
            hex.select();
            fillHexGreen(hex);
        }
    }

    public static void fillHexHalfBlue(Hex hex) {
        hex.getPolygon().setOpacity(.5);
        hex.getPolygon().setFill(Color.BLUE);
    }

    public static void fillHexTransparent(Hex hex) {
        hex.getPolygon().setFill(Color.TRANSPARENT);
    }

    public static void fillHexGreen(Hex hex) {
        hex.getPolygon().setFill(Color.GREEN);
    }

    public static void fillHexGreenOrTransparent(Hex hex) {
        if (hex.isSelected()) {
            GameFunction.fillHexGreen(hex);
        } else {
            GameFunction.fillHexTransparent(hex);
        }
    }
}
