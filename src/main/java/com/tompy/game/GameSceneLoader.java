package com.tompy.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tompy.game.play.GamePlayApplication;
import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameSceneLoader {

    public FXMLLoader loadScene(Stage stage, String propertiesFilename, SceneLoader sceneLoader) throws IOException {
        Properties sceneProperties = new Properties();
        sceneProperties.load(getFileFromResourceAsStream(propertiesFilename));

        FXMLLoader fxmlLoader = new FXMLLoader(
                GamePlayApplication.class.getResource(sceneProperties.getProperty(GameConstants.FXML)));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(sceneProperties.getProperty(GameConstants.TITLE));
        stage.setScene(scene);
        stage.show();

        LayoutDescription layoutDescription = null;
        if (sceneProperties.containsKey(GameConstants.BOARD_LAYOUT)) {
            InputStream boardLayoutStream = getFileFromResourceAsStream(
                    sceneProperties.getProperty(GameConstants.BOARD_LAYOUT));
            ObjectMapper mapper = new ObjectMapper();

            try {
                layoutDescription = (LayoutDescription) mapper.readValue(boardLayoutStream, LayoutDescription.class);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                throw new RuntimeException("No board layout");
            }
        }

        GameController gpc = sceneLoader.loadSceneController(fxmlLoader, stage, sceneProperties, layoutDescription);
        GameData.builder().properties(sceneProperties)
                .layoutMap(layoutDescription == null ? null : layoutDescription.getLayouts())
                .hexBoard(gpc.getHexBoardPane()).text(gpc.getTextPane()).init();

        if (sceneProperties.containsKey(GameConstants.BOARD_LAYOUT)) {
            gpc.drawHexBoardWithLayout();
        } else {
            gpc.drawHexBoard();
        }

        return fxmlLoader;
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
