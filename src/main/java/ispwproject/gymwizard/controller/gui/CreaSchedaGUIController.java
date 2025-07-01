package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class CreaSchedaGUIController extends AbstractGUIController{

    public AnchorPane anchorPane;
    @FXML
    private TextField nomeScheda;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onCreaScheda(ActionEvent event) throws DAOException {
        System.out.println("CREA SCHEDA button clicked.");
        String nome = nomeScheda.getText();
        new SchedaController().creaScheda(nome);
        showPopup("Successo", "Scheda creata", "Scheda creata con successo!");
        switchScene("/views/VisualizzaSchedaView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/VisualizzaSchedaView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia","Scheda allenamento","Puoi visualizzare la tua scheda di allenamento.\n" +
                "Puoi scollare per effettuare uno zoom");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
