package com.tompy.game.state;

import com.tompy.hexboard.Hex;
import javafx.stage.Stage;

public class StateFactory {
    private static StateFactory singletonStateFactory;

    public static StateFactory get() {
        if (singletonStateFactory == null) {
            singletonStateFactory = new StateFactory();
        }
        return singletonStateFactory;
    }

    public StateBuilder buidler() {
        return new StateBuilder(this);
    }

    private GameState create(StateBuilder builder) {
        switch (builder.type) {
            case COMMON:
                return new CommonGameStateImpl();
            case SCENE_CHANGE:
                return new ChangeSceneStateImpl(builder.stage, builder.properties);
            case MOVE_1:
                return new Move1StateImpl(builder.originHex);
            case TEST_POINTER:
                return new TestHexPointerState();
            default:
                return null;
        }
    }

    public static class StateBuilder {
        private final StateFactory factory;
        private StateType type;
        private String properties;
        private Stage stage;
        private Hex originHex;

        public StateBuilder(StateFactory factory) {
            this.factory = factory;
        }

        public StateBuilder type(StateType type) {
            this.type = type;
            return this;
        }

        public StateBuilder properties(String properties) {
            this.properties = properties;
            return this;
        }

        public StateBuilder stage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public StateBuilder originHex(Hex originHex) {
            this.originHex = originHex;
            return this;
        }

        public GameState build() {
            return factory.create(this);
        }
    }
}
