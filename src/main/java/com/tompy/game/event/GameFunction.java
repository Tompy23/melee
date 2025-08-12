package com.tompy.game.event;

import com.tompy.counter.Counter;
import com.tompy.game.GameData;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexFunction;
import javafx.scene.Node;

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

    public static List<Hex> findPath(Hex start, Hex target) {
        Map<Hex, Hex> parentMap = new HashMap<>();
        Set<Hex> visited = new HashSet<>();
        Map<Hex, Long> distances = new HashMap<>();
        GameData.get().getHexBoard().getHexes().forEach(h -> distances.put(h, Long.MAX_VALUE));
        PriorityQueue<Hex> priorityQueue = new PriorityQueue<>(HexPriorityComparator::hexPriorityCompare);
        priorityQueue.add(start);
        distances.put(start, 0L);
        HexPriorityComparator.startHex = start;
        Hex current = null;

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();

            if (!visited.contains(target)) {
                visited.add(current);

                if (current.equals(target)) {
                    return reconstructPath(start, target, parentMap);
                }

                List<Hex> neighbors = HexFunction.getNeighbors(current);
                for (Hex neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        //long predictedDistance = HexFunction.distance(next, target);
                        long neighborDistance = HexFunction.distance2(current, neighbor);
                        long totalDistance = neighborDistance + HexFunction.distance2(current, start);
                        if (totalDistance < distances.get(neighbor)) {
                            distances.put(neighbor, totalDistance);
                            parentMap.put(neighbor, current);
                            priorityQueue.add(neighbor);
                        }

                        long newCost = distances.get(current) + neighbor.getEntryCost();
                        if (!distances.containsKey(neighbor) || newCost < distances.get(neighbor)) {
                            distances.put(neighbor, newCost);
                        }
                    }
                }
            }
        }

        return null;
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

    private static class HexPriorityComparator {
        private static Hex startHex;

        public static int hexPriorityCompare(Hex h1, Hex h2) {
            return HexFunction.distance2(h1, startHex) < HexFunction.distance2(h2, startHex) ? 1 : 0;
        }
    }
}
