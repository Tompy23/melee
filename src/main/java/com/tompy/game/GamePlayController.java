package com.tompy.game;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public interface GamePlayController {

    void setGameData(GameData gameData);

    void setStage(Stage stage);

    void drawHexBoard();

    void drawHexBoardWithLayout();

    Pane getHexBoardPane();

    Pane getTextPane();

    void enableMove1Button(boolean enable);
}
