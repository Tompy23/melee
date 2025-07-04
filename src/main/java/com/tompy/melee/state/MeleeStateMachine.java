package com.tompy.melee.state;

import com.tompy.state.AbstractStateMachine;
import com.tompy.state.State;
import com.tompy.state.StateMachine;

public class MeleeStateMachine extends AbstractStateMachine {
    @Override
    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
    }

    @Override
    public boolean stopThread() {
        return false;
    }
}
