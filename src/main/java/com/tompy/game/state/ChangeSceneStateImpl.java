package com.tompy.game.state;

import com.tompy.game.GameConstants;
import com.tompy.game.GameFxmlLoader;
import com.tompy.game.SceneLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ChangeSceneStateImpl extends AbstractGameState {
    protected final Stage stage;
    protected final String scenePropertiesName;
    protected final GameStateType type;
    protected final SceneLoader sceneLoader;

    public ChangeSceneStateImpl(Stage stage, String scenePropertiesName, GameStateType type, SceneLoader sceneLoader) {
        this.stage = stage;
        this.scenePropertiesName = scenePropertiesName;
        this.type = type;
        this.sceneLoader = sceneLoader;
    }

    @Override
    public void beginState() {
        GameFxmlLoader gameFxmlLoader = new GameFxmlLoader();
        try {
            Properties sceneProperties = new Properties();
            sceneProperties.load(getFileFromResourceAsStream(scenePropertiesName));
            gameFxmlLoader.loadFxml(stage, sceneProperties, sceneLoader,
                    GameConstants.class.getResource(sceneProperties.getProperty(GameConstants.FXML)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void process(long l) {
        GameStateMachine.get().changeState(GameStateFactory.buidler().type(type).build());
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
}
