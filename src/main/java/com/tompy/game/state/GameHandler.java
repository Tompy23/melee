package com.tompy.game.state;

import javafx.scene.input.MouseEvent;

public interface GameHandler {

    void onClickHex(MouseEvent event);

    void onMouseEnterHex(MouseEvent event);

    void onMouseLeaveHex(MouseEvent event);
}
