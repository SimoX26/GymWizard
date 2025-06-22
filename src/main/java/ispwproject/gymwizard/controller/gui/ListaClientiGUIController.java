package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class ListaClientiGUIController extends AbstractGUIController{

    @FXML
    private VBox clientListVBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        // Aggiunta clienti di esempio (sostituisci con dati reali)
        for (int i = 1; i <= 4; i++) {
            Button cliente = new Button("⚪ Nome Cliente " + i);
            cliente.setStyle("-fx-text-fill: white; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 16; -fx-cursor: hand;");
            int finalI = i;
            cliente.setOnAction(event -> apriDettaglioCliente(event, finalI));
            clientListVBox.getChildren().add(cliente);
        }
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia","Lista delle attività","Puoi visualizzare la lista delle attività disponibili.\n" +
                "Puoi scollare per scorrere la lista e scegliere l'attività a cui vuoi prenotarti");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }

    public void apriDettaglioCliente(ActionEvent event, int idCliente) {
        System.out.println("CLIENTE button clicked.");
        this.switchScene("/views/VisualizzaSchedaView.fxml", event);

    }
}
