package com.tompy.hexboard;

import java.util.*;

public class HexBoard {
    private final int pixelHeight;
    private final int pixelWidth;
    private final int height;
    private final int width;
    private List<Hex> hexes;
    private Map<HexCoordinate, Hex> hexMap;

    public HexBoard(Builder builder) {
        this.pixelHeight = builder.pixelHeight;
        this.pixelWidth = builder.pixelWidth;
        this.height = builder.height;
        this.width = builder.width;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Hex hex = Hex.builder().setQ(j).setR(i).build();
                hexes.add(hex);
                hexMap.put(hex.getCoordinate(), hex);
            }
        }
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public int getPixelWidth() {
        return pixelWidth;
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
        return hexMap.get(HexCoordinate.builder().setQ(q).setR(r).build());
    }

    public List<Hex> getSelectedHexes() {
        return hexes.stream().filter(Hex::isSelected).toList();
    }

    public void unselectAllHexes() {
        hexes.forEach(Hex::unselect);
    }

    public static final class Builder {
        private int pixelHeight;
        private int pixelWidth;
        private int height;
        private int width;

        public Builder pixelHeight(int pixelHeight) {
            this.pixelHeight = pixelHeight;
            return this;
        }

        public Builder pixelWidth(int pixelWidth) {
            this.pixelWidth = pixelWidth;
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
