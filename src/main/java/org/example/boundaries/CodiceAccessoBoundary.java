package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CodiceAccessoBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    public void initialize() {

        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardClienteView.fxml"));

        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Codice D'Accesso");
            alert.setContentText("Utilizza questo codice QR per accedere alla struttura.");
            alert.showAndWait();
        });

        homeIcon.setOnMouseClicked(event -> switchScene("/views/DashboardClienteView.fxml"));
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensione fissa
            stage.setScene(scene);
            stage.setResizable(false);              // blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
