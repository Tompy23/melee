package com.tompy.melee;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.HexCoordinate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.HashMap;
import java.util.Map;

public class GamePlayController {
    private static final double SQRT3 = Math.sqrt(3);
    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollBackground;
    @FXML
    private StackPane stackBoard;
    @FXML
    private Pane paneBackImage;
    @FXML
    private Pane paneHexDecorator;
    @FXML
    private Pane paneHexBoard;
    @FXML
    private Button zoomIn;
    @FXML
    private Button zoomOut;

    private HexBoard board;

    private double zoom = 1.0;

    private Map<HexCoordinate, Polygon> boardPolygons;

    private HexCoordinate currentHex;

    public void showGrid() {
        board = HexBoard.builder().pixelSize(30).height(32).width(32).border(75).build();

        drawHexBoard();
    }

    public void drawHexBoard() {
        boardPolygons = new HashMap<>();

        double height = 1.5 * board.getPixelSize();
        double width = SQRT3 * board.getPixelSize();

        double panelWidth = board.getWidth() * width + 2 * board.getBorder();
        double panelHeight = board.getHeight() * height + 2 * board.getBorder();

        scrollBackground.setPrefViewportWidth(panelWidth);
        scrollBackground.setPrefViewportHeight(panelHeight);

        stackBoard.setPrefWidth(panelWidth);
        stackBoard.setPrefHeight(panelHeight);

        paneHexBoard.setPrefWidth(panelWidth);
        paneHexBoard.setPrefHeight(panelHeight);

        paneHexDecorator.setPrefWidth(panelWidth);
        paneHexDecorator.setPrefHeight(panelHeight);

        paneBackImage.setPrefWidth(panelWidth);
        paneBackImage.setPrefHeight(panelHeight);

        for (Hex hex : board.getHexes()) {
            double hexWidth = SQRT3 * board.getPixelSize();

            int j = hex.getCol();
            int i = hex.getRow();

            double x = board.getBorder() + j / 2.0 * hexWidth;
            double y = board.getBorder() + (i * 1.5 * board.getPixelSize());
            if (i % 2.0 == 0) {
                x += 1.0 / 2.0 * hexWidth;
            }

            paneHexBoard.getChildren().add(hex.getPolygon());
            boardPolygons.put(hex.getCoordinate(), hex.getPolygon());
            hex.getPolygon().setOnMouseEntered(this::turnBlue);
            hex.getPolygon().setOnMouseExited(this::turnWhite);

            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexDecorator.getChildren().add(c);
        }

        paneBackImage.setBackground(Background.fill(Color.ORANGE));
    }

    public void turnBlue(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        p.setOpacity(.5);
        p.setFill(Color.BLUE);
    }

    public void turnWhite(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        p.setFill(Color.TRANSPARENT);
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
