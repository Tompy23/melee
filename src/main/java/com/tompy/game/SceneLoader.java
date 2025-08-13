package com.tompy.game;

import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public interface SceneLoader {
    GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties);
    GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties, LayoutDescription layoutDescription);
}
