package com.tompy.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tompy.hexboard.terrain.LayoutDescription;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractSceneLoader {

    protected void handleHexBoard(GameHexBoardController gpc, Stage stage, Properties sceneProperties) {
        stage.setTitle(sceneProperties.getProperty(GameConstants.TITLE));

        LayoutDescription layoutDescription = null;
        if (sceneProperties.containsKey(GameConstants.BOARD_LAYOUT)) {
            InputStream boardLayoutStream = getFileFromResourceAsStream(
                    sceneProperties.getProperty(GameConstants.BOARD_LAYOUT));
            ObjectMapper mapper = new ObjectMapper();

            try {
                layoutDescription = mapper.readValue(boardLayoutStream, LayoutDescription.class);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                throw new RuntimeException("No board layout");
            }
        }

        GameHexBoardData.builder().properties(sceneProperties)
                .layoutMap(layoutDescription == null ? null : layoutDescription.getLayouts())
                .hexBoard(gpc.getHexBoardPane()).text(gpc.getTextPane()).init();

        if (sceneProperties.containsKey(GameConstants.BOARD_LAYOUT)) {
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
