package com.tompy.game.state;

import com.tompy.game.GameData;
import com.tompy.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.Hex;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public abstract class AbstractGameState implements GameState {
    @Override
    public void beginState() {

    }

    @Override
    public void endState() {

    }

    @Override
    public void process(long l) {

    }

//    @Override
//    public void onMouseEnterCounter(MouseEvent event) {
//        Counter counter = (Counter) event.getTarget();
//
//        GameFunction.enterCounterSetProperties(counter);
//        counter.getHex().handleEnter(event);
//
//        GameData.get().setHexWithMouse(counter.getHex());
//    }
//
//    @Override
//    public void onMouseLeaveCounter(MouseEvent event) {
//        Counter counter = (Counter) event.getTarget();
//
//        GameFunction.exitCounterSetProperties(counter);
//        counter.getHex().handleExit(event);
//
//        GameData.get().setHexWithMouse(null);
//    }

//    @Override
//    public void onClickCounter(MouseEvent event) {
//        Counter counter = (Counter) event.getTarget();
//        Hex hex = counter.getHex();
//        if (event.getClickCount() == 1) {
//            if (!event.isControlDown()) {
//                GameFunction.unselectAllCountersOutsideHex(hex);
//            }
//
//            if (hex.isCountersStacked()) {
//                if (counter.isSelected()) {
//                    GameFunction.unselectAllCountersInHex(hex);
//                } else {
//                    GameFunction.selectAllCountersInHex(hex);
//                }
//            } else {
//                if (!event.isControlDown()) {
//                    GameFunction.unselectAllCountersInHex(hex);
//                }
//                if (counter.isSelected()) {
//                    GameFunction.unSelectCounter(counter);
//                } else {
//                    GameFunction.selectCounter(counter);
//                }
//            }
//        } else {
//            if (event.getClickCount() == 2) {
//                if (hex.isCountersStacked()) {
//                    hex.unstackCounters();
//                } else {
//                    hex.stackCounters();
//                    if (counter.isSelected()) {
//                        GameFunction.selectAllCountersInHex(hex);
//                    } else {
//                        GameFunction.unselectAllCountersInHex(hex);
//                    }
//                }
//            }
//        }
//        GameFunction.displayCountersInHex(hex);
//    }

    @Override
    public void onClickMove1(ActionEvent event) {

    }

    @Override
    public void onClickTestPointer(ActionEvent event) {

    }

    @Override
    public void onMouseMove(MouseEvent event) {

    }
}
