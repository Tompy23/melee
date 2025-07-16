module com.tompy.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires jdk.xml.dom;

    opens com.tompy.game to javafx.fxml;
    exports com.tompy.game;
    exports com.tompy.game.state;
    opens com.tompy.game.state to javafx.fxml;
}