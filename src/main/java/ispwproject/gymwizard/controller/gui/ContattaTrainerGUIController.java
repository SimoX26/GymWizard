package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ContattaTrainerGUIController extends AbstractGUIController {

    private static final Logger logger = Logger.getLogger(ContattaTrainerGUIController.class.getName());

    @FXML private TextField inputField;
    @FXML private Button sendBtn;
    @FXML private VBox messagesBox;
    @FXML private AnchorPane anchorPane;

    private String context;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    public void setContext(String context) {
        this.context = context;
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Schermata chiamata da: %s", context));
        }

    }

    public void onBackClick(ActionEvent event) {
        logger.info("BACK button clicked.");
        switchScene("/views/VisualizzaAttivitaView.fxml", event);
    }

    public void onHelpClick() {
        logger.info("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Chat Trainer");
        alert.setContentText("Scrivi un messaggio nel campo in basso e premi INVIA.");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        logger.info("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }
}
