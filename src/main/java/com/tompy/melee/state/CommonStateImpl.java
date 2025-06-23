package com.tompy.melee.state;

import com.tompy.state.State;

public class CommonStateImpl implements State {
    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {
        System.out.println(l);
    }
}
