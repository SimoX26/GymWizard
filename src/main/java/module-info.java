module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    // Necessari per reflection nei controller
    opens org.example.controller.gui to javafx.fxml;
    opens org.example.controller to javafx.graphics;

    // Esportazione dei pacchetti
    exports org.example.controller;
    exports org.example.controller.gui;
}
