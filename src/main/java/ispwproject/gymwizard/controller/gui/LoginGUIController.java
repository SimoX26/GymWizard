package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.model.Credentials;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.DAO.ConnectionFactory;
import ispwproject.gymwizard.util.exception.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class LoginGUIController extends AbstractGUIController {

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
    private void onLoginBtnClick(ActionEvent mainPageEvent) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Campi obbligatori", "Inserisci email e password.");
            return;
        }

        try {
            // 1. Crea oggetto credenziali
            Credentials rawCred = new Credentials(email, password, null);

            // 2. Chiama il controller applicativo
            LoginController loginController = new LoginController(rawCred);
            Credentials fullCred = loginController.login();

            if (fullCred.getRole() == null) {
                showAlert(AlertType.ERROR, "Login fallito", "Credenziali non valide.");
                return;
            }

            // 3. Cambio connessione in base al ruolo
            ConnectionFactory.changeRole(fullCred.getRole());

            // 4. Salva la sessione
            SessionBean sessionBean = new SessionBean(fullCred.getEmail(), fullCred.getRole());
            SessionManager.getInstance().setSession(sessionBean);

            // 5. Routing in base al ruolo
            switch (fullCred.getRole()) {
                case CLIENTE -> switchScene("/views/DashboardClienteView.fxml", mainPageEvent);
                case TRAINER -> switchScene("/views/DashboardTrainerView.fxml", mainPageEvent);
                case ADMIN -> switchScene("/views/DashboardAdminView.fxml", mainPageEvent);
                default -> showAlert(AlertType.ERROR, "Errore", "Ruolo sconosciuto.");
            }

        } catch (DAOException e) {
            showAlert(AlertType.ERROR, "Errore di accesso al database", e.getMessage());
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Errore imprevisto", e.getMessage());
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


