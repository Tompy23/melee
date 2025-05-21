package com.tompy.messenger;

import java.util.Optional;
import java.util.function.Function;

/**
 * The service interface exposed to the users of the message service
 */
public interface MessageService {
    /**
     * Start up the service
     */
    void start();

    /**
     * Put the message service in a draining state for a number of milliseconds
     * @param wait - Number of milliseconds to wait while draining the quuees
     */
    void stop(long wait);

    /**
     * Create and register a listener queue for the com.tompy.messenger.Message Server client
     * @param messageType - The type of message
     * @param converter - A converter to create an object from JSON
     * @return - A token used by the client to indicate which queue to use
     * @param <T>
     */
    <T extends Message> Integer createListenerQueue(String messageType, Function<String, T> converter);

    /**
     * Send a message to the com.tompy.messenger.Message Service
     * @param message - The message to send
     */
    void broadcast(Message message);

    /**
     * Return the top message from the queue referenced by the token
     * @param token - A token referencing a specific listener queue
     * @return - The top message
     * @param <T>
     */
    <T extends Message> Optional<T> poll(Integer token);

    /**
     * Return a copy of the top message from the queue referenced by the token
     * @param token - A token referencing a specific listener queue
     * @return - The top message
     * @param <T>
     */
    <T extends Message> Optional<T> peek(Integer token);
}
