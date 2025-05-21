package com.tompy.messenger.state;

/**
 * An abstract to be extended by all state machines that handles all the necessary work
 */
public abstract class AbstractStateMachine implements StateMachine {
    protected State currentState;

    @Override
    public void changeState(State newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        currentState.beginState();
    }

    @Override
    public void process() {
        currentState.process();
    }
}
