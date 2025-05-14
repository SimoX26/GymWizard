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
import java.util.Optional;

public class DashboardTrainerBoundary {

    @FXML private Label helpIcon, logoutIcon;
    @FXML private Button listaClientiBtn, chatBtn;

    @FXML
    private void initialize() {

        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Dashboard Coach");
            alert.setContentText("Puoi accedere alla lista dei clienti oppure alla chat con loro.");
            alert.showAndWait();
        });

        logoutIcon.setOnMouseClicked( event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });

        listaClientiBtn.setOnAction(event -> switchScene("/views/ListaClientiView.fxml"));

        chatBtn.setOnAction(event -> switchScene("/views/ListaChatView.fxml"));
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) listaClientiBtn.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // fissa dimensione
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
