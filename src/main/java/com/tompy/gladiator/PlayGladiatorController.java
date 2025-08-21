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
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class PlayGladiatorController extends AbstractGameHexBoardController {
    private boolean moveSet = false;
    private Rectangle tempImage;

    @FXML
    private CheckBox chbQuick;

    @FXML
    private RadioButton radPreRight;

    @FXML
    private RadioButton radPreLeft;

    @FXML
    private RadioButton radPostRight;

    @FXML
    private RadioButton radPostLeft;

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

        return neighbors[facing];
    }

    private List<Hex> getMove(int directionOffset) {
        return getMove(directionOffset, 1, false, false);
    }

    private List<Hex> getMove(int directionOffset, int length, boolean ignoreQuick, boolean ignoreRotationOffset) {
        int offset = 0;
        if (!ignoreRotationOffset && radPreRight.isSelected()) {
            offset = -1;
        }
        if (!ignoreRotationOffset && radPreLeft.isSelected()) {
            offset = 1;
        }

        List<Hex> returnValue = new ArrayList<>();
        if (!moveSet) {
            Counter counter = GladiatorData.get().getPlayer().getCounter();
            int facing = counter.getFacing() - directionOffset - offset;
            if (facing < 0) {
                facing += 6;
            }
            if (facing > 5) {
                facing -= 6;
            }
            Hex hex = findNext(counter.getHex(), facing);
            returnValue.add(hex);
            for (int i = 1; i < length; i++) {
                hex = findNext(hex, facing);
                returnValue.add(hex);
            }

            if (chbQuick.isSelected() && !ignoreQuick) {
                returnValue.add(findNext(hex, facing));
            }
        }
        return returnValue;
    }

    public void showHexes(List<Hex> hexes) {
        for (Hex hex : hexes) {
            hex.setFill(Color.PINK);
            hex.setOpacity(0.6);
        }
    }

    public void resetHexes(List<Hex> hexes) {
        hexes.forEach(h -> h.setFill(Color.TRANSPARENT));
    }

    private void showHexImage(Hex hex, Counter counter) {
        showHexImage(hex, counter, 0, false);
    }

    private void showHexImage(Hex hex, Counter counter, int rotation) {
        showHexImage(hex, counter, rotation, false);
    }

    private void showHexImage(Hex hex, Counter counter, int rotation, boolean ignoreRotation) {
        int facingOffset = 0;
        if (!ignoreRotation && radPreRight.isSelected()) {
            facingOffset = 1;
        }
        if (!ignoreRotation && radPreLeft.isSelected()) {
            facingOffset = -1;
        }
        if (!ignoreRotation && radPostRight.isSelected()) {
            facingOffset = 1;
        }
        if (!ignoreRotation && radPostLeft.isSelected()) {
            facingOffset = -1;
        }
        tempImage = new Rectangle();
        tempImage.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (counter.getImage().getWidth() / 2));
        tempImage.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() - (counter.getImage().getHeight() / 2));
        tempImage.setHeight(counter.getHeight());
        tempImage.setWidth(counter.getWidth());

        tempImage.setFill(new ImagePattern(counter.getImage()));
        tempImage.setOpacity(0.5);

        int fixedRotation = counter.getFacing() - 1 + rotation + facingOffset;
        if (fixedRotation < 0) {
            fixedRotation += 6;
        }
        if (fixedRotation > 5) {
            fixedRotation -= 6;
        }
        tempImage.setRotate(fixedRotation * 60);

        GameHexBoardData.get().getPaneHexBoard().getChildren().add(tempImage);
    }

    private void removeHexImage() {
        GameHexBoardData.get().getPaneHexBoard().getChildren().remove(tempImage);
    }

    public void onForwardEnter(MouseEvent event) {
        List<Hex> hexes = getMove(+0);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onForwardExit(MouseEvent event) {
        List<Hex> hexes = getMove(0);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onBackwardsEntered(MouseEvent event) {
        List<Hex> hexes = getMove(3);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onBackwardsExited(MouseEvent event) {
        List<Hex> hexes = getMove(3);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onSSFLEntered(MouseEvent event) {
        List<Hex> hexes = getMove(1);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onSSFLExited(MouseEvent event) {
        List<Hex> hexes = getMove(1);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onSSFREntered(MouseEvent event) {
        List<Hex> hexes = getMove(-1);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onSSFRExited(MouseEvent event) {
        List<Hex> hexes = getMove(-1);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onSSBLEntered(MouseEvent event) {
        List<Hex> hexes = getMove(2);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onSSBLExited(MouseEvent event) {
        List<Hex> hexes = getMove(2);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onSSBREntered(MouseEvent event) {
        List<Hex> hexes = getMove(-2);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());
    }

    public void onSSBRExited(MouseEvent event) {
        List<Hex> hexes = getMove(-2);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onChargeEntered(MouseEvent event) {
        List<Hex> hexes = getMove(0, 3, true, true);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter(),0, true);
    }

    public void onChargeExited(MouseEvent event) {
        List<Hex> hexes = getMove(0, 3, true, true);
        resetHexes(hexes);
        removeHexImage();
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