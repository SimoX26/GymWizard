module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.google.api.client.extensions.java6.auth;

    exports org.example;
    exports org.example.controllers;

    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.boundaries to javafx.fxml;
}
