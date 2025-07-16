package com.tompy.state;

/**
 * The API for a state machine
 */
public interface StateMachine<T extends State> {
    /**
     * Change the state of the machine
     * @param newState - The next {@link State}
     */
    void changeState(T newState);

    /**
     * Called from the thread
     * @param l - The time since last call
     */
    void process(long l);

    /**
     * Indicate that the processing loop should end
     * @return True the thread will stop, false it will continue
     */
    boolean stopThread();
}
