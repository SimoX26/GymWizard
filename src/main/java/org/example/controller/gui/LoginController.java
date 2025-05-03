package org.example.controller.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // 🔒 Aggiungi qui la logica di validazione (es. database, regex, ecc.)
        if (email.equals("admin@example.com") && password.equals("1234")) {
            // Login riuscito: carica una schermata successiva (es. Dashboard.fxml)
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Accesso effettuato con successo!");
            alert.showAndWait();

            // Puoi caricare una nuova schermata qui, ad esempio:
            /*
            Parent dashboard = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
            Scene scene = new Scene(dashboard);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            */
        } else {
            // Login fallito
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore di accesso");
            alert.setHeaderText(null);
            alert.setContentText("Email o password errati.");
            alert.showAndWait();
        }
    }

    // 🔁 Metodo opzionale per tornare alla schermata iniziale
    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene homeScene = new Scene(homeRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }
}
