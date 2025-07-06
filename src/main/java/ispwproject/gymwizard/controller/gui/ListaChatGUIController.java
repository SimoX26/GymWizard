package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.logging.Logger;

public class ListaChatGUIController extends AbstractGUIController {

    @FXML
    private VBox chatListVBox;

    @FXML
    private AnchorPane anchorPane;

    private static final Logger logger = AppLogger.getLogger();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onChatBtnClick(ActionEvent chatEvent) {
        logger.info("CHAT LIST button clicked.");
        this.switchScene("/views/ChatView.fxml", chatEvent);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        logger.info("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle chat");
        alert.setContentText("""
                Puoi visualizzare la lista delle tue chat.
                Puoi scollare per scorrere la lista e scegliere la chat che vuoi aprire.
                """);
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
