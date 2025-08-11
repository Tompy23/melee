package com.tompy.hexboard;

import com.tompy.counter.Counter;
import com.tompy.game.GameData;
import com.tompy.game.PaneCoordinates;
import com.tompy.game.event.GameFunction;
import com.tompy.hexboard.state.HexState;
import com.tompy.hexboard.terrain.Terrain;
import com.tompy.state.StateMachine;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hex extends Polygon implements StateMachine<HexState> {
    private final HexCoordinate coordinate;
    private final double[] polygonCoordinates;
    private final List<Counter> counters;
    private final long entryCost;
    private final PaneCoordinates paneCoordinates;
    private final GameData gameData;
    private boolean countersStacked;
    private HexState currentState;
    private Terrain terrain;

    private Hex(Builder builder) {
        super(builder.coordinates);
        polygonCoordinates = builder.coordinates;
        coordinate = HexCoordinate.builder().setCol(builder.col).setRow(builder.row).build();
        counters = new ArrayList<>();
        countersStacked = true;
        this.entryCost = builder.entryCost;
        this.paneCoordinates = builder.paneCoordinates;
        this.gameData = builder.gameData;
        this.terrain = builder.terrain;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PaneCoordinates getPaneCoordinates() {
        return new PaneCoordinates(paneCoordinates.getX() * gameData.getZoom(),
                paneCoordinates.getY() * gameData.getZoom());
    }

    public long getCol() {
        return coordinate.getCol();
    }

    public long getRow() {
        return coordinate.getRow();
    }

    public long getQ() {
        return coordinate.getQ();
    }

    public long getR() {
        return coordinate.getR();
    }

    public long getS() {
        return coordinate.getS();
    }

    public CubeCoordinate getCube() {
        return new CubeCoordinate(getQ(), getR(), getS());
    }

    public long getEntryCost() {
        return terrain.getEntryCost();
    }

    public HexCoordinate getCoordinate() {
        return coordinate;
    }

    public boolean isSelected() {
        return currentState.isSelected();
    }

    public List<Counter> getCounters() {
        return Collections.unmodifiableList(counters);
    }

    public void stackCounters() {
        countersStacked = true;
    }

    public void unstackCounters() {
        countersStacked = false;
    }

    public boolean isCountersStacked() {
        return countersStacked;
    }

    public void addCounter(Counter counter) {
        counters.add(counter);

        counter.addToHex(this);

        GameFunction.displayCountersInHex(this);
    }

    public void removeCounter(Counter oldCounter) {
        List<Counter> countersToRemove = new ArrayList<>();
        for (Counter counter : counters) {

            if (counter.getId().equals(oldCounter.getId())) {
                countersToRemove.add(counter);
            }
        }
        counters.removeAll(countersToRemove);

        GameFunction.displayCountersInHex(this);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Polygon fillTerrain() {
        Polygon p = new Polygon(polygonCoordinates);
        p.setOpacity(1.0);
        p.setFill(terrain.fill());
        return p;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Hex o)) {
            return false;
        }
        if (this == other) {
            return true;
        }
        return this.coordinate.equals(o.coordinate);
    }

    @Override
    public int hashCode() {
        return (int) coordinate.hashCode();
    }

    @Override
    public void changeState(HexState newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
        if (currentState != null) {
            currentState.beginState();
        }
    }

    @Override
    public void continueState(HexState newState) {
        if (currentState != null) {
            currentState.endState();
        }
        currentState = newState;
    }

    @Override
    public HexState getCurrentState() {
        return currentState;
    }

    @Override
    public void process(long l) {
        if (currentState != null) {
            currentState.process(l);
        }
        counters.forEach(c -> c.process(l));
    }

    @Override
    public boolean stopThread() {
        return false;
    }

    public void handleClick(MouseEvent event) {
        currentState.handleClick();
    }

    public void handleEnter(MouseEvent event) {
        currentState.handleEnter();
    }

    public void handleExit(MouseEvent event) {
        currentState.handleExit();
    }

    public static final class Builder {
        private int col;
        private int row;
        private double[] coordinates;
        private PaneCoordinates paneCoordinates;
        private GameData gameData;
        private long entryCost = 1;
        private Terrain terrain;

        public Builder setCol(int col) {
            this.col = col;
            return this;
        }

        public Builder setRow(int row) {
            this.row = row;
            return this;
        }

        public Builder coordinates(double[] coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder paneCoordinates(PaneCoordinates paneCoordinates) {
            this.paneCoordinates = paneCoordinates;
            return this;
        }

        public Builder gameData(GameData gameData) {
            this.gameData = gameData;
            return this;
        }

        public Builder entryCost(long entryCost) {
            this.entryCost = entryCost;
            return this;
        }

        public Builder terrain(Terrain terrain) {
            this.terrain = terrain;
            return this;
        }

        public Hex build() {
            return new Hex(this);
        }
    }
}
