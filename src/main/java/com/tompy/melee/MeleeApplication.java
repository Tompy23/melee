package com.tompy.melee;

import com.tompy.melee.state.CommonStateImpl;
import com.tompy.melee.state.MeleeStateMachine;
import com.tompy.melee.state.MeleeTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MeleeApplication extends Application {
    private MeleeTimer timer;
    private MeleeStateMachine stateMachine;

    @Override
    public void start(Stage stage) throws IOException {
        GamePlaySceneLoader gpsl = new GamePlaySceneLoader();

        gpsl.begin(stage);


        stateMachine = new MeleeStateMachine();
        timer = new MeleeTimer(stateMachine);
        stateMachine.changeState(new CommonStateImpl());
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}