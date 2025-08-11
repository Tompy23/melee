package com.tompy.hexboard.terrain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Layout {
    @JsonProperty
    private long x;
    @JsonProperty
    private long y;
    @JsonProperty
    private String terrain;
    @JsonProperty
    private long entry;

    public Layout() {

    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public String getTerrain() {
        return terrain;
    }

    public long getEntry() {
        return entry;
    }
}
