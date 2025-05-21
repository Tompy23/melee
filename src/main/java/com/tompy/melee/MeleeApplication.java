package com.tompy.melee;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MeleeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePlaySceneLoader gpsl = new GamePlaySceneLoader();

        gpsl.begin(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}