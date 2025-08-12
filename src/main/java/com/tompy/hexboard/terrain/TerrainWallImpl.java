package com.tompy.hexboard.terrain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TerrainWallImpl extends AbstractTerrain {

    public TerrainWallImpl(long entryCost) {
        super(entryCost);
    }

    @Override
    public Paint fill() {
        return Color.BLACK;
    }

    @Override
    public boolean allowLos() {
        return false;
    }

    @Override
    public boolean noEntry() {
        return true;
    }
}
