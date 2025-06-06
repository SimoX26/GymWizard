package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.Optional;

public class DashboardAdminGUIController extends AbstractGUIController{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        System.out.println("** INIT EXEC - Dashboard Admin **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardAdminView.fxml");

        welcomeLabel.setText("Benvenuto " + "NOME UTENTE");
    }

    @FXML
    public void onListinoAttivitaClick(ActionEvent event){
        switchScene("/views/AttivitaView.fxml", event);
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
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Admin");
        alert.setContentText("""
                In questa schermata puoi:" +
                - Gestire il listino delle attività disponibili" +
                - Visualizzare i report e le statistiche della palestra" +
                - Inviare comunicazioni globali agli utenti" +
                Le icone in alto permettono di tornare indietro (↩), accedere alla guida (?), o effettuare il logout (⌂).
                """);
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
