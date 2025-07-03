package com.tompy.melee;

import com.tompy.hexboard.CubeCoordinate;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.HexCoordinate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
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

    private Map<HexCoordinate, Polygon> boardPolygons;

    private HexCoordinate currentHex;

    public void showGrid() {
        board = HexBoard.builder().pixelSize(30).height(32).width(32).build();

        drawHexBoard();
    }

    public void drawHexBoard() {
        boardPolygons = new HashMap<>();

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
            boardPolygons.put(hex.getCoordinate(), hexShape);
            hexShape.setOnMouseEntered(this::turnBlue);
            hexShape.setOnMouseExited(this::turnWhite);

            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexBoard.getChildren().add(c);
        }
    }

    public void turnBlue(MouseEvent event) {
        Polygon p = (Polygon)event.getTarget();
        p.setFill(Color.BLUE);
    }
    public void turnWhite(MouseEvent event) {
        Polygon p = (Polygon)event.getTarget();
        p.setFill(Color.WHITE);
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

    public void onMouseMoved(MouseEvent event) {
        HexCoordinate coordinate = getHexFromPointer(event.getX(), event.getY());
        if (currentHex == null) {
            currentHex = coordinate;
        }
        if (!currentHex.equals(coordinate)) {
            Polygon p = boardPolygons.get(currentHex);
            if (p != null) {
                p.setFill(Color.WHITE);
            } else {
                System.out.println("old -> " + currentHex.toString());
            }
            currentHex = coordinate;
            p = boardPolygons.get(currentHex);
            if (p != null) {
                p.setFill(Color.BLUE);
            } else {
                System.out.println("new -> " + currentHex.toString());
            }
        }

    }

    private HexCoordinate cubeRound(CubeCoordinate cube) {
        long q = Math.round(cube.getQ());
        long r = Math.round(cube.getR());
        long s = Math.round(cube.getS());

        double qDiff = Math.abs(cube.getQ() - q);
        double rDiff = Math.abs(cube.getR() - r);
        double sDiff = Math.abs(cube.getS() - s);

        if (qDiff > rDiff && qDiff > sDiff) {
            q = -r - s;
        } else {
            if (rDiff > sDiff) {
                r = -q - s;
            } else {
                s = -q - r;
            }
        }

        int col = (int) (2 * q + r);
        int row = (int) (r);

        return HexCoordinate.builder().setCol(col).setRow(row).build();
    }

    public HexCoordinate getHexFromPointer(double x, double y) {
        double newX = (x) / board.getPixelSize();
        double newY = (y) / board.getPixelSize();

        double q = (SQRT3 / 3 * newX) - (1.0 / 3.0 * newY);
        double r = 2.0 / 3.0 * newY;
        return cubeRound(new CubeCoordinate(q, r, -q - r));
    }
}
