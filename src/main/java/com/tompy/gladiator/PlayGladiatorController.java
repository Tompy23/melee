package com.tompy.gladiator;

import com.tompy.counter.Counter;
import com.tompy.game.AbstractGameHexBoardController;
import com.tompy.game.GameHexBoardData;
import com.tompy.game.state.GameStateFactory;
import com.tompy.game.state.GameStateMachine;
import com.tompy.game.state.GameStateType;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayGladiatorController extends AbstractGameHexBoardController {

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

    @FXML
    private Pane paneMove;

    @FXML
    private Pane paneCombat;

    public PlayGladiatorController() {

    }

    public Pane getPaneMove() {
        return paneMove;
    }

    public Pane getPaneCombat() {
        return paneCombat;
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

        return returnValue;
    }

    public void showHexes(List<Hex> hexes) {
        for (Hex hex : hexes) {
            hex.setFill(Color.PINK);
            hex.setOpacity(0.6);
        }
    }

    public void resetHexes(List<Hex> hexes) {
        hexes.stream().filter(Objects::nonNull).forEach(h -> h.setFill(Color.TRANSPARENT));
    }

    private void showHexImage(Hex hex, Counter counter) {
        showHexImage(hex, counter, 0, false);
    }

    private void showHexImage(Hex hex, Counter counter, int rotation) {
        showHexImage(hex, counter, rotation, false);
    }

    private void showHexImage(Hex hex, Counter counter, int rotation, boolean ignoreRotation) {
        tempImage = new Rectangle();
        tempImage.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (counter.getImage().getWidth() / 2));
        tempImage.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() - (counter.getImage().getHeight() / 2));
        tempImage.setHeight(counter.getHeight());
        tempImage.setWidth(counter.getWidth());

        tempImage.setFill(new ImagePattern(counter.getImage()));
        tempImage.setOpacity(0.5);

        int fixedRotation = getRotation(counter, rotation, ignoreRotation);
        tempImage.setRotate(fixedRotation * 60);

        GameHexBoardData.get().getPaneHexBoard().getChildren().add(tempImage);
    }

    private int getRotation(Counter counter, int rotation, boolean ignoreRotation) {
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

        int fixedRotation = counter.getFacing() - 1 + rotation + facingOffset;
        if (fixedRotation < 0) {
            fixedRotation += 6;
        }
        if (fixedRotation > 5) {
            fixedRotation -= 6;
        }

        return fixedRotation;
    }

    private void removeHexImage() {
        GameHexBoardData.get().getPaneHexBoard().getChildren().remove(tempImage);
    }

//    private void moveCounter(Hex hex, Counter counter) {
//        moveCounter(hex, counter, 0, false);
//    }
//
//    private void moveCounter(Hex hex, Counter counter, int rotation) {
//        moveCounter(hex, counter, rotation, false);
//    }
//
//    private void moveCounter(Hex hex, Counter counter, int rotation, boolean ignoreRotation) {
//        int fixedRotation = getRotation(counter, rotation, ignoreRotation);
//        counter.setFacing(fixedRotation + 1);
//        counter.getHex().removeCounter(counter);
//        hex.addCounter(counter);
//    }

    public void onForwardAction(ActionEvent event) {
        Hex destination = getMove(0).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), 0, false);
        GladiatorData.get().getPlayer().setMoveToHex(destination);
        GladiatorData.get().getPlayer().setMoveToRotation(rotation);
        onForwardExit(null);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).pane(paneMove)
                        .secondPane(paneCombat).build());
    }

    public void onForwardEnter(MouseEvent event) {
        List<Hex> hexes = getMove(0);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter());

    }

    public void onForwardExit(MouseEvent event) {
        List<Hex> hexes = getMove(0);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onBackwardAction(ActionEvent event) {
        Hex destination = getMove(3).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), 3, false);
        onBackwardsExited(null);
        GladiatorData.get().getPlayer().setMoveToHex(destination);
        GladiatorData.get().getPlayer().setMoveToRotation(rotation);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).pane(paneMove)
                        .secondPane(paneCombat).build());
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

    public void onSSFLAction(ActionEvent actionevent) {
        Hex destination = getMove(1).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), 1, false);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).originHex(destination)
                        .intValue(rotation).pane(paneMove).secondPane(paneCombat).build());
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

    public void onSSFRAction(ActionEvent event) {
        Hex destination = getMove(-1).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), -1, false);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).originHex(destination)
                        .intValue(rotation).pane(paneMove).secondPane(paneCombat).build());
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

    public void onSSBLAction(ActionEvent event) {
        Hex destination = getMove(2).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), 2, false);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).originHex(destination)
                        .intValue(rotation).pane(paneMove).secondPane(paneCombat).build());
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

    public void onSSBRAction(ActionEvent event) {
        Hex destination = getMove(-2).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), -2, false);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).originHex(destination)
                        .intValue(rotation).pane(paneMove).secondPane(paneCombat).build());
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

    public void onChargeAction(ActionEvent event) {
        Hex destination = getMove(1, 3, true, true).getLast();
        int rotation = getRotation(GladiatorData.get().getPlayer().getCounter(), 0, true);
        GameStateMachine.get()
                .changeState(GameStateFactory.buidler().type(GameStateType.GLADIATOR_MOVE).originHex(destination)
                        .intValue(rotation).pane(paneMove).secondPane(paneCombat).build());
    }

    public void onChargeEntered(MouseEvent event) {
        List<Hex> hexes = getMove(0, 3, true, true);
        showHexes(hexes);
        showHexImage(hexes.getLast(), GladiatorData.get().getPlayer().getCounter(), 0, true);
    }

    public void onChargeExited(MouseEvent event) {
        List<Hex> hexes = getMove(0, 3, true, true);
        resetHexes(hexes);
        removeHexImage();
    }

    public void onPauseAction(ActionEvent event) {

    }

    public void onPauseEntered(MouseEvent event) {

    }

    public void onPauseExited(MouseEvent event) {

    }

    public void onRecoverAction(ActionEvent event) {

    }

    public void onRecoverEntered(MouseEvent event) {

    }

    public void onRecoverExited(MouseEvent event) {

    }

    public void onKneelAction(ActionEvent event) {

    }

    public void onKneelEntered(MouseEvent event) {

    }

    public void onKneelExited(MouseEvent event) {

    }

    public void onRollFLAction(ActionEvent event) {

    }

    public void onRollFLEntered(MouseEvent event) {

    }

    public void onRollFLExited(MouseEvent event) {

    }

    public void onRollFRAction(ActionEvent event) {

    }

    public void onRollFREntered(MouseEvent event) {

    }

    public void onRollFRExited(MouseEvent event) {

    }

    public void onRollBLAction(ActionEvent event) {

    }

    public void onRollBLEntered(MouseEvent event) {

    }

    public void onRollBLExited(MouseEvent event) {

    }

    public void onRollBRAction(ActionEvent event) {

    }

    public void onRollBREntered(MouseEvent event) {

    }

    public void onRollBRExited(MouseEvent event) {

    }

    public void onKickAction(ActionEvent event) {

    }

    public void onKickEntered(MouseEvent event) {

    }

    public void onKickExited(MouseEvent event) {

    }
}