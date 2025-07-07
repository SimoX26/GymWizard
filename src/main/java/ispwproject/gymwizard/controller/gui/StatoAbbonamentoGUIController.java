package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Abbonamento;
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

    private final AbbonamentoController controller = DemoFactory.getAbbonamentoController();

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        Abbonamento abbonamento = controller.getDatiAbbonamento();  // usa il controller dinamico

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
        logger.info("RINNOVA button clicked.");
        switchScene("/views/RinnovaAbbonamentoView.fxml", rinnovaEvent);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", backEvent);
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
