package com.tompy.game.play;

import com.tompy.game.GameController;
import javafx.scene.layout.Pane;

public interface GamePlayController extends GameController {

    void enableMove1Button(boolean enable);

    void drawHexBoard();

    void drawHexBoardWithLayout();

    Pane getHexBoardPane();

    Pane getTextPane();
}
