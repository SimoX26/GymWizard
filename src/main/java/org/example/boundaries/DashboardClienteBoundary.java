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

public class DashboardClienteBoundary {

    @FXML private Label helpIcon, logoutIcon;
    @FXML private Button schedaBtn,attivitaBtn,statoAbbonamentoBtn,chatBtn,codiceAccessoBtn;

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

        schedaBtn.setOnAction(event -> switchScene("/views/SchedaView.fxml"));
        attivitaBtn.setOnAction(event -> switchScene("/views/AttivitaView.fxml"));
        statoAbbonamentoBtn.setOnAction(event -> switchScene("/views/StatoAbbonamentoView.fxml"));
        chatBtn.setOnAction(event -> switchScene("/views/ChatView.fxml"));
        codiceAccessoBtn.setOnAction(event -> switchScene("/views/CodiceAccessoView.fxml"));
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) schedaBtn.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // fissa dimensione
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
