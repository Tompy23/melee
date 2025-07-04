package com.tompy.melee;

import com.tompy.melee.state.MeleeStateMachine;
import com.tompy.state.StateMachine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GamePlaySceneLoader {

    public void loadScene(Stage stage, MeleeStateMachine stateMachine, String propertiesFilename) throws IOException {
        Properties sceneProperties = new Properties();
        sceneProperties.load(getFileFromResourceAsStream(propertiesFilename));

        FXMLLoader fxmlLoader = new FXMLLoader(MeleeApplication.class.getResource(sceneProperties.getProperty("fxml")));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(sceneProperties.getProperty("title"));
        stage.setScene(scene);
        stage.show();

        GamePlayController gpc = fxmlLoader.getController();
        gpc.setStage(stage);
        gpc.setStateMachine(stateMachine);
        gpc.setProperties(sceneProperties);
        gpc.showGrid();
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
