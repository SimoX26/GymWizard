package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.ClientiController;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class ListaClientiGUIController extends AbstractGUIController{

    @FXML
    private VBox clientListVBox;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize() throws SQLException {
        anchorPane.setBackground(new Background(this.background()));

        List<Utente> clientiList = ClientiController.getClienti();

        for (Utente cliente : clientiList) {
            Button btn = new Button(cliente.getUsername() );
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-padding: 10 20 10 20;" +
                            "-fx-cursor: hand"
            );
            btn.setOnAction(event -> onClienteClick(event, cliente));
            clientListVBox.getChildren().add(btn);
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

    public void onClienteClick(ActionEvent event, Utente cliente) {
        System.out.println("CLIENTE button clicked.");
        SessionManager.getInstance().setAttributo("clienteSelezionato", cliente);
        this.switchScene("/views/VisualizzaSchedaView.fxml", event);

    }
}
