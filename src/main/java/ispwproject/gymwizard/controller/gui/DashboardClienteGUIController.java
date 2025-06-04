package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.Optional;

public class DashboardClienteGUIController extends AbstractGUIController{

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
        System.out.println("** INIT EXEC - Dashboard Client **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardClienteView.fxml");

        welcomeLabel.setText("Benvenuto " + "NOME UTENTE");
    }

    @FXML
    public void onSchedaBtnClick(ActionEvent trainingCardEvent) {
        System.out.println("TRAINING CARD button clicked.");
        this.switchScene("/views/SchedaAllenamentoView.fxml", trainingCardEvent);
    }

    @FXML
    public void onAttivitaBtnClick(ActionEvent coursesEvent) {
        System.out.println("COURSES button clicked.");
        this.switchScene("/views/AttivitaView.fxml", coursesEvent);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Coach");
        alert.setContentText("Puoi accedere alla lista dei clienti oppure alla chat con loro.");
        alert.showAndWait();
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        System.out.println("LOGOUT button clicked.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            SessionManager.getInstance().clearAll();
            switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
}
