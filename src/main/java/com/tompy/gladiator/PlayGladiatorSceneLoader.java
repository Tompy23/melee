package com.tompy.gladiator;

import com.tompy.game.AbstractSceneLoader;
import com.tompy.game.GameController;
import com.tompy.game.SceneLoader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public class PlayGladiatorSceneLoader extends AbstractSceneLoader implements SceneLoader {
    @Override
    public GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties) {
        PlayGladiatorController controller = fxmlLoader.getController();
        controller.setStage(stage);

        handleHexBoard(controller, stage, sceneProperties);

        return controller;
    }
}
