package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class RiepilogoOrdineGUIController extends AbstractGUIController{

    @FXML
    private Label name, dataEmissione, dataScadenza;

    @FXML
    private TextArea description;

    @FXML
    public void initialize() {
        String tipo = (String) SessionManager.getInstance().getAttributo("tipoAbbonamento");

        if (tipo != null) {
            name.setText(AbbonamentoController.getNomeAbbonamento(tipo));
            description.setText(AbbonamentoController.getDescrizioneAbbonamento(tipo));
            dataEmissione.setText(String.valueOf(AbbonamentoController.getDataEmissione(tipo)));
            dataScadenza.setText(String.valueOf(AbbonamentoController.getDataScadenza(tipo)));
        } else {
            name.setText("Tipo non selezionato");
            description.setText("-");
            dataEmissione.setText("-");
            dataScadenza.setText("-");
        }
    }

    @FXML
    private void onRinnovaClick() {
        System.out.println("ACQUISTA button clicked.");
        this.showPopup("Acquisto completato", null, "Grazie per il tuo acquisto! Il tuo abbonamento è stato attivato.");
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
