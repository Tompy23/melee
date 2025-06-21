package com.tompy.hexboard;

import java.util.*;

public class HexBoard {
    private final int pixelSize;
    private final int height;
    private final int width;
    private List<Hex> hexes;
    private Map<HexCoordinate, Hex> hexMap;

    public HexBoard(Builder builder) {
        this.pixelSize = builder.pixelSize;
        this.height = builder.height;
        this.width = builder.width;

        hexes = new ArrayList<>();
        hexMap = new HashMap<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width * 2; j += 2) {
                Hex hex = Hex.builder().setCol(j).setRow(i).build();
                hexes.add(hex);
                hexMap.put(hex.getCoordinate(), hex);
            }
        }
    }

    public static Builder builder() {
        return new Builder();
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
        private int pixelSize;
        private int height;
        private int width;

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
