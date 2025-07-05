package com.tompy.game;

import javafx.stage.Stage;

import java.util.Properties;

public interface GamePlayController {

    void setProperties(Properties properties);

    void setStage(Stage stage);

    void showGrid();
}
