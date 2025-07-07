package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.logging.Logger;

public class ListinoAttivitaGUIController extends AbstractGUIController {

    private static final Logger LOGGER = AppLogger.getLogger();
    private static final String HOME_PAGE_ATTR = "homePage";

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox addBtn;

    @FXML
    private VBox attivitaContainer;

    private final AttivitaController controller = DemoFactory.getAttivitaController();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        if ("/views/DashboardAdminView.fxml".equals(SessionManager.getInstance().getAttributo(HOME_PAGE_ATTR))) {
            Button btn = new Button("+");
            btn.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-cursor: hand"
            );
            btn.setOnAction(this::handleAddActivity);
            addBtn.getChildren().add(btn);
        }

        try {
            List<Attivita> attivitaList = controller.getAttivitaDisponibili();
            attivitaContainer.getChildren().clear();
            for (Attivita attivita : attivitaList) {
                Button btn = new Button(attivita.getNome() + " | " + attivita.getData() + " | " + attivita.getOraInizio() + " - " + attivita.getOraFine() + " | posti rimanenti: " + attivita.getPostiDisponibili());
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle("-fx-font-size: 14px; -fx-cursor: hand; -fx-font-family: 'Helvetica'; -fx-font-weight: bold;");
                btn.setOnAction(event -> onAttivitaClick(event, attivita));
                attivitaContainer.getChildren().add(btn);
            }
        } catch (DAOException e) {
            showError("Errore caricamento attività", e.getMessage());
        }
    }

    @FXML
    public void handleAddActivity(ActionEvent event) {
        LOGGER.info("ADD button clicked.");
        switchScene("/views/CreaAttivitaView.fxml", event);
    }

    @FXML
    public void onAttivitaClick(ActionEvent event, Attivita attivita) {
        LOGGER.info("ATTIVITA button clicked.");
        SessionManager.getInstance().setAttributo("attivitaSelezionata", attivita);
        this.switchScene("/views/VisualizzaAttivitaView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        LOGGER.info("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo(HOME_PAGE_ATTR), backEvent);
    }

    @FXML
    public void onHelpClick() {
        LOGGER.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Lista delle attività", """
                Puoi visualizzare la lista delle attività disponibili.
                Puoi scorrere per trovare l'attività a cui vuoi prenotarti.
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        LOGGER.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo(HOME_PAGE_ATTR), homeEvent);
    }
}
