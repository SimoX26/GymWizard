package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class ListaChatGUIController extends AbstractGUIController{

    @FXML private VBox chatListVBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onChatBtnClick(ActionEvent chatEvent) {
        System.out.println("CHAT LIST button clicked.");
        this.switchScene("/views/ChatView.fxml", chatEvent);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle chat");
        alert.setContentText("Puoi visualizzare la lista delle tue chat.\n" +
                "Puoi scollare per scorrere la lista e scegliere la chat che vuoi aprire.");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
