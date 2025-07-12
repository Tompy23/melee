package com.tompy.game.event;

import com.tompy.hexboard.Hex;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GameFunction {

    public static void selectHexGreenOrBlue(Polygon target) {
        Hex hex = (Hex) target.getUserData();

        if (hex.isSelected()) {
            hex.unselect();
            fillHexHalfBlue(target);
        } else {
            hex.select();
            fillHexGreen(target);
        }
    }

    public static void fillHexHalfBlue(Polygon p) {
        p.setOpacity(.5);
        p.setFill(Color.BLUE);
    }

    public static void fillHexTransparent(Polygon p) {
        p.setFill(Color.TRANSPARENT);
    }

    public static void fillHexGreen(Polygon p) {
        p.setFill(Color.GREEN);
    }

    public static void fillHexGreenOrTransparent(Polygon p) {
        Hex h = (Hex) p.getUserData();
        if (h.isSelected()) {
            GameFunction.fillHexGreen(p);
        } else {
            GameFunction.fillHexTransparent(p);
        }
    }
}
