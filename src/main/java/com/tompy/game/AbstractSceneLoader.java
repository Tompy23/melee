package com.tompy.game;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public abstract class AbstractSceneLoader implements SceneLoader {
    @Override
    public GameController loadSceneController( FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties) {
        return loadSceneController(fxmlLoader, stage, sceneProperties, null);
    }
}
