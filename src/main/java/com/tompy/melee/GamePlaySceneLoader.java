package com.tompy.melee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePlaySceneLoader {

    public void begin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MeleeApplication.class.getResource("game-play.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Melee!");
        stage.setScene(scene);
        stage.show();

        GamePlayController gpc = fxmlLoader.getController();
        gpc.showGrid();
    }
}
