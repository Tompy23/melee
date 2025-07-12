package com.tompy.game.state;

import com.tompy.state.State;
import javafx.scene.input.MouseEvent;

public interface GameHandler extends State {

    void onClickHex(MouseEvent event);

    void onMouseEnterHex(MouseEvent event);

    void onMouseLeaveHex(MouseEvent event);
}
