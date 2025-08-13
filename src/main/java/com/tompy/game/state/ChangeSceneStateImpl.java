package com.tompy.game.state;

import com.tompy.game.GameSceneLoader;
import com.tompy.game.play.GamePlaySceneLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneStateImpl extends AbstractGameState {
    protected final Stage stage;
    protected final String scenePropertiesName;
    protected final GameStateType type;

    public ChangeSceneStateImpl(Stage stage, String scenePropertiesName, GameStateType type) {
        this.stage = stage;
        this.scenePropertiesName = scenePropertiesName;
        this.type = type;
    }

    @Override
    public void beginState() {
        GameSceneLoader gpsl = new GameSceneLoader();
        try {
            gpsl.loadScene(stage, scenePropertiesName, new GamePlaySceneLoader());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void process(long l) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(type).build());
    }
}
