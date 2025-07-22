package com.tompy.hexboard;

import com.tompy.game.GameData;

import java.util.ArrayList;
import java.util.List;

public class HexFunction {

    public static int[] directionVector = new int[]{2, 0, 1, -1, -1, -1, -2, 0, -1, 1, 1, 1};

    public static List<Hex> getNeighbors(Hex hex) {
        List<Hex> returnValue = new ArrayList<>();
        for (int i = 0; i < 12; i+=2) {
            int x = hex.getCoordinate().getCol() + directionVector[i];
            int y = hex.getCoordinate().getR() + directionVector[i+1];
            Hex neighbor = GameData.get().getHexBoard().getHex(x, y);
            if (neighbor != null) {
                returnValue.add(neighbor);
            }
        }

        return returnValue;
    }
}
