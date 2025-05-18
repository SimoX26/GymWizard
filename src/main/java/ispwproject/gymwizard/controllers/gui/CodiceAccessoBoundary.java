package ispwproject.gymwizard.controllers.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class CodiceAccessoBoundary extends AbstractGUIController{

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene(backEvent, "/views/DashboardClienteView.fxml");
    }

    @FXML
    public void onHelpClick(ActionEvent helpEvent) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Codice D'Accesso");
        alert.setContentText("Utilizza questo codice QR per accedere alla struttura.");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene(homeEvent, "/views/DashboardClienteView.fxml");
    }
}
