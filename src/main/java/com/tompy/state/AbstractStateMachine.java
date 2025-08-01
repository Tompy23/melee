package com.tompy.state;

import com.tompy.hexboard.state.HexState;

/**
 * An abstract to be extended by all state machines that handles all the necessary work
 */
public abstract class AbstractStateMachine<T extends State> implements StateMachine<T> {
    protected T currentState;

    @Override
    public void changeState(T newState) {
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
    public void continueState(T newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
    }
}
