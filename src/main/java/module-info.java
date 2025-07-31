module ispwproject.gymwizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    requires com.google.zxing.javase;
    requires com.google.zxing;
    requires com.google.gson;
    requires java.sql;
    requires jcommander;

    exports ispwproject.gymwizard.controller;
    exports ispwproject.gymwizard.controller.app;
    exports ispwproject.gymwizard.model;
    exports ispwproject.gymwizard.view;
    exports ispwproject.gymwizard.util.dao;
    exports ispwproject.gymwizard.util.exception;
    exports ispwproject.gymwizard.util.bean;

    opens ispwproject.gymwizard.controller to javafx.fxml;
    opens ispwproject.gymwizard.controller.gui to javafx.fxml;
    opens ispwproject.gymwizard.controller.app to javafx.fxml;
    opens ispwproject.gymwizard.util.exception to javafx.fxml;
}
