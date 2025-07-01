package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.controller.app.SchedaController;
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

import java.util.List;

public class VisualizzaSchedaGUIController extends AbstractGUIController{

    @FXML
    private ComboBox<Scheda> comboBoxSchede;

    @FXML
    private HBox HBoxBtn;

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
    public void initialize() throws DAOException {
        anchorPane.setBackground(new Background(this.background()));

        Utente u;

        if("/views/DashboardTrainerView.fxml".equals(SessionManager.getInstance().getAttributo("homePage"))){
            Button nuova = new Button("CREA NUOVA SCHEDA");
            Button add = new Button("AGGIUNGI ESERCIZIO");
            Button flush = new Button("SVUOTA SCHEDA");

            nuova.setStyle("-fx-cursor: hand; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");
            add.setStyle("-fx-cursor: hand; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");
            flush.setStyle("-fx-cursor: hand; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");

            nuova.setOnAction(this::handleNewTrainingCard);
            add.setOnAction(this::handleAddExercise);
            flush.setOnAction(this::handleFlushTrainingCard);

            HBoxBtn.getChildren().add(nuova);
            HBoxBtn.getChildren().add(add);
            HBoxBtn.getChildren().add(flush);

            u = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        } else {
            u = (Utente) SessionManager.getInstance().getAttributo("utente");
        }

        // Popolamento del ComboBox (lista varie schede)
        List<Scheda> schede = SchedaController.getSchedeByIdCliente(u.getId());
        ObservableList<Scheda> lista = FXCollections.observableArrayList(schede);
        comboBoxSchede.setItems(lista);

        // Conversione in stringhe per mostrare solo il nome della scheda
        comboBoxSchede.setConverter(new StringConverter<>() {
            @Override
            public String toString(Scheda scheda) {
                return (scheda != null) ? scheda.getNomeScheda() : "";
            }

            @Override
            public Scheda fromString(String nome) {
                return comboBoxSchede.getItems().stream()
                        .filter(s -> s.getNomeScheda().equals(nome))
                        .findFirst()
                        .orElse(null);
            }
        });

        // Listener per intercettare la selezione
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


        // Viene selezionata la prima scheda disponibile
        if (!lista.isEmpty()) {
            comboBoxSchede.getSelectionModel().selectFirst();
            Scheda selezionata = comboBoxSchede.getSelectionModel().getSelectedItem();
            SessionManager.getInstance().setAttributo("scheda", selezionata);
            onSchedaSelezionata(selezionata);
        }

        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeEsercizio()));
        colSerie.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSerie()).asObject());
        colRipetizioni.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRipetizioni()).asObject());
    }

    @FXML
    public void onSchedaSelezionata(Scheda schedaSelezionata) throws DAOException {
        if (schedaSelezionata == null) {
            return;
        }

        System.out.println("Scheda selezionata: " + schedaSelezionata.getNomeScheda()
                + " [ID: " + schedaSelezionata.getId() + "]");

        // Salva l'intera scheda nella sessione
        SessionManager.getInstance().setAttributo("scheda", schedaSelezionata);

        // Carica gli esercizi associati alla scheda
        List<EsercizioScheda> esercizi = SchedaController.getEserciziScheda(schedaSelezionata.getId());
        ObservableList<EsercizioScheda> eserciziObs = FXCollections.observableArrayList(esercizi);

        // Imposta gli esercizi nella tabella
        tableViewEsercizi.setItems(eserciziObs);
    }

    @FXML
    public void handleNewTrainingCard(ActionEvent event){
        System.out.println("CREA NUOVA SCHEDA button clicked.");
        this.switchScene("/views/CreaSchedaView.fxml", event);
    }

    @FXML
    public void handleFlushTrainingCard(ActionEvent event) {
        System.out.println("SVUOTA SCHEDA button clicked.");
    }

    @FXML
    public void handleAddExercise(ActionEvent event) {
        System.out.println("AGGIUNGI ESERCIZIO button clicked.");
        this.switchScene("/views/AggiungiEsercizioView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        String s = (String) SessionManager.getInstance().getAttributo("homePage");
        if(s.equals("/views/DashboardTrainerView.fxml")){
            this.switchScene("/views/ListaClientiView.fxml", backEvent);
        } else {
            this.switchScene(s, backEvent);
        }
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia","Scheda allenamento","Puoi visualizzare la tua scheda di allenamento.\n" +
                "Puoi scollare per effettuare uno zoom");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
