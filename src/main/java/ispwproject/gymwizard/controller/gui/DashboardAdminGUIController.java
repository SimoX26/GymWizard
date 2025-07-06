package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import java.util.logging.Logger;

public class DashboardAdminGUIController extends AbstractGUIController{

    private static final Logger logger = Logger.getLogger(DashboardAdminGUIController.class.getName());

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        logger.info("** INIT EXEC - Dashboard Admin **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardAdminView.fxml");

        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        welcomeLabel.setText("Benvenuto " + u.getUsername());
    }

    @FXML
    public void onListinoAttivitaClick(ActionEvent event){
        switchScene("/views/ListinoAttivitaView.fxml", event);
    }

    @FXML
    public void onReportClick(ActionEvent event){
        switchScene("/views/ReportStatisticheView.fxml", event);
    }

    @FXML
    public void onComunicazioneClick(ActionEvent event){
        switchScene("/views/InviaComunicazioniView.fxml", event);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Dashboard Cliente", """
                In questa schermata puoi:" +
                - Gestire il listino delle attività disponibili" +
                - Visualizzare i report e le statistiche della palestra" +
                - Inviare comunicazioni globali agli utenti" +
                Le icone in alto permettono di tornare indietro (↩), accedere alla guida (?), o effettuare il logout (⌂).
                """);
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
