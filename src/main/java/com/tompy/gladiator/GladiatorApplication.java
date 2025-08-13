package com.tompy.gladiator;

import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.game.state.GameTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GladiatorApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.SCENE_CHANGE).stage(stage).properties("gladiator-start.properties").build());
        new GameTimer().start();
    }

    public static void main(String[] args) {
        launch();
    }
}