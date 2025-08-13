package com.tompy.game.state;

import com.tompy.game.state.play.*;
import com.tompy.hexboard.Hex;
import javafx.stage.Stage;

public class GameStateFactory {
    private static GameStateFactory singletonGameStateFactory;

    private GameStateFactory() {

    }

    private static GameStateFactory get() {
        if (singletonGameStateFactory == null) {
            singletonGameStateFactory = new GameStateFactory();
        }
        return singletonGameStateFactory;
    }

    public static GameStateBuilder buidler() {
        return new GameStateBuilder();
    }

    private GameState create(GameStateBuilder builder) {
        switch (builder.type) {
            case COMMON:
                return new CommonGameStateImpl();
            case SCENE_CHANGE:
                return new ChangeSceneStateImpl(builder.stage, builder.properties);
            case MOVE_1:
                return new Move1StateImpl(builder.originHex);
            case TEST_POINTER:
                return new TestHexPointerState();
            case DRAW_LINE:
                return new DrawLineGameStateImpl();
            case FIND_PATH:
                return new PathGameStateImpl();
            default:
                return null;
        }
    }

    public static class GameStateBuilder {
        private final GameStateFactory factory;
        private GameStateType type;
        private String properties;
        private Stage stage;
        private Hex originHex;

        public GameStateBuilder() {
            this.factory = GameStateFactory.get();
        }

        public GameStateBuilder type(GameStateType type) {
            this.type = type;
            return this;
        }

        public GameStateBuilder properties(String properties) {
            this.properties = properties;
            return this;
        }

        public GameStateBuilder stage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public GameStateBuilder originHex(Hex originHex) {
            this.originHex = originHex;
            return this;
        }

        public GameState build() {
            return factory.create(this);
        }
    }
}
