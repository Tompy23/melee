package com.tompy.game.state;

import com.tompy.game.GamePlaySceneLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneStateImpl extends AbstractGameState {
    private final Stage stage;
    private final String scenePropertiesName;

    public ChangeSceneStateImpl (Stage stage, String scenePropertiesName) {
        this.stage = stage;
        this.scenePropertiesName = scenePropertiesName;
    }

    @Override
    public void beginState() {
        GamePlaySceneLoader gpsl = new GamePlaySceneLoader();
        try {
            gpsl.loadScene(stage, scenePropertiesName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void process(long l) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.COMMON).build());
    }
}
