package com.tompy.messenger.test;

import com.tompy.messenger.MessageService;
import com.tompy.messenger.service.MessageInstance;
import com.tompy.state.State;

import java.util.Optional;

public class ListeningState implements State {
    private final MessageService service;
    private final Integer listenerTokenA;
    private final Integer listenerTokenB;

    public ListeningState() {
        service = MessageInstance.get();
        listenerTokenA = service.createListenerQueue("mA", MessageA::fromJson);
        listenerTokenB = service.createListenerQueue("mB", MessageB::fromJson);
    }

    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process() {
        Optional<MessageA> mA;
        do {
            mA = service.poll(listenerTokenA);
            if (mA.isPresent()) {
                System.out.println(mA.get());
                MessageB mB = MessageB.builder().id(mA.get().getId()).age(88).build();
                service.broadcast(mB);
            }
        } while (mA.isPresent());

        Optional<MessageB> mB;
        do {
            mB = service.poll(listenerTokenB);
            mB.ifPresent(System.out::println);
        } while (mB.isPresent());
    }
}
