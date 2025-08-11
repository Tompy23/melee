package com.tompy.hexboard.terrain;

import javafx.scene.paint.Paint;

public interface Terrain {
    Paint fill();

    long getEntryCost();
}
