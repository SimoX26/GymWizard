package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class DashboardAdminGUIController extends AbstractGUIController{

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
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
    public void onHelpClick(ActionEvent helpEvent) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Admin");
        alert.setContentText("""
                In questa schermata puoi:\n" +
                - Gestire il listino delle attività disponibili\n" +
                - Visualizzare i report e le statistiche della palestra\n" +
                - Inviare comunicazioni globali agli utenti\n" +
                Le icone in alto permettono di tornare indietro (↩), accedere alla guida (?), o effettuare il logout (⌂).\n
                """);
        alert.showAndWait();
    }

    @FXML
    public void onLogoutClick(ActionEvent homeEvent) {
        System.out.println("🚪 Logout in corso...");
        SessionManager.getInstance().clearAll();
        System.out.println("✅ Logout effettuato. Ritorno al menu principale.");
    }
}
