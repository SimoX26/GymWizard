package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.app.PagamentoController;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.awt.*;
import java.net.URI;

public class RiepilogoOrdineGUIController extends AbstractGUIController{

    private String tipo;

    @FXML
    private Label name, price, dataEmissione, dataScadenza;

    @FXML
    private TextArea description;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));

        tipo = (String) SessionManager.getInstance().getAttributo("tipoAbbonamento");

        if (tipo != null) {
            name.setText(AbbonamentoController.getNomeAbbonamento(tipo));
            description.setText(AbbonamentoController.getDescrizioneAbbonamento(tipo));
            price.setText(String.valueOf(AbbonamentoController.getPrezzoAbbonamento(tipo)));
            dataEmissione.setText(String.valueOf(AbbonamentoController.getDataEmissione()));
            dataScadenza.setText(String.valueOf(AbbonamentoController.getDataScadenza(tipo)));
        } else {
            name.setText("Tipo non selezionato");
            description.setText("-");
            price.setText("-");
            dataEmissione.setText("-");
            dataScadenza.setText("-");
        }
    }

    @FXML
    private void onPagamentoClick() {
        System.out.println("PAGAMENTO button clicked.");

        int prezzo = AbbonamentoController.getPrezzoAbbonamento(tipo);
        try {
            PagamentoController paypal = new PagamentoController();
            String url = paypal.creaOrdine(prezzo);
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            this.showError("Errore", "Errore durante l'avvio del pagamento." + e.getMessage());
        }

        this.showPopup("Pagamento in attesa", null, "Verifica lo stato del pagamento una volta completato su PayPal.");
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
