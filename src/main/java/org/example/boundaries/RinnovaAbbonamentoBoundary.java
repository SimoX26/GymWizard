package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RinnovaAbbonamentoBoundary {

    @FXML
    private void handleBackClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StatoAbbonamento.fxml"));
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
        alert.setHeaderText("Rinnova Abbonamento");
        alert.setContentText("""
                Scegli una delle tipologie disponibili:
                - 10 Ingressi
                - Mensile
                - Trimestrale
                - Annuale
                
                Premi 'Scegli' accanto al tipo desiderato per proseguire.
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

    private void caricaRiepilogo(ActionEvent event, String tipoAbbonamento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RiepilogoOrdine.fxml"));
            Parent root = loader.load();

            // Ottieni il controller e imposta il tipo di abbonamento
            RiepilogoOrdineBoundary controller = loader.getController();
            controller.setTipoAbbonamento(tipoAbbonamento);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleScegli10Ingressi(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento 10 Ingressi");
    }

    @FXML
    private void handleScegliMensile(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Mensile");
    }

    @FXML
    private void handleScegliTrimestrale(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Trimestrale");
    }

    @FXML
    private void handleScegliAnnuale(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Annuale");
    }
}
