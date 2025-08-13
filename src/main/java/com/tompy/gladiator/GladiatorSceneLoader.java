package com.tompy.gladiator;

import com.tompy.game.AbstractSceneLoader;
import com.tompy.game.GameController;
import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public class GladiatorSceneLoader extends AbstractSceneLoader {
    @Override
    public GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties,
            LayoutDescription layoutDescription) {
        return null;

    }
}
