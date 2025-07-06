package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InviaComunicazioniGUIController extends AbstractGUIController {

    @FXML
    private TextArea areaMessaggi;

    @FXML
    private TextField inputMessaggio;

    @FXML
    private AnchorPane anchorPane;

    private static final Logger logger = AppLogger.getLogger();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

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

        logger.log(Level.INFO, "Messaggio inviato: {0}", messaggio);

        Alert conferma = new Alert(AlertType.INFORMATION);
        conferma.setTitle("Messaggio inviato");
        conferma.setHeaderText(null);
        conferma.setContentText("Il messaggio è stato inviato con successo.");
        conferma.showAndWait();
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        logger.info("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), event);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
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
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), event);
    }
}
