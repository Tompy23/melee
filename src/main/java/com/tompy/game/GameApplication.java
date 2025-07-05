package com.tompy.game;

import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameTimer;
import com.tompy.game.state.StateFactory;
import com.tompy.game.state.StateType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        GameStateMachine.get().changeState(StateFactory.get().buidler().type(StateType.SCENE_CHANGE).stage(stage).properties("first.properties").build());
        new GameTimer().start();
    }

    public static void main(String[] args) {
        launch();
    }
}