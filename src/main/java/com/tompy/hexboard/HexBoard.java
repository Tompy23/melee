package com.tompy.hexboard;

import javafx.scene.paint.Color;

import java.util.*;

public class HexBoard {
    private static final double SQRT3 = Math.sqrt(3);
    private final int border;
    private final int pixelSize;
    private final int height;
    private final int width;
    private final List<Hex> hexes;
    private final Map<HexCoordinate, Hex> hexMap;

    public HexBoard(Builder builder) {
        this.border = builder.border;
        this.pixelSize = builder.pixelSize;
        this.height = builder.height;
        this.width = builder.width;

        hexes = new ArrayList<>();
        hexMap = new HashMap<>();

        double[] coordinates = new double[12];

        int angle = 30;
        for (int j = 0; j < 12; j += 2) {
            double radians = angle * Math.PI / 180;
            coordinates[j] = pixelSize * Math.cos(radians);
            coordinates[j + 1] = pixelSize * Math.sin(radians);
            angle += 60;
        }

        double hexWidth = SQRT3 * pixelSize;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width * 2; j += 2) {
                int row = i;
                int col = j;

                double x = border + j / 2.0 * hexWidth;
                double y = border + (i * 1.5 * pixelSize);
                if (i % 2.0 == 0) {
                    x += 1.0 / 2.0 * hexWidth;
                } else {
                    col--;
                }

                double[] finalCoordinates = new double[12];
                for (int k = 0; k < 12; k += 2) {
                    finalCoordinates[k] = coordinates[k] + x;
                    finalCoordinates[k + 1] = coordinates[k + 1] + y;
                }

                Hex hex = Hex.builder().setCol(col).setRow(row).coordinates(finalCoordinates).build();
                hex.setFill(Color.TRANSPARENT);
                hex.setStroke(Color.BLACK);
                hex.setStrokeWidth(1.0);
                hexes.add(hex);
                hexMap.put(hex.getCoordinate(), hex);
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getBorder() {
        return border;
    }

    public int getPixelSize() {
        return pixelSize;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Hex> getHexes() {
        return Collections.unmodifiableList(hexes);
    }

    public Hex getHex(int q, int r) {
        return hexMap.get(HexCoordinate.builder().setCol(q).setRow(r).build());
    }

    public List<Hex> getSelectedHexes() {
        return hexes.stream().filter(Hex::isSelected).toList();
    }

    public void unselectAllHexes() {
        hexes.forEach(Hex::unselect);
    }

    public static final class Builder {
        private int border;
        private int pixelSize;
        private int height;
        private int width;

        public Builder border(int border) {
            this.border = border;
            return this;
        }

        public Builder pixelSize(int pixelSize) {
            this.pixelSize = pixelSize;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public HexBoard build() {
            return new HexBoard(this);
        }
    }
}
