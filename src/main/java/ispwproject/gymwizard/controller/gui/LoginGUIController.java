package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.LoginController;
import ispwproject.gymwizard.util.exception.CredenzialiException;
import ispwproject.gymwizard.util.exception.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        anchorPane.setBackground(new Background(this.background()));
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
            }

        } catch (CredenzialiException e) {
            this.showPopup("Login fallito", "Credenziali non valide", e.getMessage());

        } catch (DAOException e) {
            this.showError("Errore DB", e.getMessage());

        } catch (Exception e) {
            this.showError("Errore", "Errore imprevisto: " + e.getMessage());
        }
    }
}


