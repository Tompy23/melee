package com.tompy.melee;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class GamePlayController {
    private static final double SQRT3 = Math.sqrt(3);
    @FXML
    private ScrollPane background;
    @FXML
    private StackPane boardStack;
    @FXML
    private Pane hexBoard;

    private HexBoard board;

    public void showGrid() {
        // background.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        //background.setOpacity(1.0);

        // backanchor.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        board = HexBoard.builder().pixelSize(30).height(24).width(24).build();

        drawHexBoard();


    }

    public void drawHexBoard() {
        double height = 2 * board.getPixelSize();
        double width = SQRT3 * board.getPixelSize();

        double panelWidth = board.getWidth() * width + 200;
        double panelHeight = board.getHeight() * height;

        background.setPrefViewportWidth(panelWidth);
        background.setPrefViewportHeight(panelHeight);

        boardStack.setPrefWidth(panelWidth);
        boardStack.setPrefHeight(panelHeight);

        hexBoard.setPrefWidth(panelWidth);
        hexBoard.setPrefHeight(panelHeight);

        double[] coordinates = new double[12];

        int angle = 30;
        for (int j = 0; j < 12; j += 2) {
            double radians = angle * Math.PI / 180;
            coordinates[j] = board.getPixelSize() * Math.cos(radians);
            coordinates[j + 1] = board.getPixelSize() * Math.sin(radians);
            angle += 60;
        }

        for (Hex hex : board.getHexes()) {
            double x = 100 + hex.getCol() / 2.0 * width;
            double y = 100 + (hex.getRow() * 1.5 * board.getPixelSize());
            if (hex.getRow() % 2 == 0) {
                x += 1.0 / 2.0 * width;
            }
            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            hexBoard.getChildren().add(c);

            double[] finalCoordinates = new double[12];
            for (int i = 0; i < 12; i += 2) {
                finalCoordinates[i] = coordinates[i] + x;
                finalCoordinates[i + 1] = coordinates[i + 1] + y;
            }

            Polygon hexShape = new Polygon(finalCoordinates);
            hexShape.setFill(Color.TRANSPARENT);
            hexShape.setStroke(Color.BLACK);
            hexShape.setStrokeWidth(1.0);
            hexBoard.getChildren().add(hexShape);

        }
    }
}
