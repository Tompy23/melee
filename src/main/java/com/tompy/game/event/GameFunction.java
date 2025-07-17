package com.tompy.game.event;

import com.tompy.game.GameData;
import com.tompy.game.counter.Counter;
import com.tompy.hexboard.Hex;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        hex.setOpacity(.5);
        hex.setFill(Color.BLUE);
    }

    public static void fillHexTransparent(Hex hex) {
        hex.setFill(Color.TRANSPARENT);
    }

    public static void fillHexGreen(Hex hex) {
        hex.setFill(Color.GREEN);
    }

    public static void fillHexGreenOrTransparent(Hex hex) {
        if (hex.isSelected()) {
            GameFunction.fillHexGreen(hex);
        } else {
            GameFunction.fillHexTransparent(hex);
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
        text.setX(hex.localToParent(hex.getLayoutBounds())
                .getCenterX() - (-2 + text.getLayoutBounds().getWidth() / 2.0));
        text.setY(hex.localToParent(hex.getLayoutBounds()).getCenterY() + 12);
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
                counter.setX(hex.localToParent(hex.getLayoutBounds())
                        .getCenterX() - (counter.getImage().getWidth() / 2));
                counter.setY(hex.localToParent(hex.getLayoutBounds())
                        .getCenterY() - (counter.getImage().getHeight() / 2));
                if (counter.isSelected()) {
                    counter.setStroke(Color.GREEN);
                } else {
                    counter.setStroke(Color.TRANSPARENT);
                }
                GameData.get().getController().getHexBoardPane().getChildren().add(counter);
            } else {
                long offset = 0;
                for (Counter counter : counters) {
                    counter.setWidth(counter.getImage().getWidth());
                    counter.setHeight(counter.getImage().getHeight());
                    counter.setX(hex.localToParent(hex.getLayoutBounds())
                            .getCenterX() - (counter.getImage().getWidth() / 2) + offset);
                    counter.setY(hex.localToParent(hex.getLayoutBounds())
                            .getCenterY() - (counter.getImage().getHeight() / 2) - offset);
                    if (hex.isCountersStacked()) {
                        offset += 8;
                    } else {
                        offset += 12;
                    }
                    if (counter.isSelected()) {
                        counter.setStroke(Color.GREEN);
                    } else {
                        counter.setStroke(Color.TRANSPARENT);
                    }
                    GameData.get().getController().getHexBoardPane().getChildren().add(counter);
                }
            }
        }
    }

    public static void unselectAllCountersOutsideHex(Hex otherHex) {
        for (Hex hex : GameData.get().getHexBoard().getHexes()) {
            if (!hex.equals(otherHex)) {
                for (Counter counter : hex.getCounters()) {
                    counter.unselect();
                }
            }
        }
    }

    public static void selectAllCountersInHex(Hex hex) {
        for (Counter counter : hex.getCounters()) {
            counter.select();
        }
    }

    public static void unselectAllCountersInHex(Hex hex) {
        if (hex != null) {
            for (Counter counter : hex.getCounters()) {
                    counter.unselect();
            }
        }
    }

    public static void onMouseEnterCounter(MouseEvent event) {

    }
}
