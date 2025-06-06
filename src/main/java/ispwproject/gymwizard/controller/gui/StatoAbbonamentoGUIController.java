package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatoAbbonamentoGUIController extends AbstractGUIController{

    @FXML
    private Label stato, dataEmissione, dataScadenza, tipologia;

    @FXML
    public void initialize() {
        Abbonamento abbonamento = AbbonamentoController.getDatiAbbonamento();

        if (abbonamento != null) {
            tipologia.setText(abbonamento.getTipo());
            dataEmissione.setText(abbonamento.getDataInizio().toString());
            dataScadenza.setText(abbonamento.getDataFine().toString());
            stato.setText(abbonamento.getStato());
        } else {
            // Gestione del caso in cui non ci siano dati
            tipologia.setText("N/A");
            dataEmissione.setText("N/A");
            dataScadenza.setText("N/A");
            stato.setText("N/A");

        }
    }

    @FXML
    private void onRinnovaClick(ActionEvent rinnovaEvent) {
        System.out.println("RINNOVA button clicked.");
        switchScene("/views/RinnovaAbbonamentoView.fxml", rinnovaEvent);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Stato Abbonamento", "\"\"\"\n" +
                "                • Stato: può essere Attivo, Scaduto o In Scadenza (entro 10 giorni).\n" +
                "                • Data di emissione: giorno in cui è stato emesso l'abbonamento.\n" +
                "                • Tipologia: tipo di piano (es. Mensile, Trimestrale, Annuale).\n" +
                "                \"\"\"");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
