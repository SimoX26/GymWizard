package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void initialize() {
    }

    public void setDettagliAttivita(String nome, String giorno, String ora, String descrizione) {
        nomeLabel.setText(nome);
        giornoLabel.setText(giorno);
        oraLabel.setText(ora);
        descrizioneArea.setText(descrizione);
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Codice D'Accesso");
        alert.setContentText("Utilizza questo codice QR per accedere alla struttura.");
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
