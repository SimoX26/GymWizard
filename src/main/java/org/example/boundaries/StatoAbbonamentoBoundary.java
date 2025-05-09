package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class StatoAbbonamentoBoundary {

    @FXML
    private Label statoLabel, dataLabel, tipologiaLabel;

    @FXML
    private void handleBackClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DashboardCliente.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) statoLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHelpClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Stato Abbonamento");
        alert.setContentText("""
                • Stato: può essere Attivo, Scaduto o In Scadenza (entro 10 giorni).
                • Data di emissione: giorno in cui è stato emesso l'abbonamento.
                • Tipologia: tipo di piano (es. Mensile, Trimestrale, Annuale).
                """);
        alert.showAndWait();
    }

    @FXML
    private void handleHomeClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
                Parent homeRoot = loader.load();
                Stage stage = (Stage) statoLabel.getScene().getWindow();
                stage.setScene(new Scene(homeRoot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleRinnovaClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RinnovaAbbonamento.fxml"));
            Parent rinnovaRoot = loader.load();
            Stage stage = (Stage) statoLabel.getScene().getWindow();
            stage.setScene(new Scene(rinnovaRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
