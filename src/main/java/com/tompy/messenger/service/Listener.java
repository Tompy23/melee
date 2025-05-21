package com.tompy.messenger.service;

import com.tompy.messenger.Message;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * Handles {@link Message} to distribute to listeners associated wit the com.tompy.messenger.Message Service
 * @param <T>
 */
public class Listener<T extends Message> {
    private final Function<String, T> converter;
    private final ConcurrentLinkedQueue<String> queue;

    Listener(Function<String, T> converter) {
        this.converter = converter;
        queue = new ConcurrentLinkedQueue<>();
    }

    /**
     * Adds {@link Message} to the listener queue
     * @param message - {@link Message} to add
     */
    public synchronized void add(Message message) {
        queue.offer(message.getJson());
    }

    /**
     * Retrieve an optional to from the listener queue
     *
     * @return {@link Optional} of {@link Message} for the listener
     */
    public synchronized Optional<T> poll() {
        String message = queue.poll();
        return Optional.ofNullable((message != null) ? converter.apply(message) : null);
    }

    /**
     * An optional to from the top of the listener queue
     *
     * @return {@link Optional} of {@link Message} look at the top of the queue
     */
    public synchronized Optional<T> peek() {
        String message = queue.peek();
        return Optional.ofNullable ((message != null) ? converter.apply(message) : null);
    }
}
