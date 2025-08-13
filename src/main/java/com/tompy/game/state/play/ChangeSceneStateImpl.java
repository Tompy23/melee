package com.tompy.game.state.play;

import com.tompy.game.GameSceneLoader;
import com.tompy.game.play.GamePlaySceneLoader;
import com.tompy.game.state.AbstractGameState;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneStateImpl extends AbstractGameState {
    protected final Stage stage;
    protected final String scenePropertiesName;

    public ChangeSceneStateImpl(Stage stage, String scenePropertiesName) {
        this.stage = stage;
        this.scenePropertiesName = scenePropertiesName;
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
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.COMMON).build());
    }
}
