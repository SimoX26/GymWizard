package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class VisualizzaSchedaGUIController extends AbstractGUIController{

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", backEvent);
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
        this.switchScene("/views/DashboardClienteView.fxml", homeEvent);
    }
}
