package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RiepilogoOrdineBoundary {

    @FXML
    private Label titoloAbbonamentoLabel;

    // Questo metodo viene chiamato da GUIRinnovaAbbonamentoController
    public void setTipoAbbonamento(String tipo) {
        titoloAbbonamentoLabel.setText(tipo);
    }

    @FXML
    private void handleBackClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RinnovaAbbonamento.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHelpClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Riepilogo Ordine");
        alert.setContentText("""
                In questa schermata puoi visualizzare il riepilogo del tuo abbonamento selezionato.
                Premi "Procedi con l'acquisto" per confermare e completare l'acquisto.
                """);
        alert.showAndWait();
    }

    @FXML
    private void handleHomeClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleAcquistaClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acquisto completato");
        alert.setHeaderText(null);
        alert.setContentText("Grazie per il tuo acquisto! Il tuo abbonamento è stato attivato.");
        alert.showAndWait();
    }
}
