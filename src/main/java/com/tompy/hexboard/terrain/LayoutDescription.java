package com.tompy.hexboard.terrain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tompy.hexboard.HexCoordinate;

import java.util.HashMap;
import java.util.Map;

public class LayoutDescription {
    @JsonProperty
    private Layout[] layouts;

    public LayoutDescription() {

    }

    public Map<HexCoordinate, Layout> getLayouts() {
        Map<HexCoordinate, Layout> layoutMap = new HashMap<>();
        for (int i = 0; i < layouts.length; i++) {
            layoutMap.put(HexCoordinate.builder().setCol(layouts[i].getX()).setRow(layouts[i].getY()).build(), layouts[i]);
        }
        return layoutMap;
    }
}
