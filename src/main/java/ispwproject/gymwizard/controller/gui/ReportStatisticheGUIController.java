package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.ReportStatisticheController;
import ispwproject.gymwizard.util.bean.PagamentoBean;
import ispwproject.gymwizard.util.bean.PrenotazioneBean;
import ispwproject.gymwizard.util.bean.UtenteAttivoBean;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportStatisticheGUIController extends AbstractGUIController implements Initializable {

    // Tabelle storiche
    @FXML private TableView<PagamentoBean> pagamentiTable;
    @FXML private TableColumn<PagamentoBean, String> colDataPagamento;
    @FXML private TableColumn<PagamentoBean, String> colImportoPagamento;
    @FXML private TableColumn<PagamentoBean, String> colMetodoPagamento;

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
        colDataPagamento.setCellValueFactory(new PropertyValueFactory<>("data"));
        colImportoPagamento.setCellValueFactory(new PropertyValueFactory<>("importo"));
        colMetodoPagamento.setCellValueFactory(new PropertyValueFactory<>("metodo"));

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
        List<PagamentoBean> pagamenti = controller.getStoricoPagamenti();
        List<PrenotazioneBean> prenotazioni = controller.getStoricoPrenotazioni();
        List<UtenteAttivoBean> utenti = controller.getUtentiAttivi();

        pagamentiTable.getItems().setAll(pagamenti);
        prenotazioniTable.getItems().setAll(prenotazioni);
        utentiAttiviTable.getItems().setAll(utenti);
    }

}


