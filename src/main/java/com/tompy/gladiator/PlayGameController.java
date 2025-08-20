package com.tompy.gladiator;

import com.tompy.game.AbstractGameHexBoardController;
import com.tompy.game.state.GameStateMachine;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class PlayGameController extends AbstractGameHexBoardController {

    public void handleMouseMove(MouseEvent event) {
        GameStateMachine.get().getCurrentState().onMouseMove(event);
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

    public void onForwardEnter(MouseEvent event) {

    }

    public void onForwardExit(MouseEvent event) {

    }

    public void onBackwardsEntered(MouseEvent event) {

    }

    public void onBackwardsExited(MouseEvent event) {

    }

    public void onSSFLEntered(MouseEvent event) {

    }

    public void onSSFLExited(MouseEvent event) {

    }

    public void onSSFREntered(MouseEvent event) {

    }

    public void onSSFRExited(MouseEvent event) {

    }

    public void onSSBLEntered(MouseEvent event) {

    }

    public void onSSBLExited(MouseEvent event) {

    }

    public void onSSBREntered(MouseEvent event) {

    }

    public void onSSBRExited(MouseEvent event) {

    }

    public void onChargeEntered(MouseEvent event) {

    }

    public void onChargeExited(MouseEvent event) {

    }

    public void onPauseEntered(MouseEvent event) {

    }

    public void onPauseExited(MouseEvent event) {

    }

    public void onRecoverEntered(MouseEvent event) {

    }

    public void onRecoverExited(MouseEvent event) {

    }

    public void onKneelEntered(MouseEvent event) {

    }

    public void onKneelExited(MouseEvent event) {

    }

    public void onRollFLEntered(MouseEvent event) {

    }

    public void onRollFLExited(MouseEvent event) {

    }

    public void onRollFREntered(MouseEvent event) {

    }

    public void onRollFRExited(MouseEvent event) {

    }

    public void onRollBLEntered(MouseEvent event) {

    }

    public void onRollBLExited(MouseEvent event) {

    }

    public void onRollBREntered(MouseEvent event) {

    }

    public void onRollBRExited(MouseEvent event) {

    }

    public void onKickEntered(MouseEvent event) {

    }

    public void onKickExited(MouseEvent event) {

    }

}