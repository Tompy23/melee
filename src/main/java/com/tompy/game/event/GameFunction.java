package com.tompy.game.event;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameFunction {

    public static void selectHexGreenOrBlue(Hex hex) {
        if (hex.isSelected()) {
            hex.unselect();
            fillHexHalfBlue(hex);
            removeText(hex.getCoordinate().toString());
        } else {
            hex.select();
            fillHexGreen(hex);
            showHexCoordinates(hex);
        }
    }

    public static void fillHexHalfBlue(Hex hex) {
        hex.getPolygon().setOpacity(.5);
        hex.getPolygon().setFill(Color.BLUE);
    }

    public static void fillHexTransparent(Hex hex) {
        hex.getPolygon().setFill(Color.TRANSPARENT);
    }

    public static void fillHexGreen(Hex hex) {
        hex.getPolygon().setFill(Color.GREEN);
    }

    public static void fillHexGreenOrTransparent(Hex hex) {
        if (hex.isSelected()) {
            GameFunction.fillHexGreen(hex);
        } else {
            GameFunction.fillHexTransparent(hex);
            //removeText(hex.getCoordinate().toString());
        }
    }

    public static void removeText(String hexId) {
        Optional<Node> node = GameData.get().getController().getTextPane().getChildren().stream()
                .filter(n -> n.getId().equals(hexId)).findFirst();
        if (node.isPresent()) {
            GameData.get().getController().getTextPane().getChildren().remove(node.get());
        }
    }

    public static void showHexCoordinates(Hex hex) {
        Pane hexTextPane = GameData.get().getController().getTextPane();
        Text text = new Text();
        text.setStyle("-fx-font: 10 arial;");
        text.setText(hex.getCoordinate().toString());
        text.setX(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds())
                .getCenterX() - (-2 + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds()).getCenterY() + 12);
        text.setId(hex.getCoordinate().toString());
        hexTextPane.getChildren().add(text);
    }

    public static void displayCountersInHex(Hex hex) {
        List<Node> toRemove = new ArrayList<>();
        for (Node child : GameData.get().getController().getHexBoardPane().getChildren()) {
            if (child.getId() != null && child.getId().startsWith("COUNTER")) {
                toRemove.add(child);
            }
        }
        GameData.get().getController().getHexBoardPane().getChildren().removeAll(toRemove);


        List<Rectangle> counters = hex.getCounters();
        if (!counters.isEmpty()) {
            if (counters.size() == 1) {
                Rectangle counterView = counters.getFirst();
                Counter counter = (Counter) counterView.getUserData();
                counterView.setWidth(counter.getImage().getWidth());
                counterView.setHeight(counter.getImage().getHeight());
                counterView.setX(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds())
                        .getCenterX() - (counter.getImage().getWidth() / 2));
                counterView.setY(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds())
                        .getCenterY() - (counter.getImage().getHeight() / 2));
                GameData.get().getController().getHexBoardPane().getChildren().add(counterView);
            } else {
                long offset = 0;
                for (Rectangle counterView : counters) {
                    Counter counter = (Counter) counterView.getUserData();
                    counterView.setWidth(counter.getImage().getWidth());
                    counterView.setHeight(counter.getImage().getHeight());
                    counterView.setX(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds())
                            .getCenterX() - (counter.getImage().getWidth() / 2) + offset);
                    counterView.setY(hex.getPolygon().localToParent(hex.getPolygon().getLayoutBounds())
                            .getCenterY() - (counter.getImage().getHeight() / 2) - offset);
                    if (hex.isCountersStacked()) {
                        offset += 8;
                    } else {
                        offset += 12;
                    }
                    GameData.get().getController().getHexBoardPane().getChildren().add(counterView);
                }
            }
        }
    }

    public static void selectCounter(Counter counter) {

    }
}
