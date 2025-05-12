package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.Optional;

public class InviaComunicazioniBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    private TextArea areaMessaggi;

    @FXML
    private TextField inputMessaggio;

    @FXML
    private Button sendBtn, plusBtn;

    @FXML
    public void initialize() {
        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardAdminView.fxml"));
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

    @FXML
    private void handleSendMessage() {
        String messaggio = inputMessaggio.getText().trim();

        if (messaggio.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Messaggio vuoto");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un messaggio da inviare.");
            alert.showAndWait();
            return;
        }

        areaMessaggi.appendText("Admin: " + messaggio + "\n");
        inputMessaggio.clear();

        Alert conferma = new Alert(AlertType.INFORMATION);
        conferma.setTitle("Messaggio inviato");
        conferma.setHeaderText(null);
        conferma.setContentText("Il messaggio è stato inviato con successo.");
        conferma.showAndWait();
    }

    private void handleHelpClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Comunicazioni");
        alert.setContentText("""
                - Scrivi il tuo messaggio nel campo in basso.
                - Premi ↪ per inviare il messaggio a tutti gli utenti.
                - Premi + per eventuali azioni aggiuntive (non ancora attive).
                - Usa la freccia ↩ per tornare alla dashboard.
                - Usa la casetta ⌂ per effettuare il logout.
                """);
        alert.showAndWait();
    }

    private void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);  // dimensioni fisse
            stage.setScene(scene);
            stage.setResizable(false);               // blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
