package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AbbonamentoController;
import ispwproject.gymwizard.controller.app.ReportStatisticheController;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReportStatisticheGUIController extends AbstractGUIController implements Initializable {

    // Tabelle storiche
    @FXML private TableView<Abbonamento> pagamentiTable;
    @FXML private TableColumn<Abbonamento, String> colTipoAbbonamento;
    @FXML private TableColumn<Abbonamento, String> colImportoAbbonamento;
    @FXML private TableColumn<Abbonamento, String> colDataInizio;
    @FXML private TableColumn<Abbonamento, String> colStato;

    @FXML private TableView<PrenotazioneBean> prenotazioniTable;
    @FXML private TableColumn<PrenotazioneBean, String> colDataPrenotazione;
    @FXML private TableColumn<PrenotazioneBean, String> colAttivitaPrenotata;
    @FXML private TableColumn<PrenotazioneBean, String> colStatoPrenotazione;

    @FXML private TableView<UtenteAttivoBean> utentiAttiviTable;
    @FXML private TableColumn<UtenteAttivoBean, String> colUsername;
    @FXML private TableColumn<UtenteAttivoBean, String> colNomeCompleto;
    @FXML private TableColumn<UtenteAttivoBean, String> colUltimoAccesso;

    private final ReportStatisticheController controller = new ReportStatisticheController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupColumns();
        loadData();
    }

    private void setupColumns() {
        // Pagamenti
        colTipoAbbonamento.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colImportoAbbonamento.setCellValueFactory(cellData -> {
            String tipo = cellData.getValue().getTipo();
            int prezzoCent = AbbonamentoController.getPrezzoAbbonamento(tipo);
            String prezzoFormattato = String.format("%.2f €", prezzoCent / 100.0);
            return new SimpleStringProperty(prezzoFormattato);
        });

        colDataInizio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataInizio().toString()));
        colStato.setCellValueFactory(new PropertyValueFactory<>("stato"));

        // Prenotazioni
        colDataPrenotazione.setCellValueFactory(new PropertyValueFactory<>("data"));
        colAttivitaPrenotata.setCellValueFactory(new PropertyValueFactory<>("attivita"));
        colStatoPrenotazione.setCellValueFactory(new PropertyValueFactory<>("stato"));

        // Utenti Attivi
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colNomeCompleto.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        colUltimoAccesso.setCellValueFactory(new PropertyValueFactory<>("ultimoAccesso"));
    }

    private void loadData() {
        List<Abbonamento> abbonamenti = controller.getStoricoAbbonamenti();
        List<PrenotazioneBean> prenotazioni = controller.getStoricoPrenotazioni();
        List<UtenteAttivoBean> utenti = controller.getUtentiAttivi();

        pagamentiTable.getItems().setAll(abbonamenti);
        prenotazioniTable.getItems().setAll(prenotazioni);
        utentiAttiviTable.getItems().setAll(utenti);
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"),event);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Inerfaccia", "Report e statistiche", """
                Questa sezione ti permette di monitorare l'andamento dell'attività della palestra in tempo reale.
                Cosa puoi visualizzare:
                - Storico Pagamenti
                - Storico Prenotazioni
                - Utenti Attivi
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), event);
    }

}


