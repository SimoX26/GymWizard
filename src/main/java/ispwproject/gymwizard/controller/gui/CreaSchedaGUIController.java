package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Logger;

public class CreaSchedaGUIController extends AbstractGUIController {

    private static final Logger logger = Logger.getLogger(CreaSchedaGUIController.class.getName());

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nomeScheda;

    @FXML
    private ToggleGroup tipoSchedaGroup;
    @FXML
    private RadioButton bulkRadio;
    @FXML
    private RadioButton cutRadio;


    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onCreaScheda(ActionEvent event) throws DAOException {
        logger.info("CREA SCHEDA button clicked.");
        String nome = nomeScheda.getText();

        String tipo = null;

        if (bulkRadio.isSelected()) {
            tipo = "bulk";
        } else if (cutRadio.isSelected()) {
            tipo = "cut";
        }

        new SchedaController().creaScheda(nome, tipo);
        showPopup("Successo", "Scheda creata", "Scheda creata con successo!");
        switchScene("/views/VisualizzaSchedaView.fxml", event);
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
                "Puoi visualizzare la tua scheda di allenamento.\nPuoi scollare per effettuare uno zoom");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}

