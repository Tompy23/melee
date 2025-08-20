package com.tompy.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class GameFxmlLoader {

    public FXMLLoader loadFxml(Stage stage, Properties sceneProperties, SceneLoader sceneLoader,
                               URL resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();

        sceneLoader.loadSceneController(fxmlLoader, stage, sceneProperties);

        return fxmlLoader;
    }
}
