module ispwproject.gymwizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.net.http;
    requires com.google.zxing.javase;
    requires com.google.zxing;

    exports ispwproject.gymwizard.controller;
    exports ispwproject.gymwizard.controller.app;
    exports ispwproject.gymwizard.model;
    exports ispwproject.gymwizard.view;
    exports ispwproject.gymwizard.util.DAO;
    exports ispwproject.gymwizard.util.exception;
    exports ispwproject.gymwizard.util.bean;

    opens ispwproject.gymwizard.controller to javafx.fxml;
    opens ispwproject.gymwizard.controller.gui to javafx.fxml;
    opens ispwproject.gymwizard.controller.app to javafx.fxml;
    opens ispwproject.gymwizard.util.exception to javafx.fxml;
}
