package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.ClientiController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.ClienteBean;
import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ListaClientiGUIController extends AbstractGUIController {

    @FXML
    private VBox clientListVBox;

    @FXML
    private AnchorPane anchorPane;

    private final ClientiController clientiController = DemoFactory.getClientiController();
    private final ClienteBean bean = new ClienteBean();
    private static final Logger logger = AppLogger.getLogger();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        try {
            clientiController.getClienti(bean);

            List<Utente> clientiList = bean.getListaClienti();

            for (Utente cliente : clientiList) {
                Button btn = new Button(cliente.getUsername());
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: black;" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-cursor: hand"
                );
                btn.setOnAction(event -> onClienteClick(event, cliente));
                clientListVBox.getChildren().add(btn);
            }

        } catch (SQLException e) {
            showError("Errore", "Errore durante il caricamento dei clienti: " + e.getMessage());
            logger.severe("Errore durante il caricamento dei clienti: " + e.getMessage());
        }
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Lista Clienti", "Puoi visualizzare la lista dei clienti presenti nel sistema.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }

    public void onClienteClick(ActionEvent event, Utente cliente) {
        logger.info("CLIENTE button clicked.");
        SessionManager.getInstance().setAttributo("clienteSelezionato", cliente);
        this.switchScene("/views/VisualizzaSchedaView.fxml", event);
    }
}
