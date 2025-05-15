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
    private Label backIcon, helpIcon, homeIcon, titoloAbbonamentoLabel;

    // Questo metodo viene chiamato da GUIRinnovaAbbonamentoController
    public void setTipoAbbonamento(String tipo) {
        titoloAbbonamentoLabel.setText(tipo);
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void handleAcquistaClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acquisto completato");
        alert.setHeaderText(null);
        alert.setContentText("Grazie per il tuo acquisto! Il tuo abbonamento è stato attivato.");
        alert.showAndWait();
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/RinnovaAbbonamentoView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Riepilogo Ordine");
        alert.setContentText("In questa schermata puoi visualizzare il riepilogo del tuo abbonamento selezionato.\n" +
                "                Premi \"Procedi con l'acquisto\" per confermare e completare l'acquisto.");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    private void switchScene(String path, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
