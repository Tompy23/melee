package com.tompy.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tompy.hexboard.Hex;
import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GamePlaySceneLoader {

    public void loadScene(Stage stage, String propertiesFilename) throws IOException {
        Properties sceneProperties = new Properties();
        sceneProperties.load(getFileFromResourceAsStream(propertiesFilename));

        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource(sceneProperties.getProperty("fxml")));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(sceneProperties.getProperty("title"));
        stage.setScene(scene);
        stage.show();

        LayoutDescription layoutDescription = null;
        if (sceneProperties.containsKey("board.layout")) {
            InputStream boardLayoutStream = getFileFromResourceAsStream(sceneProperties.getProperty("board.layout"));
            ObjectMapper mapper = new ObjectMapper();

            try {
                layoutDescription = (LayoutDescription) mapper.readValue(boardLayoutStream, LayoutDescription.class);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                throw new RuntimeException("Now board layout");
            }
        }

        GamePlayController gpc = fxmlLoader.getController();
        gpc.setStage(stage);
        GameData.builder().controller(gpc).properties(sceneProperties).layoutMap(layoutDescription == null ? null : layoutDescription.getLayouts()).init();

        if (sceneProperties.containsKey("board.layout")) {
            gpc.drawHexBoardWithLayout();
        } else {
            gpc.drawHexBoard();
        }
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
