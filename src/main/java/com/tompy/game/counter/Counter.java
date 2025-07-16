package com.tompy.game.counter;

import com.tompy.hexboard.Hex;
import javafx.scene.image.Image;

public interface Counter {
    long getId();

    void addToHex(Hex hex);

    Hex getHex();

    Image getImage();

    boolean isSelected();

    void select();

    void select(boolean selected);

    void unselect();
}
