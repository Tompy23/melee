package com.tompy.messenger.polling;

import com.tompy.messenger.Message;
import com.tompy.messenger.service.Listener;

import java.util.Iterator;

/**
 * A state used by the message service to process the passing of messages from the broadcaster to the
 * appropriate listener queues
 */
public class MessagePollingState extends MessageAbstractState {
    public MessagePollingState(MessagePollingService service) {
        super(service);
    }

    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {
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
