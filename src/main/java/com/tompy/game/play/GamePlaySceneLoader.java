package com.tompy.game.play;

import com.tompy.game.AbstractSceneLoader;
import com.tompy.game.GameController;
import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public class GamePlaySceneLoader extends AbstractSceneLoader {

    public GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties, LayoutDescription layoutDescription) {
        GamePlayController gpc = fxmlLoader.getController();
        gpc.setStage(stage);
        GamePlayData.builder().controller(gpc).init();

        return gpc;
    }
}
