module ispwproject.gymwizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    exports ispwproject.gymwizard.controller;
    exports ispwproject.gymwizard.controller.app;
    exports ispwproject.gymwizard.util.DAO;
    exports ispwproject.gymwizard.util.exception;

    opens ispwproject.gymwizard.controller to javafx.fxml;
    opens ispwproject.gymwizard.controller.gui to javafx.fxml;
    opens ispwproject.gymwizard.controller.app to javafx.fxml;
    opens ispwproject.gymwizard.util.exception to javafx.fxml;
}
