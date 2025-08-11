package com.tompy.hexboard.terrain;

import java.util.HashMap;
import java.util.Map;

public class TerrainFactory {

    private static TerrainFactory factory;
    private Map<String, TerrainType> terrainMap;

    private static TerrainFactory get() {
        if (factory == null) {
            factory = new TerrainFactory();
        }
        return factory;
    }

    public TerrainFactory() {
        terrainMap = new HashMap<>();
        for (TerrainType terrainType : TerrainType.values()) {
            terrainMap.put(terrainType.getTag(), terrainType);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private Terrain create(Builder builder) {
        switch(terrainMap.get(builder.type)) {
            case CLEAR:
                return new TerrainClearImpl(builder.entryCost);
            case WALL:
                return new TerrainWallImpl(builder.entryCost);
            case WATER:
                return new TerrainWaterImpl(builder.entryCost);
            default:
                return new TerrainClearImpl(1);
        }
    }

    public static class Builder {
        private TerrainFactory factory;
        private String type;
        private long entryCost;

        public Builder() {
            factory = TerrainFactory.get();
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder entryCost(long entryCost) {
            this.entryCost = entryCost;
            return this;
        }

        public Terrain build() {
            return factory.create(this);
        }
    }
}
