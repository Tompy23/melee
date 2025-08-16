package com.tompy.game.play;

import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.game.state.GameTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePlayApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        GameStateMachine.get().changeState(
                GameStateFactory.buidler().type(GameStateType.SCENE_CHANGE).stage(stage).properties("first.properties")
                        .sceneLoader(new GamePlaySceneLoader()).toType(GameStateType.BEGIN_GAME_PLAY).build());
        new GameTimer().start();
    }
}