package com.tompy.game;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.state.HexStateFactory;
import com.tompy.hexboard.state.HexStateType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AbstractGameHexBoardController extends AbstractGameController implements GameHexBoardController {
    @FXML
    protected ScrollPane scrollBackground;
    @FXML
    protected StackPane stackBoard;
    @FXML
    protected Pane paneBackImage;
    @FXML
    protected Pane paneHexDecorator;
    @FXML
    protected Pane paneHexBoard;
    @FXML
    protected Pane paneText;


    @Override
    public Pane getHexBoardPane() {
        return paneHexBoard;
    }

    @Override
    public Pane getTextPane() {
        return paneText;
    }

    @Override
    public void drawHexBoardWithLayout() {
        drawHexBoard();
    }

    @Override
    public void drawHexBoard() {
        HexBoard board = GameHexBoardData.get().getHexBoard();
        double height = 1.5 * board.getPixelSize();
        double width = GameConstants.SQRT3 * board.getPixelSize();

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
            hex.changeState(HexStateFactory.builder().type(HexStateType.COMMON).switch1(false).hex(hex).build());

            double hexWidth = GameConstants.SQRT3 * board.getPixelSize();

            long j = hex.getCol();
            long i = hex.getRow();

            double x = board.getBorder() + j / 2.0 * hexWidth;
            double y = board.getBorder() + (i * 1.5 * board.getPixelSize());

            paneHexDecorator.getChildren().add(hex.fillTerrain());

            Circle c = new Circle();
            c.setCenterX(x + hexWidth / 2);
            c.setCenterY(y);
            c.setRadius(3);
            paneHexDecorator.getChildren().add(c);
        }

        paneBackImage.setBackground(Background.fill(Color.ORANGE));
    }

}
