package com.tompy.game;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface GamePlayController {

    void setStage(Stage stage);

    void drawHexBoard();

    Pane getHexBoardPane();

    Pane getTextPane();

    void enableMove1Button(boolean enable);
}
