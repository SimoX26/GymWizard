package ispwproject.gymwizard.controller.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import ispwproject.gymwizard.util.bean.LoginBean;
import ispwproject.gymwizard.util.bean.SessionBean;
import ispwproject.gymwizard.util.DAO.SessionDAO;

public class LoginBoundary extends AbstractGUIController{

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

        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        SessionDAO sessionDAO = new SessionDAO();
        SessionBean session = sessionDAO.userLogin(loginBean);

        if (session != null) {
            if(session.getRole().equals("cliente")){
                this.switchScene(mainPageEvent,"/views/DashboardClienteView.fxml");
            }else if(session.getRole().equals("personal_trainer")){
                this.switchScene(mainPageEvent,"/views/DashboardTrainerView.fxml");
            }else if(session.getRole().equals("amministratore")){
                this.switchScene(mainPageEvent,"/views/DashboardAdminView.fxml");
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
