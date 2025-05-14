package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RinnovaAbbonamentoBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    public void initialize() {

        backIcon.setOnMouseClicked(event -> switchScene("/views/StatoAbbonamentoView.fxml"));

        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Rinnova Abbonamento");
            alert.setContentText("""
                  Scegli una delle tipologie disponibili:
                  - 10 Ingressi
                  - Mensile
                  - Trimestrale
                  - Annuale
                """);
            alert.showAndWait();
        });

        homeIcon.setOnMouseClicked(event -> switchScene("/views/DashboardClienteView.fxml"));
    }

    private void caricaRiepilogo(ActionEvent event, String tipoAbbonamento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RiepilogoOrdineView.fxml"));
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
