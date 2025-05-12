package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashboardAdminBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    private Button listinoBtn, reportBtn, comunicazioniBtn;

    @FXML
    private void initialize() {
        // Torna alla schermata iniziale
        backIcon.setOnMouseClicked(event -> switchScene("/views/Home.fxml", event));

        // Guida interfaccia
        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Dashboard Amministratore");
            alert.setContentText("""
                In questa schermata puoi:
                - Gestire il listino delle attività disponibili
                - Visualizzare i report e le statistiche della palestra
                - Inviare comunicazioni globali agli utenti
                Le icone in alto permettono di tornare indietro (↩), accedere alla guida (?), o effettuare il logout (⌂).
            """);
            alert.showAndWait();
        });

        // Logout con conferma
        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml", event);
            }
        });

        // Azioni pulsanti
        listinoBtn.setOnAction(event -> switchScene("/views/ListinoAttivitaView.fxml", event));
        reportBtn.setOnAction(event -> switchScene("/views/ReportStatisticheView.fxml", event));
        comunicazioniBtn.setOnAction(event -> switchScene("/views/InviaComunicazioniView.fxml", event));
    }

    private void switchScene(String fxmlPath, javafx.event.Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);  // Fissa la dimensione
            stage.setScene(scene);
            stage.setResizable(false);               // Blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
