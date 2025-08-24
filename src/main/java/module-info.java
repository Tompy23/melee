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
    requires annotations;
    requires java.desktop;

    opens com.tompy.game to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.tompy.game;
    exports com.tompy.game.state;
    opens com.tompy.game.state to javafx.fxml;
    exports com.tompy.hexboard.terrain;
    opens com.tompy.hexboard.terrain to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.tompy.game.play;
    opens com.tompy.game.play to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.tompy.game.state.play;
    opens com.tompy.game.state.play to javafx.fxml;
    exports com.tompy.gladiator;
    opens com.tompy.gladiator to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.tompy.gladiator.details;
    opens com.tompy.gladiator.details to com.fasterxml.jackson.databind, javafx.fxml;
}