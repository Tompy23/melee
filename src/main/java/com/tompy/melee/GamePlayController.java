package com.tompy.melee;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.HexCoordinate;
import com.tompy.melee.state.ChangeSceneStateImpl;
import com.tompy.melee.state.MeleeStateMachine;
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
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    private Properties properties;

    private MeleeStateMachine stateMachine;

    private Stage stage;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setStateMachine(MeleeStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showGrid() {
        int pixelSize = Integer.parseInt(properties.getProperty("board.pixel.size"));
        int height = Integer.parseInt(properties.getProperty("board.height"));
        int width = Integer.parseInt(properties.getProperty("board.width"));
        int border = Integer.parseInt(properties.getProperty("board.border"));
        board = HexBoard.builder().pixelSize(pixelSize).height(height).width(width).border(border).build();

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
            hex.getPolygon().setOnMouseClicked(this::handleClick);

            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexDecorator.getChildren().add(c);
        }

        paneBackImage.setBackground(Background.fill(Color.ORANGE));
    }

    public void handleClick(MouseEvent event) {
        System.out.println("Mouse Down");
        Polygon target = (Polygon) event.getTarget();
        Hex hex = (Hex) target.getUserData();
        if (hex.isSelected()) {
            hex.unselect();
        } else {
            hex.select();
            target.setOpacity(.5);
            target.setFill(Color.GREEN);
        }
    }

    public void turnBlue(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        Hex h = (Hex) p.getUserData();
        //if (!h.isSelected()) {
        p.setOpacity(.5);
        p.setFill(Color.BLUE);
        //}
    }

    public void turnWhite(MouseEvent event) {
        Polygon p = (Polygon) event.getTarget();
        Hex h = (Hex) p.getUserData();
        if (!h.isSelected()) {
            p.setFill(Color.TRANSPARENT);
        } else {
            p.setOpacity(.5);
            p.setFill(Color.GREEN);
        }
    }

    public void handleNextScene(ActionEvent event) {
        String nextScene = properties.getProperty("scene.next");
        stateMachine.changeState(new ChangeSceneStateImpl(stateMachine, stage, nextScene));
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

    public void handleUnselect(ActionEvent event) {
        board.unselectAllHexes();
        for (Hex hex : board.getHexes()) {
            hex.getPolygon().setFill(Color.TRANSPARENT);
        }
    }
}
