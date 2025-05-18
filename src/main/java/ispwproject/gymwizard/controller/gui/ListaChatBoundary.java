package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ListaChatBoundary extends AbstractGUIController{

    @FXML private VBox chatListVBox;

    String homePage = SessionManager.getInstance().getAttributo("homePage");

    @FXML
    public void initialize() {
        /*
        // Esempio mock utenti
        for (int i = 1; i <= 5; i++) {
            Label utente = new Label("🗨️ Chat con Utente " + i);
            utente.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Comic Sans MS'; -fx-cursor: hand; -fx-padding: 8 10; -fx-background-color: #444; -fx-background-radius: 8;");
            int finalI = i;
            utente.setOnMouseClicked(event -> onChatClick(finalI, event));
            chatListVBox.getChildren().add(utente);
        }
        */
    }

    @FXML
    public void onChatBtnClick(ActionEvent chatEvent) {
        System.out.println("CHAT button clicked.");
        this.switchScene(chatEvent, "/views/ChatView.fxml");
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene(backEvent, homePage);
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
        this.switchScene(homeEvent, homePage);
    }
}
