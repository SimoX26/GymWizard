package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class InviaComunicazioniGUIController extends AbstractGUIController{

    String homePage = (String) SessionManager.getInstance().getAttributo("homePage");

    @FXML
    private TextArea areaMessaggi;

    @FXML
    private TextField inputMessaggio;

    @FXML
    private Button sendBtn, plusBtn;

    @FXML
    private void handleSendMessage() {
        String messaggio = inputMessaggio.getText().trim();

        if (messaggio.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Messaggio vuoto");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci un messaggio da inviare.");
            alert.showAndWait();
            return;
        }

        areaMessaggi.appendText("Admin: " + messaggio + "\n");
        inputMessaggio.clear();

        Alert conferma = new Alert(AlertType.INFORMATION);
        conferma.setTitle("Messaggio inviato");
        conferma.setHeaderText(null);
        conferma.setContentText("Il messaggio è stato inviato con successo.");
        conferma.showAndWait();
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        this.switchScene(homePage,event);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Inerfaccia", "Comunicazioni", """
                - Scrivi il tuo messaggio nel campo in basso.
                - Premi ↪ per inviare il messaggio a tutti gli utenti.
                - Premi + per eventuali azioni aggiuntive (non ancora attive).
                - Usa la freccia ↩ per tornare alla dashboard.
                - Usa la casetta ⌂ per effettuare il logout.
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        this.switchScene(homePage, event);
    }
}
