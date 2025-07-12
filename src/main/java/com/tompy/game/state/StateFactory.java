package com.tompy.game.state;

import com.tompy.game.GamePlayController;
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

    private GameHandler create(StateBuilder builder) {
        switch (builder.type) {
            case COMMON:
                return new CommonGameStateImpl();
            case SCENE_CHANGE:
                return new ChangeSceneStateImpl(builder.stage, builder.properties);
            default:
                return null;
        }
    }


    public static class StateBuilder {
        private final StateFactory factory;
        private StateType type;
        private String properties;
        private GamePlayController controller;
        private Stage stage;

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

        public StateBuilder controller(GamePlayController controller) {
            this.controller = controller;
            return this;
        }

        public StateBuilder stage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public GameHandler build() {
            return factory.create(this);
        }
    }
}
