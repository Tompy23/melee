package com.tompy.game;

import com.tompy.hexboard.Hex;
import com.tompy.hexboard.HexBoard;

import java.util.Objects;
import java.util.Properties;

public class GameData {
    private static GameData gameDataSingleton;
    private final GamePlayController controller;
    private final HexBoard hexBoard;
    private final Properties properties;
    private Hex hexWithMouse;

    private GameData(Builder builder) {
        this.controller = Objects.requireNonNull(builder.controller, "Game Play Controller cannot be null.");
        this.properties = Objects.requireNonNull(builder.properties, "Scene Properties cannot be null");
        int pixelSize = Integer.parseInt(properties.getProperty("board.pixel.size"));
        int height = Integer.parseInt(properties.getProperty("board.height"));
        int width = Integer.parseInt(properties.getProperty("board.width"));
        int border = Integer.parseInt(properties.getProperty("board.border"));
        this.hexBoard = HexBoard.builder().pixelSize(pixelSize).height(height).width(width).border(border).build();
        gameDataSingleton = this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static GameData get() {
        if (gameDataSingleton == null) {
            throw new RuntimeException("Game Data is null");
        } else {
            return gameDataSingleton;
        }
    }

    public HexBoard getHexBoard() {
        return hexBoard;
    }

    public Object getProperty(String name) {
        return properties.get(name);
    }

    public void setHexWithMouse(Hex hex) {
        hexWithMouse = hex;
    }

    public Hex getHexWithMouse() {
        return hexWithMouse;
    }

    public static class Builder {
        private GamePlayController controller;
        private Properties properties;

        public Builder controller(GamePlayController controller) {
            this.controller = controller;
            return this;
        }

        public Builder properties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public void init() {
            new GameData(this);
        }
    }
}
