module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.google.api.client.extensions.java6.auth;

    exports ispwproject.gymwizard.controller;
    exports ispwproject.gymwizard.controller.app;

    opens ispwproject.gymwizard.controller.gui to javafx.fxml;
    exports ispwproject.gymwizard.util;
    opens ispwproject.gymwizard.util to javafx.fxml;
    opens ispwproject.gymwizard.controller.app to javafx.fxml;
    exports ispwproject.gymwizard.util.exception;
    opens ispwproject.gymwizard.util.exception to javafx.fxml;
    opens ispwproject.gymwizard.controller to javafx.fxml;
}
