package com.tompy.hexboard;

import com.tompy.game.counter.Counter;
import com.tompy.game.event.GameFunction;
import com.tompy.game.state.GameStateMachine;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hex {
    private final HexCoordinate coordinate;
    private final Polygon polygon;
    private boolean selected;
    private List<Rectangle> counters;
    private boolean countersStacked;

    private Hex(Builder builder) {
        coordinate = HexCoordinate.builder().setCol(builder.col).setRow(builder.row).build();
        this.polygon = builder.polygon;
        selected = false;
        counters = new ArrayList<>();
        countersStacked = false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Polygon getPolygon() {
        return polygon;
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

    public HexCoordinate getCoordinate() {
        return coordinate;
    }

    public boolean isSelected() {
        return selected;
    }

    public List<Rectangle> getCounters() {
        return Collections.unmodifiableList(counters);
    }

    public boolean isCountersStacked() {
        return countersStacked;
    }

    public void addCounter(Counter newCounter) {
        Rectangle newCounterView = new Rectangle();
        newCounterView.setFill(new ImagePattern(newCounter.getImage()));
        newCounterView.setUserData(newCounter);

        GameStateMachine gsm = GameStateMachine.get();
        newCounterView.setOnMouseEntered(gsm::onMouseEnterCounter);
        newCounterView.setOnMouseExited(gsm::onMouseLeaveCounter);
        newCounterView.setOnMouseClicked(gsm::onMouseClickCounter);

        counters.add(newCounterView);

        newCounter.addToHex(this);

        GameFunction.displayCountersInHex(this);
    }

    public void removeCounter(Counter oldCounter) {
        for (Rectangle counter : counters) {
            if (((Counter) counter.getUserData()).getId() == oldCounter.getId()) {


            }
        }
    }

//    public void displayCounters() {
//        if (!counters.isEmpty()) {
//            if (counters.size() == 1) {
//                ImageView counterImageView = counters.getFirst();
//                Counter counter = (Counter) counterImageView.getUserData();
//
//            } else {
//                if (countersStacked) {
//
//                } else {
//
//                }
//            }
//        }
//    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Hex o)) {
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
        private Polygon polygon;

        public Builder setCol(int col) {
            this.col = col;
            return this;
        }

        public Builder setRow(int row) {
            this.row = row;
            return this;
        }

        public Builder setPolygon(Polygon polygon) {
            this.polygon = polygon;
            return this;
        }

        public Hex build() {
            return new Hex(this);
        }
    }
}
