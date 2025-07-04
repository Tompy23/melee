package com.tompy.melee;

import com.tompy.melee.state.ChangeSceneStateImpl;
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
        stateMachine = new MeleeStateMachine();
        timer = new MeleeTimer(stateMachine);
        stateMachine.changeState(new ChangeSceneStateImpl(stateMachine, stage, "first.properties"));
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}