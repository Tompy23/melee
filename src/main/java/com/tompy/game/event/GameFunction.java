package com.tompy.game.event;

import com.tompy.counter.Counter;
import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameFunction {

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

    public static List<Hex> bfsFindPathCost(Hex start, Hex target) {
        Map<Hex, Hex> cameFrom = new HashMap<>();
        PriorityQueue<HexNode> grid = new PriorityQueue<>();
        HexNode startNode = new HexNode(start, 0L);
        grid.add(startNode);
        cameFrom.put(start, null);
        Map<Hex, Long> costSoFar = new HashMap<>();
        costSoFar.put(start, 0L);

        while (!grid.isEmpty()) {
            HexNode current = grid.poll();

            for (Hex neighbor : HexFunction.getNeighbors(current.getHex())) {
                long newCost = costSoFar.get(current.getHex()) + neighbor.getEntryCost();

                if (!cameFrom.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    costSoFar.put(neighbor, newCost);
                    grid.add(new HexNode(neighbor, newCost));
                    cameFrom.put(neighbor, current.getHex());
                }
            }
        }

        return reconstructPath(start, target, cameFrom);
    }



    public static List<Hex> bfsFindPath(Hex start, Hex target) {
        Map<Hex, Hex> cameFrom = new HashMap<>();
        Queue<Hex> grid = new LinkedList<>();
        grid.add(start);
        cameFrom.put(start, null);

        while (!grid.isEmpty()) {
            Hex current = grid.poll();
            for (Hex neighbor : HexFunction.getNeighbors(current)) {
                if (!cameFrom.containsKey(neighbor)) {
                    grid.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        return reconstructPath(start, target, cameFrom);
    }

    private static List<Hex> reconstructPath(Hex start, Hex goal, Map<Hex, Hex> parentMap) {
        // construct output list
        LinkedList<Hex> path = new LinkedList<>();
        Hex currNode = goal;
        while (!currNode.equals(start)) {
            path.addFirst(currNode);
            currNode = parentMap.get(currNode);
        }
        path.addFirst(start);
        return path;
    }

    private static class HexNode implements Comparable<HexNode> {
        private Hex hex;
        private long costFromStart;

        public HexNode(Hex hex, long cost) {
            this.hex = hex;
            this.costFromStart = cost;
        }

        public Hex getHex() {
            return hex;
        }

        @Override
        public int compareTo(@NotNull HexNode o) {
            return Math.toIntExact(costFromStart - o.costFromStart);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof HexNode)) {
                return false;
            }
            HexNode node = (HexNode)o;
            return node.getHex().equals(hex);
        }

        @Override
        public int hashCode() {
            return hex.hashCode() * Math.toIntExact(costFromStart);
        }
    }
}
