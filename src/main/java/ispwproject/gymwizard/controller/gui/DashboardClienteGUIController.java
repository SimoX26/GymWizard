package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import java.util.logging.Logger;

public class DashboardClienteGUIController extends AbstractGUIController{

    private static final Logger logger = Logger.getLogger(DashboardClienteGUIController.class.getName());

    @FXML
    private Label welcomeLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        logger.info("** INIT EXEC - Dashboard Client **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardClienteView.fxml");

        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        welcomeLabel.setText("Benvenuto " + u.getUsername());
    }

    @FXML
    public void onSchedaBtnClick(ActionEvent trainingCardEvent) {
        logger.info("SCHEDA ALLENAMENTO button clicked.");
        this.switchScene("/views/VisualizzaSchedaView.fxml", trainingCardEvent);
    }

    @FXML
    public void onAttivitaBtnClick(ActionEvent coursesEvent) {
        logger.info("COURSES button clicked.");
        this.switchScene("/views/ListinoAttivitaView.fxml", coursesEvent);
    }

    @FXML
    public void onStatoAbbonamentoBtnClick(ActionEvent event) {
        logger.info("STATO ABBONAMENTO button clicked.");
        this.switchScene("/views/StatoAbbonamentoView.fxml", event);
    }

    @FXML
    public void onChatListBtnClick(ActionEvent event) {
        logger.info("CHAT button clicked.");
        this.switchScene("/views/ListaChatView.fxml", event);
    }

    @FXML
    public void onCodiceAccessoBtnClick(ActionEvent event) {
        logger.info("ACCESS CODE button clicked.");
        this.switchScene("/views/CodiceAccessoView.fxml", event);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Dashboard Cliente", "Puoi accedere alle varie funzioni disponibili per un cliente della palestra.");
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        logger.info("LOGOUT button clicked.");
        if (this.logout()) {
            SessionManager.getInstance().clearAll();
            this.switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
}
