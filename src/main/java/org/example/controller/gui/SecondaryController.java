package org.example.controller.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import org.example.controller.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}