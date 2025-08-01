package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.util.List;

public class VisualizzaSchedaGUIController extends AbstractGUIController {

    private static final String HOMEPAGE = "homePage";

    @FXML
    private ComboBox<Scheda> comboBoxSchede;

    @FXML
    private HBox hBoxBtn;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private TableView<EsercizioScheda> tableViewEsercizi;
    @FXML
    private TableColumn<EsercizioScheda, String> colNome;
    @FXML
    private TableColumn<EsercizioScheda, Integer> colSerie;
    @FXML
    private TableColumn<EsercizioScheda, Integer> colRipetizioni;
    @FXML
    private TableColumn<EsercizioScheda, String> colTipo; // nuova colonna

    private static final String STYLE_BUTTON = "button-custom";

    // Controller reale o demo (in base a configurazione)
    private final SchedaController controller = DemoFactory.getSchedaController();

    @FXML
    public void initialize() throws DAOException {
        anchorPane.setBackground(new Background(this.background()));

        Utente u;

        // Determino l'utente corretto in base alla view
        if ("/views/DashboardTrainerView.fxml".equals(SessionManager.getInstance().getAttributo(HOMEPAGE))) {
            Button nuova = new Button("CREA NUOVA SCHEDA");
            Button add = new Button("AGGIUNGI ESERCIZIO");
            Button flush = new Button("SVUOTA SCHEDA");

            nuova.getStyleClass().add(STYLE_BUTTON);
            add.getStyleClass().add(STYLE_BUTTON);
            flush.getStyleClass().add(STYLE_BUTTON);

            nuova.setOnAction(this::handleNewTrainingCard);
            add.setOnAction(this::handleAddExercise);
            flush.setOnAction(this::handleFlushTrainingCard);

            hBoxBtn.getChildren().addAll(nuova, add, flush);
            u = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        } else {
            u = (Utente) SessionManager.getInstance().getAttributo("utente");
        }

        // Popolo il ComboBox con le schede dell'utente
        List<Scheda> schede = controller.getSchedeByIdCliente(u.getId());
        ObservableList<Scheda> lista = FXCollections.observableArrayList(schede);
        comboBoxSchede.setItems(lista);

        comboBoxSchede.setConverter(new StringConverter<>() {
            @Override
            public String toString(Scheda scheda) {
                return (scheda != null) ? scheda.getNomeScheda() + " (" + scheda.getTipo() + ")" : "";
            }

            @Override
            public Scheda fromString(String nome) {
                return comboBoxSchede.getItems().stream()
                        .filter(s -> (s.getNomeScheda() + " (" + s.getTipo() + ")").equals(nome))
                        .findFirst()
                        .orElse(null);
            }
        });

        comboBoxSchede.setOnAction(event -> {
            Scheda selezionata = comboBoxSchede.getSelectionModel().getSelectedItem();
            if (selezionata != null) {
                try {
                    onSchedaSelezionata(selezionata);
                } catch (DAOException e) {
                    throw new RuntimeException("Errore nel caricamento degli esercizi", e);
                }
            }
        });

        // Se ci sono schede, seleziono la prima
        if (!lista.isEmpty()) {
            comboBoxSchede.getSelectionModel().selectFirst();
            Scheda selezionata = comboBoxSchede.getSelectionModel().getSelectedItem();
            SessionManager.getInstance().setAttributo("scheda", selezionata);
            onSchedaSelezionata(selezionata);
        }

        // Imposto le colonne
        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeEsercizio()));
        colSerie.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSerie()).asObject());
        colRipetizioni.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRipetizioni()).asObject());

        // Colonna Tipo: cast esplicito per evitare l'errore
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(
                ((Scheda) SessionManager.getInstance().getAttributo("scheda")).getTipo()
        ));
    }


    @FXML
    public void onSchedaSelezionata(Scheda schedaSelezionata) throws DAOException {
        if (schedaSelezionata == null) return;

        AppLogger.getLogger().info("Scheda selezionata: " + schedaSelezionata.getNomeScheda()
                + " [ID: " + schedaSelezionata.getId() + "]");

        SessionManager.getInstance().setAttributo("scheda", schedaSelezionata);
        List<EsercizioScheda> esercizi = controller.getEserciziScheda(schedaSelezionata.getId());
        ObservableList<EsercizioScheda> eserciziObs = FXCollections.observableArrayList(esercizi);
        tableViewEsercizi.setItems(eserciziObs);
    }

    @FXML
    public void handleNewTrainingCard(ActionEvent event) {
        AppLogger.getLogger().info("CREA NUOVA SCHEDA button clicked.");
        this.switchScene("/views/CreaSchedaView.fxml", event);
    }

    @FXML
    public void handleFlushTrainingCard(ActionEvent event) {
        AppLogger.getLogger().info("SVUOTA SCHEDA button clicked.");
        // Da implementare eventualmente in seguito
    }

    @FXML
    public void handleAddExercise(ActionEvent event) {
        AppLogger.getLogger().info("AGGIUNGI ESERCIZIO button clicked.");
        this.switchScene("/views/AggiungiEsercizioView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        AppLogger.getLogger().info("BACK button clicked.");
        String s = (String) SessionManager.getInstance().getAttributo(HOMEPAGE);
        if (s.equals("/views/DashboardTrainerView.fxml")) {
            this.switchScene("/views/ListaClientiView.fxml", backEvent);
        } else {
            this.switchScene(s, backEvent);
        }
    }

    @FXML
    public void onHelpClick() {
        AppLogger.getLogger().info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Scheda allenamento",
                "Puoi visualizzare la tua scheda di allenamento.\nPuoi scollare per effettuare uno zoom");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        AppLogger.getLogger().info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo(HOMEPAGE), homeEvent);
    }
}
