package com.tompy.game.play;

import com.tompy.game.AbstractSceneLoader;
import com.tompy.game.GameController;
import com.tompy.game.SceneLoader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.Properties;

public class GamePlaySceneLoader extends AbstractSceneLoader implements SceneLoader {

    public GameController loadSceneController(FXMLLoader fxmlLoader, Stage stage, Properties sceneProperties) {
        GamePlayController gpc = fxmlLoader.getController();
        gpc.setStage(stage);
        GamePlayData.builder().controller(gpc).init();

        handleHexBoard(gpc, stage, sceneProperties);

        return gpc;
    }
}
