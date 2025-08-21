package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.game.AbstractGameHexBoardController;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.state.GameStateMachine;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PlayGladiatorController extends AbstractGameHexBoardController {
    private boolean moveSet = false;
    private Rectangle tempImage;

    @FXML
    private CheckBox cbxQuick;

    public PlayGladiatorController() {

    }

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

    private Hex findNext(Hex hex, int facing) {
        Hex[] neighbors = HexFunction.getNeighborArray(hex);

        return neighbors[GladiatorData.get().getPlayer().getCounter().getFacing()];
    }

    public void onForwardEnter(MouseEvent event) {
        if (!moveSet) {
            Counter counter = GladiatorData.get().getPlayer().getCounter();
            Hex hex = findNext(counter.getHex(), counter.getFacing());

            hex.setFill(Color.PINK);
            hex.setOpacity(0.6);

            if (cbxQuick.isSelected()) {
                hex = findNext(hex, counter.getFacing());
                hex.setFill(Color.PINK);
                hex.setOpacity(0.6);
            }

            tempImage = new Rectangle();
            tempImage.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (counter.getImage()
                    .getWidth() / 2));
            tempImage.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() - (counter.getImage()
                    .getHeight() / 2));
            tempImage.setHeight(counter.getHeight());
            tempImage.setWidth(counter.getWidth());

            tempImage.setFill(new ImagePattern(counter.getImage()));
            tempImage.setOpacity(0.5);
            tempImage.setRotate((counter.getFacing() - 1) * 60);
            GameHexBoardData.get().getPaneHexBoard().getChildren().add(tempImage);
        }
    }

    public void onForwardExit(MouseEvent event) {
        if (!moveSet) {
            Counter counter = GladiatorData.get().getPlayer().getCounter();
            Hex hex = findNext(counter.getHex(), counter.getFacing());
            hex.setFill(Color.TRANSPARENT);

            if (cbxQuick.isSelected()) {
                Hex quick = findNext(hex, counter.getFacing());
                quick.setFill(Color.TRANSPARENT);
            }

            GameHexBoardData.get().getPaneHexBoard().getChildren().remove(tempImage);
        }
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