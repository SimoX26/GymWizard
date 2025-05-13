package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardClienteBoundary {

    @FXML private Button Btn;


    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) Btn.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // fissa dimensione
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
