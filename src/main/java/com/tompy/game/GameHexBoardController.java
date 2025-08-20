package com.tompy.game;

import javafx.scene.layout.Pane;

public interface GameHexBoardController extends GameController {

    void drawHexBoard();

    void drawHexBoardWithLayout();

    Pane getHexBoardPane();

    Pane getTextPane();
}
