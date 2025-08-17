package com.tompy.game;

import com.tompy.game.play.GamePlayApplication;
import com.tompy.gladiator.GladiatorApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class GameFxmlLoader {

    public FXMLLoader loadFxml(Stage stage, Properties sceneProperties, SceneLoader sceneLoader) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                GladiatorApplication.class.getResource(sceneProperties.getProperty(GameConstants.FXML)));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();

        sceneLoader.loadSceneController(fxmlLoader, stage, sceneProperties);

        return fxmlLoader;
    }
}
