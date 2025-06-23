package com.tompy.messenger.polling;

import com.tompy.state.State;

/**
 * The abstract class all com.tompy.messenger.Message Service States must extend
 */
public abstract class MessageAbstractState implements State {
    protected final MessagePollingService service;

    /**
     * Constructor to retain the com.tompy.messenger.Message Service
     * @param service
     */
    protected MessageAbstractState(MessagePollingService service) {
        this.service = service;
    }
}
