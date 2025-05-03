package org.example.controller.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import org.example.controller.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
