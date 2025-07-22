package com.tompy.hexboard;

import com.tompy.game.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.game.state.GameStateMachine;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hex extends Polygon {
    private final HexCoordinate coordinate;
    private boolean selected;
    private final List<Counter> counters;
    private boolean countersStacked;
    private final long entryCost;

    private Hex(Builder builder) {
        super(builder.coordinates);
        coordinate = HexCoordinate.builder().setCol(builder.col).setRow(builder.row).build();
        selected = false;
        counters = new ArrayList<>();
        countersStacked = true;
        this.entryCost = builder.entryCost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

    public int getCol() {
        return coordinate.getCol();
    }

    public int getRow() {
        return coordinate.getRow();
    }

    public int getQ() {
        return coordinate.getQ();
    }

    public int getR() {
        return coordinate.getR();
    }

    public int getS() {
        return coordinate.getS();
    }

    public CubeCoordinate getCube() {
        return new CubeCoordinate(getQ(), getR(), getS());
    }

    public long getEntryCost() {
        return entryCost;
    }

    public HexCoordinate getCoordinate() {
        return coordinate;
    }

    public boolean isSelected() {
        return selected;
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

    public void addCounter(Counter newCounter) {

        newCounter.setFill(new ImagePattern(newCounter.getImage()));
        newCounter.setStrokeWidth(6.0);
        newCounter.setStroke(Color.TRANSPARENT);

        GameStateMachine gsm = GameStateMachine.get();
        newCounter.setOnMouseEntered(gsm::onMouseEnterCounter);
        newCounter.setOnMouseExited(gsm::onMouseLeaveCounter);
        newCounter.setOnMouseClicked(gsm::onClickCounter);

        addExistingCounter(newCounter);
    }

    public void addExistingCounter(Counter counter) {
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
        return coordinate.hashCode();
    }

    public static final class Builder {
        private int col;
        private int row;
        private double[] coordinates;
        private long entryCost = 1;

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

        public Builder entryCost(long entryCost) {
            this.entryCost = entryCost;
            return this;
        }

        public Hex build() {
            return new Hex(this);
        }
    }
}
