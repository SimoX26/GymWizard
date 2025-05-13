package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class CreaAttivitaBoundary {

    @FXML
    private TextField nomeField;

    @FXML
    private ComboBox<String> giornoComboBox;

    @FXML
    private ComboBox<String> oraComboBox;

    @FXML
    private TextArea descrizioneArea;

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    public void initialize() {
        giornoComboBox.getItems().addAll("Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica");
        oraComboBox.getItems().addAll("9:00", "10:00", "11:00", "16:00", "17:00", "18:00", "19:00", "20:00");

        backIcon.setOnMouseClicked(event -> handleBackClick());
        helpIcon.setOnMouseClicked(event -> handleHelpClick());
        homeIcon.setOnMouseClicked(event -> handleHomeClick());
    }

    @FXML
    private void handleSalva() {
        String nome = nomeField.getText();
        String giorno = giornoComboBox.getValue();
        String ora = oraComboBox.getValue();
        String descrizione = descrizioneArea.getText();

        if (nome == null || giorno == null || ora == null || descrizione == null ||
                nome.isEmpty() || giorno.isEmpty() || ora.isEmpty() || descrizione.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Campi mancanti");
            alert.setHeaderText(null);
            alert.setContentText("Per favore, compila tutti i campi.");
            alert.showAndWait();
            return;
        }

        // Logica per salvare l'attività (es. salvataggio su database o lista)

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Attività salvata");
        alert.setHeaderText(null);
        alert.setContentText("L'attività è stata salvata con successo.");
        alert.showAndWait();

        handleBackClick(); // ritorno alla schermata precedente
    }

    private void handleBackClick() {
        switchScene("/views/CalendarioAttivitaView.fxml");
    }

    private void handleHelpClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Guida");
        alert.setHeaderText(null);
        alert.setContentText("Compila tutti i campi per creare una nuova attività. " +
                "Assicurati di inserire un nome, selezionare un giorno e un'ora, e fornire una descrizione.");
        alert.showAndWait();
    }

    private void handleHomeClick() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            switchScene("/views/HomeView.fxml");
        }
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // imposta dimensioni fisse
            stage.setScene(scene);
            stage.setResizable(false);               // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
