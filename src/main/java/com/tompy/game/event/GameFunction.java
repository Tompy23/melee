package com.tompy.game.event;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameFunction {

    public static void selectHexSetProperties(Hex hex) {
        if (hex.isSelected()) {
            hex.unselect();
            enterHexSetProperties(hex);
            removeText(hex.getCoordinate().toString());
        } else {
            hex.select();
            hex.setFill(Color.GREEN);
            showHexCoordinates(hex);
        }
    }

    public static void enterHexSetProperties(Hex hex) {
        hex.setOpacity(.5);
        hex.setFill(Color.BLUE);
    }

    public static void exitHexSetProperties(Hex hex) {
        if (hex.isSelected()) {
            hex.setFill(Color.GREEN);
        } else {
            hex.setFill(Color.TRANSPARENT);
        }
    }

    public static void removeText(String hexId) {
        GameData.get().getController().getTextPane().getChildren().stream().filter(n -> n.getId().equals(hexId))
                .findFirst()
                .ifPresent(value -> GameData.get().getController().getTextPane().getChildren().remove(value));
    }

    public static void showHexCoordinates(Hex hex) {
        Pane hexTextPane = GameData.get().getController().getTextPane();
        Text text = new Text();
        text.setStyle("-fx-font: 10 arial;");
        text.setText(hex.getCoordinate().toString());
        text.setX(
                hex.localToParent(hex.getLayoutBounds()).getCenterX() - (-2 + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() + 12);
        text.setId(hex.getCoordinate().toString());
        hexTextPane.getChildren().add(text);
    }

    public static void showTextHex(Hex hex, String display) {
        Pane hexTextPane = GameData.get().getController().getTextPane();
        Text text = new Text();
        text.setStyle("-fx-font: 16 arial;");
        text.setText(display);
        text.setX(
                hex.localToParent(hex.getLayoutBounds()).getCenterX() - (-2 + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() + 20);
        text.setId(hex.getCoordinate().toString());
        hexTextPane.getChildren().add(text);
    }

    public static void displayCountersInHex(Hex hex) {
        List<Node> toRemove = new ArrayList<>();
        for (Node child : GameData.get().getController().getHexBoardPane().getChildren()) {
            if (child.getId() != null && child.getId().startsWith("COUNTER")) {
                Counter counter = (Counter) child;
                if (counter.getHex().equals(hex)) {
                    toRemove.add(child);
                }
            }
        }
        GameData.get().getController().getHexBoardPane().getChildren().removeAll(toRemove);


        List<Counter> counters = hex.getCounters();
        if (!counters.isEmpty()) {
            if (counters.size() == 1) {
                Counter counter = counters.getFirst();
                counter.setWidth(counter.getImage().getWidth());
                counter.setHeight(counter.getImage().getHeight());
                counter.setX(
                        hex.localToParent(hex.getLayoutBounds()).getCenterX() - (counter.getImage().getWidth() / 2));
                counter.setY(
                        hex.localToParent(hex.getLayoutBounds()).getCenterY() - (counter.getImage().getHeight() / 2));
                GameData.get().getController().getHexBoardPane().getChildren().add(counter);
            } else {
                long offset = 0;
                for (Counter counter : counters) {
                    counter.setWidth(counter.getImage().getWidth());
                    counter.setHeight(counter.getImage().getHeight());
                    counter.setX(hex.localToParent(hex.getLayoutBounds()).getCenterX() - (counter.getImage()
                            .getWidth() / 2) + offset);
                    counter.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() - (counter.getImage()
                            .getHeight() / 2) - offset);
                    if (hex.isCountersStacked()) {
                        offset += 8;
                    } else {
                        offset += 12;
                    }
                    GameData.get().getController().getHexBoardPane().getChildren().add(counter);
                }
            }
        }
    }

    public static void unselectAllCountersOutsideHex(Hex otherHex) {
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            if (otherHex != null && !hex.equals(otherHex)) {
                hex.getCounters().forEach(GameFunction::unSelectCounter);
            }
        }
    }

    public static void selectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(GameFunction::selectCounter);
        }
    }

    public static void unselectAllCountersInHex(Hex hex) {
        if (hex != null) {
            hex.getCounters().forEach(GameFunction::unSelectCounter);
        }
    }

    public static void selectCounter(Counter counter) {
        counter.select();
        counter.setStroke(Color.GREEN);
    }

    public static void unSelectCounter(Counter counter) {
        counter.unselect();
        counter.setStroke(Color.TRANSPARENT);
    }

    public static void enterCounterSetProperties(Counter counter) {
        if (counter.getHex().isCountersStacked()) {
            counter.getHex().getCounters().forEach(c -> c.setStroke(Color.BLACK));
        } else {
            counter.setStroke(Color.BLACK);
        }
    }

    public static void exitCounterSetProperties(Counter counter) {
        for (Counter thisCounter : counter.getHex().getCounters()) {
            if (thisCounter.isSelected()) {
                thisCounter.setStroke(Color.GREEN);
            } else {
                thisCounter.setStroke(Color.TRANSPARENT);
            }
        }
    }
}
