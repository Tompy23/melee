package com.tompy.melee;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class GamePlayController {
    private static final double SQRT3 = Math.sqrt(3);
    private static final int BORDER = 100;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollBackground;
    @FXML
    private StackPane stackBoard;
    @FXML
    private Pane paneHexBoard;
    @FXML
    private Button zoomIn;
    @FXML
    private Button zoomOut;

    private HexBoard board;

    private double zoom = 1.0;

    public void showGrid() {
        // scrollPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        //scrollPane.setOpacity(1.0);

        // backanchor.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        board = HexBoard.builder().pixelSize(30).height(32).width(32).build();

        drawHexBoard();


    }

    public void drawHexBoard() {
        double height = 1.5 * board.getPixelSize();
        double width = SQRT3 * board.getPixelSize();

        double panelWidth = board.getWidth() * width + 2 * BORDER;
        double panelHeight = board.getHeight() * height + 2 * BORDER;

        scrollBackground.setPrefViewportWidth(panelWidth);
        scrollBackground.setPrefViewportHeight(panelHeight);

        stackBoard.setPrefWidth(panelWidth);
        stackBoard.setPrefHeight(panelHeight);

        paneHexBoard.setPrefWidth(panelWidth);
        paneHexBoard.setPrefHeight(panelHeight);

        double[] coordinates = new double[12];

        int angle = 30;
        for (int j = 0; j < 12; j += 2) {
            double radians = angle * Math.PI / 180;
            coordinates[j] = board.getPixelSize() * Math.cos(radians);
            coordinates[j + 1] = board.getPixelSize() * Math.sin(radians);
            angle += 60;
        }

        for (Hex hex : board.getHexes()) {
            double x = BORDER + hex.getCol() / 2.0 * width;
            double y = BORDER + (hex.getRow() * 1.5 * board.getPixelSize());
            if (hex.getRow() % 2 == 0) {
                x += 1.0 / 2.0 * width;
            }

            double[] finalCoordinates = new double[12];
            for (int i = 0; i < 12; i += 2) {
                finalCoordinates[i] = coordinates[i] + x;
                finalCoordinates[i + 1] = coordinates[i + 1] + y;
            }

            Polygon hexShape = new Polygon(finalCoordinates);
            hexShape.setFill(Color.WHITE);
            hexShape.setStroke(Color.BLACK);
            hexShape.setStrokeWidth(1.0);
            paneHexBoard.getChildren().add(hexShape);

            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexBoard.getChildren().add(c);
        }
    }

    public void handleZoomIn(ActionEvent event) {
        System.out.println("handleZoomIn");
        System.out.println(event.toString());

        zoom += .1;
        if (zoom > 2.0) {
            zoom = 2.0;
        }
        stackBoard.setScaleX(zoom);
        stackBoard.setScaleY(zoom);
    }

    public void handleZoomOut(ActionEvent event) {
        System.out.println("handleZoomOut");
        System.out.println(event.toString());

        zoom -= .1;
        if (zoom < 0.3) {
            zoom = 0.3;
        }
        stackBoard.setScaleX(zoom);
        stackBoard.setScaleY(zoom);
    }
}
