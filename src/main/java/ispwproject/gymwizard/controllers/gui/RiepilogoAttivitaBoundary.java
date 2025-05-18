package ispwproject.gymwizard.controllers.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class RiepilogoAttivitaBoundary extends AbstractGUIController{

    @FXML
    private Label nomeLabel;

    @FXML
    private Label giornoLabel;

    @FXML
    private Label oraLabel;

    @FXML
    private TextArea descrizioneArea;

    public void setDettagliAttivita(String nome, String giorno, String ora, String descrizione) {
        nomeLabel.setText(nome);
        giornoLabel.setText(giorno);
        oraLabel.setText(ora);
        descrizioneArea.setText(descrizione);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene(backEvent, "/views/AttivitaView.fxml");
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
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
