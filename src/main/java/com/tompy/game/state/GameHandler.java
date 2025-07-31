package com.tompy.game.state;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public interface GameHandler {

//    void onMouseEnterCounter(MouseEvent event);
//
//    void onMouseLeaveCounter(MouseEvent event);
//
//    void onClickCounter(MouseEvent event);

    void onClickMove1(ActionEvent event);

    void onClickTestPointer(ActionEvent event);

    void onMouseMove(MouseEvent event);
}
