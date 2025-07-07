package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Logger;

public class AggiungiEsercizioGUIController extends AbstractGUIController {

    private static final Logger logger = Logger.getLogger(AggiungiEsercizioGUIController.class.getName());

    @FXML
    private AnchorPane anchorPane;

    @FXML private TextField nomeEsercizioField;
    @FXML private TextField numeroSerieField;
    @FXML private TextField numeroRepField;
    @FXML private TextField noteField;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    public void onAggiungiEsercizio(ActionEvent event) throws SchedaController.EsercizioDuplicatoException {
        logger.info("AGGIUNGI ESERCIZIO button clicked.");

        String nome = nomeEsercizioField.getText();
        int serie = Integer.parseInt(numeroSerieField.getText());
        int rep = Integer.parseInt(numeroRepField.getText());
        String note = noteField.getText();

        new SchedaController().aggiungiEsercizio(nome, serie, rep, note);

        this.switchScene("/views/VisualizzaSchedaView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        this.switchScene("/views/VisualizzaSchedaView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Scheda allenamento",
                "Puoi visualizzare la tua scheda di allenamento.\n" +
                        "Puoi scollare per effettuare uno zoom");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}

