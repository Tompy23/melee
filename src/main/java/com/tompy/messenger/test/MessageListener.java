package com.tompy.messenger.test;

import com.tompy.messenger.service.Listener;
import com.tompy.messenger.state.AbstractStateMachine;
import com.tompy.messenger.state.StateThread;

public class MessageListener extends AbstractStateMachine {
    private final Thread thread;
    private Listener<MessageA> listenerA;
    private Listener<MessageB> listenerB;
    private boolean stop = false;

    public MessageListener() {
        thread = new Thread(new StateThread(this), "listenerThread)");
        currentState = new ListeningState();
    }

    @Override
    public boolean stopThread() {
        return stop;
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        stop = true;
    }
}
