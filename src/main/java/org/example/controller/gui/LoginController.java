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

import org.example.engineeringclasses.bean.LoginBean;
import org.example.engineeringclasses.bean.SessionBean;
import org.example.engineeringclasses.dao.db.SessionDAO;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Campi obbligatori", "Inserisci email e password.");
            return;
        }

        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        SessionDAO sessionDAO = new SessionDAO();
        SessionBean session = sessionDAO.userLogin(loginBean);

        if (session != null) {
            showAlert(AlertType.INFORMATION, "Login riuscito", "Benvenuto " + session.getUsername() + " - Ruolo: " + session.getRole());

            String fxmlPath = switch (session.getRole()) {
                case "cliente" -> "/view/DashboardCliente.fxml";
                case "personal_trainer" -> "/view/DashboardTrainer.fxml";
                case "amministratore" -> "/view/DashboardAdmin.fxml";
                default -> null;
            };

            if (fxmlPath != null) {
                Parent dashboard = FXMLLoader.load(getClass().getResource(fxmlPath));
                Scene scene = new Scene(dashboard);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert(AlertType.ERROR, "Errore", "Ruolo utente non riconosciuto.");
            }

        } else {
            showAlert(AlertType.ERROR, "Errore di accesso", "Email o password errati.");
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void goBackToHome(ActionEvent event) throws IOException {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        Scene homeScene = new Scene(homeRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }
}
