package com.tompy.game;

import javafx.stage.Stage;

public class AbstractGameController implements GameController {
    protected Stage stage;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
