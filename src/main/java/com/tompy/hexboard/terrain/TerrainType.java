package com.tompy.hexboard.terrain;

public enum TerrainType {
    CLEAR ("CLEAR"),
    WALL ("WALL"),
    WATER ("WATER");

    private String tag;

    TerrainType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
