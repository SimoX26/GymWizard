package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.bean.AbbonamentoBean;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import ispwproject.gymwizard.util.logger.AppLogger;
import java.util.logging.Logger;

public class StatoAbbonamentoGUIController extends AbstractGUIController {

    private static final Logger logger = AppLogger.getLogger();

    @FXML
    private Label stato;
    @FXML
    private Label dataEmissione;
    @FXML
    private Label dataScadenza;
    @FXML
    private Label tipologia;
    @FXML
    private AnchorPane anchorPane;

    private final AbbonamentoController controller = DemoFactory.getAbbonamentoController(); // Viene selezionato il controller a runtime
    private final AbbonamentoBean bean = new AbbonamentoBean();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background())); // Impostazione dell'immagine di sfondo

        controller.getDatiAbbonamento(bean); // Impostazione della bean

        Abbonamento a = bean.getAbbonamento();
        if (a != null) {
            tipologia.setText(a.getTipo());
            dataEmissione.setText(a.getDataInizio().toString());
            dataScadenza.setText(a.getDataFine().toString());
            stato.setText(a.getStato());
        } else { // Gestione del caso in cui non ci siano nessun dato dell'abbonamento
            tipologia.setText("N/A");
            dataEmissione.setText("N/A");
            dataScadenza.setText("N/A");
            stato.setText("N/A");
        }
    }

    @FXML
    private void onRinnovaClick(ActionEvent event) {
        logger.info("RINNOVA button clicked.");
        switchScene("/views/RinnovaAbbonamentoView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        logger.info("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    @FXML
    public void onHelpClick() {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Stato Abbonamento", """
                • Stato: può essere Attivo, Scaduto o In Scadenza (entro 10 giorni).
                • Data di emissione: giorno in cui è stato emesso l'abbonamento.
                • Tipologia: tipo di piano (es. Mensile, Trimestrale, Annuale).
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
