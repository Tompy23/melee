package com.tompy.state;

/**
 * API for a state
 */
public interface State {
    /**
     * Called as the state is introduced
     */
    void beginState();

    /**
     * Called after state is changed away from this state
     */
    void endState();

    /**
     * Called in a loop while this is the current state
     * @param l - time since last call
     */
    void process(long l);
}
