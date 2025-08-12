package com.tompy.counter.state;

import javafx.scene.input.MouseEvent;

public interface CounterHandler {
    void handleClick(MouseEvent event);

    void handleEnter(MouseEvent event);

    void handleExit(MouseEvent event);
}
