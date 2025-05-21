package com.tompy.messenger.polling;

import com.tompy.messenger.Message;
import com.tompy.messenger.service.Listener;
import com.tompy.messenger.service.MessageServiceImpl;

import java.util.Iterator;

/**
 * A state which will wait for some duration and then stop the com.tompy.messenger.Message Service thread
 */
public class MessageDrainingState extends MessageAbstractState {
    private long startDraining;
    private final long drainLength;
    private static final long defaultLength = 5000;

    /**
     * Uses the default wait while draining the queues
     * @param service - The com.tompy.messenger.Message Service reference to retrieve queues needed for processing
     */
    public MessageDrainingState(MessageServiceImpl service) {
        super(service);
        drainLength = defaultLength;
    }

    /**
     * Sets the duration to wait while draining the queues
     * @param service - The com.tompy.messenger.Message Service reference to retrieve queues needed for processing
     * @param length - Milliseconds to wait while draining queues.
     */
    public MessageDrainingState(MessageServiceImpl service, long length) {
        super(service);
        drainLength = length;
    }

    @Override
    public void beginState() {
        startDraining = System.currentTimeMillis();
    }

    @Override
    public void endState() {

    }

    @Override
    public void process() {
        if (startDraining + drainLength > defaultLength) {
            service.stopService();
        }
        Iterator<Message> broadcasterIterator = service.getBroadcaster().iterator();
        while (broadcasterIterator.hasNext()) {
            Message message = broadcasterIterator.next();
            if (message != null) {
                Iterator<Listener<?>> listenerIterator = service.getListeners(message.getType()).iterator();
                while (listenerIterator.hasNext()) {
                    listenerIterator.next().add(message);
                }
            }
        }
    }
}
