package com.tompy.messenger.service;

import com.tompy.messenger.Message;
import com.tompy.messenger.MessageService;
import com.tompy.messenger.polling.MessageDrainingState;
import com.tompy.messenger.polling.MessagePollingService;
import com.tompy.messenger.polling.MessagePollingState;
import com.tompy.state.AbstractStateMachine;
import com.tompy.state.StateThread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * Implementation of the com.tompy.messenger.Message service as a state machine
 */
public class MessageServiceImpl extends AbstractStateMachine implements MessageService, MessagePollingService {
    private final Thread stateThread;
    private final Map<String, List<Listener<?>>> listeners;
    private final ConcurrentLinkedQueue<Message> broadcaster;
    private final Map<Integer, Listener<?>> listenerMap;
    private Integer currentToken = 0;

    private boolean stop = false;


    /**
     * Default constructor to set up the message service structures
     */
    protected MessageServiceImpl() {
        stateThread = new Thread(new StateThread(this), "messageService");
        listeners = Collections.synchronizedMap(new HashMap<>());
        currentState = new MessagePollingState(this);
        listenerMap = new ConcurrentHashMap<>();
        broadcaster = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void start() {
        if (!stateThread.isAlive()) {
            stateThread.start();
        }
    }

    @Override
    public boolean stopThread() {
        return stop;
    }

    @Override
    public void stop(long wait) {
        changeState(new MessageDrainingState(this, wait));
    }

    @Override
    public void stopService() {
        stop = true;
    }

    @Override
    public synchronized <T extends Message> Integer createListenerQueue(String messageType,
            Function<String, T> converter) {
        Integer newToken;
        synchronized (this) {
            newToken = currentToken++;
        }
        Listener<T> listener = new Listener<>(converter);
        if (!listeners.containsKey(messageType)) {
            listeners.put(messageType, Collections.synchronizedList(new ArrayList<>()));
        }
        listeners.get(messageType).add(listener);
        listenerMap.put(newToken, listener);
        return newToken;
    }

    @Override
    public synchronized void broadcast(Message message) {
        //broadcasterMap.get(token).broadcast(message);
        broadcaster.add(message);
    }

    @Override
    public synchronized <T extends Message> Optional<T> poll(Integer token) {
        return (Optional<T>) listenerMap.get(token).poll();
    }

    @Override
    public synchronized <T extends Message> Optional<T> peek(Integer token) {
        return (Optional<T>) listenerMap.get(token).peek();
    }

    public synchronized ConcurrentLinkedQueue<Message> getBroadcaster() {
        return broadcaster;
    }

    @Override
    public synchronized List<Listener<?>> getListeners(String messageType) {
        if (listeners.get(messageType) == null || listeners.get(messageType).isEmpty()) {
            return null;
        } else {
            return Collections.unmodifiableList(listeners.get(messageType));
        }
    }
}
