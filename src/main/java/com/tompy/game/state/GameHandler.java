package com.tompy.game.state;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface GameHandler {

    void onClickHex(MouseEvent event);

    void onMouseEnterHex(MouseEvent event);

    void onMouseLeaveHex(MouseEvent event);

    void onMouseEnterCounter(MouseEvent event);

    void onMouseLeaveCounter(MouseEvent event);

    void onMouseClickCounter(MouseEvent event);

    void onMouseDoubleClickCounter(MouseEvent event);
}
