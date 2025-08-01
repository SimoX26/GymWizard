package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.app.PagamentoController;
import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Logger;

public class RiepilogoAbbonamentoGUIController extends AbstractGUIController {

    private static final Logger logger = AppLogger.getLogger();

    @FXML private Label name;
    @FXML private Label price;
    @FXML private Label dataEmissione;
    @FXML private Label dataScadenza;
    @FXML private Label description;
    @FXML private AnchorPane anchorPane;

    private final PagamentoController pagamentoController = new PagamentoController();
    private final AbbonamentoController controller = new AbbonamentoController();
    private String tipo;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        tipo = (String) SessionManager.getInstance().getAttributo("tipoAbbonamento");

        if (tipo != null) {
            name.setText(controller.getNomeAbbonamento(tipo));
            description.setText(controller.getDescrizioneAbbonamento(tipo));
            price.setText(String.valueOf(controller.getPrezzoAbbonamento(tipo)));
            dataEmissione.setText(String.valueOf(controller.getDataEmissione()));
            dataScadenza.setText(String.valueOf(controller.getDataScadenza(tipo)));
        } else {
            name.setText("Tipo non selezionato");
            description.setText("-");
            price.setText("-");
            dataEmissione.setText("-");
            dataScadenza.setText("-");
        }
    }

    @FXML
    private void onPagamentoClick(ActionEvent event) {
        logger.info("PAGAMENTO button clicked.");

        int prezzo = controller.getPrezzoAbbonamento(tipo);
        try {
            String url = pagamentoController.creaOrdine(prezzo);

            controller.apriNelBrowser(url);
            controller.aggiungiAbbonamento(tipo, "Pagamento mock");

            this.showPopup("Pagamento in attesa", null, "Verifica lo stato del pagamento nel browser.");
            this.showPopup("IGNORARE PAGAMENTO", null, "Dimostrazione: abbonamento attivato senza pagamento reale.");
            this.switchScene("/views/StatoAbbonamentoView.fxml", event);

        } catch (IllegalStateException e) {
            this.showError("Abbonamento esistente", "Hai già un abbonamento attivo.");
        } catch (Exception e) {
            this.showError("Errore generico", "Errore durante l’avvio del pagamento: " + e.getMessage());
        }
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        this.switchScene("/views/RinnovaAbbonamentoView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Riepilogo Ordine", """
                In questa schermata puoi visualizzare il riepilogo del tuo abbonamento selezionato.
                Premi "Procedi con l'acquisto" per confermare e completare l'acquisto.
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
