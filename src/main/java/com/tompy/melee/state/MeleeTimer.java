package com.tompy.melee.state;

import javafx.animation.AnimationTimer;

public class MeleeTimer extends AnimationTimer {
    private MeleeStateMachine stateMachine;

    public MeleeTimer(MeleeStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void handle(long l) {
        stateMachine.process(l);
    }
}
