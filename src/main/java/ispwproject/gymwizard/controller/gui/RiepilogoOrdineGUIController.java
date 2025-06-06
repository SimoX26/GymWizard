package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class RiepilogoOrdineGUIController extends AbstractGUIController{

    @FXML
    private Label titoloAbbonamentoLabel;

    // Questo metodo viene chiamato da GUIRinnovaAbbonamentoController
    public void setTipoAbbonamento(String tipo) {
        titoloAbbonamentoLabel.setText(tipo);
    }

    @FXML
    private void handleAcquistaClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acquisto completato");
        alert.setHeaderText(null);
        alert.setContentText("Grazie per il tuo acquisto! Il tuo abbonamento è stato attivato.");
        alert.showAndWait();
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/RinnovaAbbonamentoView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Riepilogo Ordine", "In questa schermata puoi visualizzare il riepilogo del tuo abbonamento selezionato.\n" +
                "                Premi \"Procedi con l'acquisto\" per confermare e completare l'acquisto.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
