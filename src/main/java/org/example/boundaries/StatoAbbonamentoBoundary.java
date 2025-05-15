package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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


    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Stato Abbonamento");
        alert.setContentText("\"\"\"\n" +
                "                • Stato: può essere Attivo, Scaduto o In Scadenza (entro 10 giorni).\n" +
                "                • Data di emissione: giorno in cui è stato emesso l'abbonamento.\n" +
                "                • Tipologia: tipo di piano (es. Mensile, Trimestrale, Annuale).\n" +
                "                \"\"\"");
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
