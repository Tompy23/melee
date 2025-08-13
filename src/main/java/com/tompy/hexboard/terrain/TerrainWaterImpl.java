package com.tompy.hexboard.terrain;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class TerrainWaterImpl extends AbstractTerrain {
    private Image image;
    private static String FILL_NAME = "water.jpg";

    public TerrainWaterImpl(long entryCost) {
        super(entryCost);
        image = new Image(FILL_NAME);

    }

    @Override
    public long getEntryCost() {
        return 2;
    }

    @Override
    public Paint fill() {
        ImagePattern pattern = new ImagePattern(image);
        return pattern;
    }
}
