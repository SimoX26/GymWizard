package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.util.exception.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class LoginGUIController extends AbstractGUIController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/images/Sfondo_home.png")).toExternalForm());

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
    private void onLoginBtnClick(ActionEvent mainPageEvent) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            this.showError("Campi obbligatori", "Inserisci email e password.");
            return;
        }

        try {
            LoginController loginController = new LoginController();
            LoginController.LoginResult result = loginController.login(email, password);

            switch (result) {
                case SUCCESSO_CLIENTE -> switchScene("/views/DashboardClienteView.fxml", mainPageEvent);
                case SUCCESSO_TRAINER -> switchScene("/views/DashboardTrainerView.fxml", mainPageEvent);
                case SUCCESSO_ADMIN -> switchScene("/views/DashboardAdminView.fxml", mainPageEvent);
                case CREDENZIALI_INVALIDE -> this.showError("Login fallito", "Credenziali non valide.");
                case ERRORE -> this.showError("Errore", "Errore sconosciuto durante il login.");
            }
        } catch (DAOException e) {
            this.showError("Errore DB", e.getMessage());
        }
    }
}


