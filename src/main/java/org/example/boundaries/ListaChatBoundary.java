package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ListaChatBoundary {

    @FXML private Label backIcon, helpIcon, homeIcon;
    @FXML private VBox chatListVBox;

    @FXML
    public void initialize() {
        // Esempio mock utenti
        for (int i = 1; i <= 5; i++) {
            Label utente = new Label("🗨️ Chat con Utente " + i);
            utente.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Comic Sans MS'; -fx-cursor: hand; -fx-padding: 8 10; -fx-background-color: #444; -fx-background-radius: 8;");
            int finalI = i;
            utente.setOnMouseClicked(event -> apriChat(finalI));
            chatListVBox.getChildren().add(utente);
        }

        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardTrainerView.fxml"));

        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Lista Chat");
            alert.setContentText("Clicca su una chat per visualizzare la conversazione.");
            alert.showAndWait();
        });

        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });
    }

    private void apriChat(int idUtente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ChatView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) chatListVBox.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensioni fisse
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
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
