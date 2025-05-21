package com.tompy.melee;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class GamePlayController {
    @FXML
    private ScrollPane background;
    @FXML
    private AnchorPane backanchor;

    public void showGrid() {
        background.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        background.setOpacity(1.0);

        backanchor.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    }
}
