package com.tompy.messenger.polling;

import com.tompy.messenger.Message;
import com.tompy.messenger.service.Listener;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Methods of attaining the various queues needed for handling the passing of messages
 */
public interface MessagePollingService {
    /**
     * Get the listener queues for a message type
     * @param messageType - A representation of a type of message
     * @return - The {@link Listener} list for a message type
     */
    List<Listener<?>> getListeners(String messageType);

    /**
     * Command to stop the service immediately
     */
    void stopService();

    /**
     * Retreieve the service's collection queue
     * @return - A {@link ConcurrentLinkedQueue} of {@link Message}
     */
    ConcurrentLinkedQueue<Message> getBroadcaster();
}
