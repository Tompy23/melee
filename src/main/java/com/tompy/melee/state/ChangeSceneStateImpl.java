package com.tompy.melee.state;

import com.tompy.melee.GamePlaySceneLoader;
import com.tompy.state.State;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeSceneStateImpl implements State {
    private final Stage stage;
    private final String scenePropertiesName;
    private final MeleeStateMachine stateMachine;

    public ChangeSceneStateImpl(MeleeStateMachine stateMachine, Stage stage, String scenePropertiesName) {
        this.stateMachine = stateMachine;
        this.stage = stage;
        this.scenePropertiesName = scenePropertiesName;
    }

    @Override
    public void beginState() {
        GamePlaySceneLoader gpsl = new GamePlaySceneLoader();
        try {
            gpsl.loadScene(stage, stateMachine, scenePropertiesName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {
        stateMachine.changeState(new CommonStateImpl());
    }
}
