package com.tompy.hexboard.terrain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class AbstractTerrain implements Terrain {
    protected long entryCost;

    protected AbstractTerrain(long entiryCost) {
        this.entryCost = entiryCost;
    }

    @Override
    public Paint fill() {
        return Color.TRANSPARENT;
    }

    @Override
    public long getEntryCost() {
        return entryCost;
    }
}
