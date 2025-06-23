package com.tompy.melee.state;

import com.tompy.state.State;
import com.tompy.state.StateMachine;
import javafx.animation.AnimationTimer;

public abstract class AbstractMeleeStateMachine extends AnimationTimer implements StateMachine {
    protected State currentState;

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void changeState(State newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        currentState.beginState();
    }

    @Override
    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
    }

    @Override
    public void handle(long l) {
        process(l);
    }

    @Override
    public boolean stopThread() {
        return false;
    }
}
