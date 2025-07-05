package com.tompy.game;

import com.tompy.game.state.ChangeSceneStateImpl;
import com.tompy.game.state.GameStateMachine;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Properties;

public class GamePlayControllerImpl implements GamePlayController {
    private static final double SQRT3 = Math.sqrt(3);
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

    private HexBoard board;

    private double zoom = 1.0;

    private Properties properties;

    private Stage stage;

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void showGrid() {
        int pixelSize = Integer.parseInt(properties.getProperty("board.pixel.size"));
        int height = Integer.parseInt(properties.getProperty("board.height"));
        int width = Integer.parseInt(properties.getProperty("board.width"));
        int border = Integer.parseInt(properties.getProperty("board.border"));
        board = HexBoard.builder().pixelSize(pixelSize).height(height).width(width).border(border).build();

        drawHexBoard();
    }

    private void drawHexBoard() {
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

            GameStateMachine stateMachine = GameStateMachine.get();
            paneHexBoard.getChildren().add(hex.getPolygon());
            hex.getPolygon().setOnMouseEntered(stateMachine::onMouseEnterHex);
            hex.getPolygon().setOnMouseExited(stateMachine::onMouseLeaveHex);
            hex.getPolygon().setOnMouseClicked(stateMachine::onClickHex);

            Circle c = new Circle();
            c.setCenterX(x);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexDecorator.getChildren().add(c);
        }

        paneBackImage.setBackground(Background.fill(Color.ORANGE));
    }

    public void handleNextScene(ActionEvent event) {
        String nextScene = properties.getProperty("scene.next");
        GameStateMachine.get().changeState(new ChangeSceneStateImpl(stage, nextScene));
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
