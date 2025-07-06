package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import static java.lang.System.*;


public class ChatGUIController extends AbstractGUIController{

    @FXML private TextField inputField;
    @FXML private Button sendBtn;
    @FXML private VBox messagesBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));
    }

    // Metodo che imposta il contesto di esecuzione in base alla schermata da cui è stata chiamata questa
    public void setContext(String context) {
        out.println("Schermata chiamata da: " + context);
    }

    public void onBackClick(ActionEvent event) {
        out.println("BACK button clicked.");
        switchScene("/views/ListaChatView.fxml", event);
    }

    public void onHelpClick() {
        out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Chat");
        alert.setContentText("Scrivi un messaggio nel campo in basso e premi INVIA.");
        alert.showAndWait();
    }

    public void onHomeClick() {
        out.println("HOME button clicked.");
    }

}
