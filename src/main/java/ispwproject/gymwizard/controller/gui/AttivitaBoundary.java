package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class AttivitaBoundary extends AbstractGUIController{

    @FXML
    public void onAttivitaClick(ActionEvent attivitaEvent){
        System.out.println("ATTIVITA button clicked.");
        this.switchScene(attivitaEvent, "/views/RiepilogoAttivitaView.fxml");
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene(backEvent, "/views/DashboardClienteView.fxml");
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle attività");
        alert.setContentText("Puoi visualizzare la lista delle attività disponibili.\n" +
                "Puoi scollare per scorrere la lista e scegliere l'attività a cui vuoi prenotarti");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene(homeEvent,"/views/DashboardClienteView.fxml");
    }
}
