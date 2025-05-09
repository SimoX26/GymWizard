package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import org.example.beans.LoginBean;
import org.example.beans.SessionBean;
import org.example.DAO.SessionDAO;

import java.io.IOException;

public class XLoginController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/Sfondo_home.png").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(
                100, 100, true, true, true, false
        );

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        anchorPane.setBackground(new Background(background));
    }

    @FXML
    private void handleLogin(ActionEvent event) {
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
            String fxmlPath = switch (session.getRole()) {
                case "cliente" -> "/views/DashboardClienteView.fxml";
                case "personal_trainer" -> "/views/DashboardTrainerView.fxml";
                case "amministratore" -> "/views/DashboardAdminView.fxml";
                default -> null;
            };

            if (fxmlPath != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                    Parent dashboard = loader.load();
                    Scene scene = new Scene(dashboard);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setWidth(1000);
                    stage.setHeight(700);
                    stage.setResizable(true);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(AlertType.ERROR, "Errore FXML", "Errore nel caricamento della dashboard.");
                }
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
}
