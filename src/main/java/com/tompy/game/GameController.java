package com.tompy.game;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface GameController {

    void setStage(Stage stage);

    void drawHexBoard();

    void drawHexBoardWithLayout();

    Pane getHexBoardPane();

    Pane getTextPane();
}
