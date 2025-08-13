package com.tompy.game.play;

public class GamePlayData {
    private static GamePlayData singleton;
    private final GamePlayController controller;

    private GamePlayData(Builder builder) {
        this.controller = builder.controller;
        singleton = this;
    }

    public GamePlayController getController() {
        return controller;
    }

    public static GamePlayData get() {
        if (singleton != null) {
            return singleton;
        } else {
            throw new RuntimeException("Game Play Data not initialized");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GamePlayController controller;

        public Builder controller(GamePlayController controller) {
            this.controller = controller;
            return this;
        }

        public void init() {
            new GamePlayData(this);
        }
    }
}
