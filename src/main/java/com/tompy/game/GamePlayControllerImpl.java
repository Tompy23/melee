package com.tompy.game;

import com.tompy.counter.Counter;
import com.tompy.counter.CounterFactory;
import com.tompy.counter.CounterType;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    @FXML
    private Pane paneText;
    @FXML
    private Button btnMove1;

    //private double zoom = 1.0;

    private Stage stage;

    private GameData gameData;

    @Override
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void drawHexBoard() {
        HexBoard board = GameData.get().getHexBoard();
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

        paneText.setPrefWidth(panelWidth);
        paneText.setPrefHeight(panelHeight);

        for (Hex hex : board.getHexes()) {
            paneHexBoard.getChildren().add(hex);
            hex.setOnMouseEntered(hex::handleEnter);
            hex.setOnMouseExited(hex::handleExit);
            hex.setOnMouseClicked(hex::handleClick);
            hex.changeState(HexStateFactory.get().builder().type(HexStateType.COMMON).switch1(false).hex(hex).build());

            double hexWidth = SQRT3 * board.getPixelSize();

            long j = hex.getCol();
            long i = hex.getRow();

            double x = board.getBorder() + j / 2.0 * hexWidth;
            double y = board.getBorder() + (i * 1.5 * board.getPixelSize());

            Circle c = new Circle();
            c.setCenterX(x + hexWidth / 2);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexDecorator.getChildren().add(c);
        }

        paneBackImage.setBackground(Background.fill(Color.ORANGE));
    }

    @Override
    public Pane getHexBoardPane() {
        return paneHexBoard;
    }

    @Override
    public Pane getTextPane() {
        return paneText;
    }

    @Override
    public void enableMove1Button(boolean enable) {
        btnMove1.setDisable(!enable);
    }

    public void handleNextScene(ActionEvent event) {
        String nextScene = (String) GameData.get().getProperty("scene.next");
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(GameStateType.SCENE_CHANGE).properties(nextScene).stage(stage).build());
    }

    public void handleZoomIn(ActionEvent event) {
        double zoom = gameData.getZoom();
        zoom += .1;
        if (zoom > 2.0) {
            zoom = 2.0;
        }
        stackBoard.setScaleX(zoom);
        stackBoard.setScaleY(zoom);
    }

    public void handleZoomOut(ActionEvent event) {
        double zoom = gameData.getZoom();
        zoom -= .1;
        if (zoom < 0.3) {
            zoom = 0.3;
        }
        stackBoard.setScaleX(zoom);
        stackBoard.setScaleY(zoom);
    }

    public void handleUnselect(ActionEvent event) {
        // TODO THis is going to go away once we have all the stuff coordinated
        // Dangerous for sure
        GameData.get().getHexBoard().unselectAllHexes();
    }

    public void handleAddCounter(ActionEvent event) {
        Counter counter = CounterFactory.counterBuilder().type(CounterType.GLADIATOR).imageName("gladiator.png")
                .hex(GameData.get().getHexBoard().getHex(0, 0)).movement(6).build();
        counter.setOnMouseClicked(counter::handleClick);
        counter.setOnMouseEntered(counter::handleEnter);
        counter.setOnMouseExited(counter::handleExit);
    }

    public void handleAddCounter2(ActionEvent event) {
        Counter counter = CounterFactory.counterBuilder().type(CounterType.GLADIATOR).imageName("gladiator2.png")
                .hex(GameData.get().getHexBoard().getHex(4, 2)).movement(5).build();
        counter.setOnMouseClicked(counter::handleClick);
        counter.setOnMouseEntered(counter::handleEnter);
        counter.setOnMouseExited(counter::handleExit);
    }

    public void handleMove1(ActionEvent event) {
        GameStateMachine.get().onClickMove1(event);
    }

    public void handleTestHexPointer(ActionEvent event) {
        GameStateMachine.get().onClickTestPointer(event);
    }

    public void handleMouseMove(MouseEvent event) {
        GameStateMachine.get().onMouseMove(event);
    }
}
