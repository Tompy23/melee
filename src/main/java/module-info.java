module com.tompy.melee {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.tompy.melee to javafx.fxml;
    exports com.tompy.melee;
    exports com.tompy.melee.state;
    opens com.tompy.melee.state to javafx.fxml;
}