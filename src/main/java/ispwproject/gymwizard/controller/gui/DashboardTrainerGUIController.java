package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Logger;

public class DashboardTrainerGUIController extends AbstractGUIController{

    private static final Logger logger = Logger.getLogger(DashboardTrainerGUIController.class.getName());

    @FXML
    private Label welcomeLabel;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        logger.info("** INIT EXEC - Dashboard Trainer**");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardTrainerView.fxml");

        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        welcomeLabel.setText("Benvenuto " + u.getUsername());
    }

    @FXML
    private void switchToListaClienti(ActionEvent event) {
        this.switchScene("/views/ListaClientiView.fxml", event);
    }

    @FXML
    public void onChatListBtnClick(ActionEvent chatListEvent) {
        logger.info("CHAT button clicked.");
        this.switchScene("/views/ListaChatView.fxml", chatListEvent);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Dashboard Trainer", "Puoi accedere alla lista dei clienti oppure alla chat con loro.");
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        logger.info("LOGOUT button clicked.");
        if (this.logout()) {
            SessionManager.getInstance().clearAll();
            switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
}
