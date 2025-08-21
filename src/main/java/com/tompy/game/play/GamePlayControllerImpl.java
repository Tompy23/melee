package com.tompy.game.play;

import com.tompy.counter.Counter;
import com.tompy.counter.CounterFactory;
import com.tompy.counter.CounterType;
import com.tompy.game.AbstractGameHexBoardController;
import com.tompy.game.GameConstants;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GamePlayControllerImpl extends AbstractGameHexBoardController implements GamePlayController {
    private static final double SQRT3 = Math.sqrt(3);

    @FXML
    private Button btnMove1;
    @FXML
    private Button btnDrawLine;

    @Override
    public void enableMove1Button(boolean enable) {
        btnMove1.setDisable(!enable);
    }

    public void handleNextScene(ActionEvent event) {
        String nextScene = (String) GameHexBoardData.get().getProperty(GameConstants.SCENE_NEXT);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.SCENE_CHANGE).properties(nextScene)
                        .stage(stage).sceneLoader(new GamePlaySceneLoader()).toType(GameStateType.BEGIN_GAME_PLAY)
                        .build());
    }

    public void handleZoomIn(ActionEvent event) {
        double zoom = stackBoard.getScaleX();
        zoom += .1;
        if (zoom > 1.0) {
            zoom = 1.0;
        }
        stackBoard.setScaleX(zoom);
        stackBoard.setScaleY(zoom);
    }

    public void handleZoomOut(ActionEvent event) {
        double zoom = stackBoard.getScaleX();
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
        GameHexBoardData.get().getHexBoard().unselectAllHexes();
    }

    public void handleAddCounter(ActionEvent event) {
        Counter counter = CounterFactory.counterBuilder().type(CounterType.GLADIATOR).imageName("gladiator.png")
                .hex(GameHexBoardData.get().getHexBoard().getHex(0, 0)).movement(6).build();
        counter.setOnMouseClicked(counter::handleClick);
        counter.setOnMouseEntered(counter::handleEnter);
        counter.setOnMouseExited(counter::handleExit);
    }

    public void handleAddCounter2(ActionEvent event) {
        Counter counter = CounterFactory.counterBuilder().type(CounterType.GLADIATOR).imageName("gladiator2.png")
                .hex(GameHexBoardData.get().getHexBoard().getHex(4, 2)).movement(5).build();
        counter.setOnMouseClicked(counter::handleClick);
        counter.setOnMouseEntered(counter::handleEnter);
        counter.setOnMouseExited(counter::handleExit);
    }

    public void handleMove1(ActionEvent event) {
        GameStateMachine.get().getCurrentState().onClickMove1(event);
    }

    public void handleTestHexPointer(ActionEvent event) {
        GameStateMachine.get().getCurrentState().onClickTestPointer(event);
    }

    public void handleMouseMove(MouseEvent event) {
        GameStateMachine.get().getCurrentState().onMouseMove(event);
    }

    public void handleDrawLine(ActionEvent event) {
        GameStateMachine.get().getCurrentState().onClickDrawLine(event);
    }

    public void handleFindPath(ActionEvent event) {
        GameStateMachine.get().getCurrentState().onClickHandleFindPath(event);
    }
}
