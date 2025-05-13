package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RiepilogoAttivitaBoundary {

    @FXML
    private Label nomeLabel;

    @FXML
    private Label giornoLabel;

    @FXML
    private Label oraLabel;

    @FXML
    private TextArea descrizioneArea;

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    private void initialize() {
        backIcon.setOnMouseClicked(event -> switchScene("/views/CalendarioAttivitaView.fxml"));
        helpIcon.setOnMouseClicked(event -> handleHelpClick());
        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });
    }

    public void setDettagliAttivita(String nome, String giorno, String ora, String descrizione) {
        nomeLabel.setText(nome);
        giornoLabel.setText(giorno);
        oraLabel.setText(ora);
        descrizioneArea.setText(descrizione);
    }

    private void handleHelpClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Come usare la schermata Riepilogo Attività");
        alert.setContentText("""
                Questa schermata mostra i dettagli completi dell’attività selezionata:
                - Nome
                - Giorno
                - Ora
                - Descrizione

                Premi ↩ per tornare alla lista delle attività.
                Premi ⌂ per effettuare il logout.
                """);
        alert.showAndWait();
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensioni fisse
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
