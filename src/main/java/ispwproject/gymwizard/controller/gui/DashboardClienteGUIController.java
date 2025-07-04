package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class DashboardClienteGUIController extends AbstractGUIController{
    @FXML
    private Label welcomeLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        System.out.println("** INIT EXEC - Dashboard Client **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardClienteView.fxml");

        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        welcomeLabel.setText("Benvenuto " + u.getUsername());
    }

    @FXML
    public void onSchedaBtnClick(ActionEvent trainingCardEvent) {
        System.out.println("SCHEDA ALLENAMENTO button clicked.");
        this.switchScene("/views/VisualizzaSchedaView.fxml", trainingCardEvent);
    }

    @FXML
    public void onAttivitaBtnClick(ActionEvent coursesEvent) {
        System.out.println("COURSES button clicked.");
        this.switchScene("/views/ListinoAttivitaView.fxml", coursesEvent);
    }

    @FXML
    public void onStatoAbbonamentoBtnClick(ActionEvent event) {
        System.out.println("STATO ABBONAMENTO button clicked.");
        this.switchScene("/views/StatoAbbonamentoView.fxml", event);
    }

    @FXML
    public void onChatListBtnClick(ActionEvent event) {
        System.out.println("CHAT button clicked.");
        this.switchScene("/views/ListaChatView.fxml", event);
    }

    @FXML
    public void onCodiceAccessoBtnClick(ActionEvent event) {
        System.out.println("ACCESS CODE button clicked.");
        this.switchScene("/views/CodiceAccessoView.fxml", event);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Dashboard Cliente", "Puoi accedere alle varie funzioni disponibili per un cliente della palestra.");
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        System.out.println("LOGOUT button clicked.");
        if (this.logout()) {
            SessionManager.getInstance().clearAll();
            this.switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
}
