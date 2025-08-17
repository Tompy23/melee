package com.tompy.hexboard;

import com.tompy.game.GameHexBoardData;

import java.util.ArrayList;
import java.util.List;

public class HexFunction {
    private final static double SQRT3 = Math.sqrt(3);

    private final static int[] directionVector = new int[]{2, 0, 1, -1, -1, -1, -2, 0, -1, 1, 1, 1};

    public static List<Hex> getNeighbors(Hex hex) {
        List<Hex> returnValue = new ArrayList<>();
        for (int i = 0; i < 12; i += 2) {
            long x = hex.getCoordinate().getCol() + directionVector[i];
            long y = hex.getCoordinate().getR() + directionVector[i + 1];
            Hex neighbor = GameHexBoardData.get().getHexBoard().getHex(x, y);
            if (neighbor != null && !neighbor.noEntry()) {
                returnValue.add(neighbor);
            }
        }

        return returnValue;
    }

    public static long distance2(Hex from, Hex to) {
        long dcol = Math.abs(from.getCol() - to.getCol());
        long drow = Math.abs(from.getRow() - to.getRow());

        return drow + Math.max(0, (dcol - drow) / 2);
    }

    public static long distance(Hex from, Hex to) {
        CubeCoordinate cube = cubeSubtract(from, to);
        return Math.max(Math.abs(cube.getQ()), Math.max(Math.abs(cube.getR()), Math.abs(cube.getS())));
    }

    private static CubeCoordinate cubeSubtract(Hex a, Hex b) {
        return new CubeCoordinate(a.getQ() - b.getQ(), a.getR() - b.getR(), a.getS() - b.getS());
    }

    private static long lerp(long a, long b, long t) {
        return a + (b - a) * t;
    }

    private static CubeCoordinate cubeLerp(CubeCoordinate a, CubeCoordinate b, int t) {
        return new CubeCoordinate(lerp(a.getQ(), b.getQ(), t), lerp(a.getR(), b.getR(), t),
                lerp(a.getS(), b.getS(), t));
    }

    public static Hex pixelToHex(double x, double y) {
        x -= GameHexBoardData.get().getHexBoard().getBorder()*1.5;
        y -= GameHexBoardData.get().getHexBoard().getBorder();
        x /= GameHexBoardData.get().getHexBoard().getPixelSize();
        y /= GameHexBoardData.get().getHexBoard().getPixelSize();

        double q = (SQRT3 / 3 * x) - (1.0 / 3) * y;
        double r = (2.0 / 3) * y;
        DoubleCubeCoordinate cubedCoordinates = new DoubleCubeCoordinate(q, r, -q - r);
        CubeCoordinate roundCoordinate = cubeRound(cubedCoordinates);
        HexCoordinate hexCoordinate = fromAxial(roundCoordinate);

        return GameHexBoardData.get().getHexBoard().getHex(hexCoordinate.getCol(), hexCoordinate.getR());
    }

    public static HexCoordinate fromAxial(CubeCoordinate coordinate) {
        return HexCoordinate.builder().setCol((2 * coordinate.getQ()) + coordinate.getR()).setRow(coordinate.getR())
                .build();
    }

    private static CubeCoordinate cubeRound(DoubleCubeCoordinate doubleCubeCoordinate) {

        Long q = Math.round(doubleCubeCoordinate.getQ());
        Long r = Math.round(doubleCubeCoordinate.getR());
        Long s = Math.round(doubleCubeCoordinate.getS());

        double qDiff = Math.abs(q - doubleCubeCoordinate.getQ());
        double rDiff = Math.abs(r - doubleCubeCoordinate.getR());
        double sDiff = Math.abs(s - doubleCubeCoordinate.getS());

        if (qDiff > rDiff && qDiff > sDiff) {
            q = -r - s;
        } else {
            if (rDiff > sDiff) {
                r = -q - s;
            } else {
                s = -q - r;
            }
        }

        return new CubeCoordinate(q, r, s);
    }

    public static CubeCoordinate toAxial(HexCoordinate coordinate) {
        return new CubeCoordinate((coordinate.getCol() - coordinate.getRow()) / 2, coordinate.getRow());
    }
}
