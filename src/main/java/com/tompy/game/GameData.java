package com.tompy.game;

import com.tompy.hexboard.HexBoard;
import com.tompy.hexboard.HexCoordinate;
import com.tompy.hexboard.terrain.Layout;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class GameData {
    private static GameData singleton;
    private final HexBoard hexBoard;
    private final Properties properties;

    private double mouseX;
    private double mouseY;

    private final Pane paneBackImage;
    private final Pane paneHexDecorator;
    private final Pane paneHexBoard;
    private final Pane paneText;

    private GameData(Builder builder) {
        this.properties = Objects.requireNonNull(builder.properties, "Scene Properties cannot be null");
        int pixelSize = Integer.parseInt(properties.getProperty("board.pixel.size"));
        int height = Integer.parseInt(properties.getProperty("board.height"));
        int width = Integer.parseInt(properties.getProperty("board.width"));
        int border = Integer.parseInt(properties.getProperty("board.border"));
        this.hexBoard = HexBoard.builder().pixelSize(pixelSize).height(height).width(width).border(border).gameData(this).layout(builder.layoutMap).build();
        this.paneBackImage = builder.paneBackImage;
        this.paneHexDecorator = builder.paneHexDecorator;
        this.paneHexBoard = builder.paneHexBoard;
        this.paneText = builder.paneText;
        singleton = this;
    }

    public static GameData get() {
        if (singleton != null) {
            return singleton;
        } else {
            throw new RuntimeException("Game Data not initialized");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public HexBoard getHexBoard() {
        return hexBoard;
    }

    public Object getProperty(String name) {
        return properties.get(name);
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public void setMousePointer(double x, double y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    public Pane getPaneBackImage() {
        return paneBackImage;
    }

    public Pane getPaneHexDecorator() {
        return paneHexDecorator;
    }

    public Pane getPaneHexBoard() {
        return paneHexBoard;
    }

    public Pane getPaneText() {
        return paneText;
    }

    public static class Builder {
        private Properties properties;
        private Map<HexCoordinate, Layout> layoutMap;
        private Pane paneBackImage;
        private Pane paneHexDecorator;
        private Pane paneHexBoard;
        private Pane paneText;


        public Builder properties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public Builder layoutMap(Map<HexCoordinate, Layout> layoutMap) {
            this.layoutMap = layoutMap;
            return this;
        }

        public Builder backImage(Pane backImage) {
            this.paneBackImage = backImage;
            return this;
        }

        public Builder hexDecorator(Pane hexDecorator) {
            this.paneHexDecorator = hexDecorator;
            return this;
        }

        public Builder hexBoard(Pane hexBoard) {
            this.paneHexBoard = hexBoard;
            return this;
        }

        public Builder text(Pane text) {
            this.paneText = text;
            return this;
        }

        public void init() {
            new GameData(this);
        }
    }
}
