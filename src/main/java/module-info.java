module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.google.api.client.extensions.java6.auth;

    exports ispwproject.gymwizard;
    exports ispwproject.gymwizard.controllers.app;

    opens ispwproject.gymwizard to javafx.fxml;
    opens ispwproject.gymwizard.controllers.gui to javafx.fxml;
    exports ispwproject.gymwizard.utils;
    opens ispwproject.gymwizard.utils to javafx.fxml;
    opens ispwproject.gymwizard.controllers.app to javafx.fxml;
}
