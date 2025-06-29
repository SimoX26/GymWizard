package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class AggiungiEsercizioGUIController extends AbstractGUIController{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onAggiungiEsercizio(ActionEvent event) {
        System.out.println("AGGIUNGI ESERCIZIO button clicked.");
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        if(SessionManager.getInstance().getAttributo("homePage").equals("/views/DashboardTrainerView.fxml")){
            this.switchScene("/views/ListaClientiView.fxml", backEvent);

        }else{
            this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
        }
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
