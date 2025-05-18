package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class StatoAbbonamentoBoundary extends AbstractGUIController{

    @FXML
    private Label statoLabel, dataLabel, tipologiaLabel;

    @FXML
    private void onRinnovaClick(ActionEvent rinnovaEvent) {
        System.out.println("RINNOVA button clicked.");
        switchScene(rinnovaEvent, "/views/RinnovaAbbonamentoView.fxml");
    }


    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        switchScene(backEvent, "/views/DashboardClienteView.fxml");
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Stato Abbonamento");
        alert.setContentText("\"\"\"\n" +
                "                • Stato: può essere Attivo, Scaduto o In Scadenza (entro 10 giorni).\n" +
                "                • Data di emissione: giorno in cui è stato emesso l'abbonamento.\n" +
                "                • Tipologia: tipo di piano (es. Mensile, Trimestrale, Annuale).\n" +
                "                \"\"\"");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene(homeEvent, "/views/DashboardClienteView.fxml");
    }
}
