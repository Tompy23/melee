package com.tompy.state;

/**
 * A class that can process a state machine in a separate thread
 */
public class StateThread implements Runnable {
    private final StateMachine machine;

    /**
     * Constructor that wraps the state machine to wrap in a thread
     * @param machine - The {@link StateMachine} to wrap in the thread
     */
    public StateThread(StateMachine machine) {
        this.machine = machine;
    }

    /**
     * Extended from {@link Runnable}
     */
    public void run() {
        while(!machine.stopThread()) {
            machine.process(System.currentTimeMillis());
        }
    }
}
