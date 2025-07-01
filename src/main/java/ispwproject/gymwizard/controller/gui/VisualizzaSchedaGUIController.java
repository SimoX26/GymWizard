package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
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

import java.util.Collection;
import java.util.List;

public class VisualizzaSchedaGUIController extends AbstractGUIController{

    @FXML
    private ComboBox<String> comboBoxSchede;

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
        }

        // Popolamento del ComboBox
        List<Scheda> schede = SchedaController.getNomiSchedeByIdCliente();
        List<String> nomiSchede = schede.stream()
                .map(Scheda::getNomeScheda)
                .toList();
        ObservableList<String> lista = FXCollections.observableArrayList(nomiSchede);
        comboBoxSchede.setItems(lista);

        // Listener per intercettare la selezione
        comboBoxSchede.setOnAction(event -> {
            try {
                onSchedaSelezionata(event);
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });

        // Viene selezionata la prima scheda disponibile
        comboBoxSchede.setItems(lista);
        if (!lista.isEmpty()) {
            comboBoxSchede.getSelectionModel().selectFirst();
            onSchedaSelezionata(null);
        }

        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeEsercizio()));
        colSerie.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSerie()).asObject());
        colRipetizioni.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRipetizioni()).asObject());
    }

    @FXML
    public void onSchedaSelezionata(ActionEvent event) throws DAOException {
        String nomeSelezionato = comboBoxSchede.getValue();

        if (nomeSelezionato == null || nomeSelezionato.isBlank()) {
            return;
        }

        List<Scheda> schede = SchedaController.getNomiSchedeByIdCliente();
        Scheda schedaSelezionata = schede.stream()
                .filter(s -> s.getNomeScheda().equals(nomeSelezionato))
                .findFirst()
                .orElse(null);

        if (schedaSelezionata != null) {
            System.out.println("Scheda selezionata: " + schedaSelezionata.getNomeScheda());
            SessionManager.getInstance().setAttributo("scheda", schedaSelezionata);

            List<EsercizioScheda> esercizi = SchedaController.getEserciziScheda(schedaSelezionata.getId());
            ObservableList<EsercizioScheda> eserciziObs = FXCollections.observableArrayList(esercizi);

            // DEBUG TEMPORANEO
            System.out.println("Esercizi trovati: " + esercizi.size());
            for (EsercizioScheda e : esercizi) {
                System.out.println(" - " + e.getNomeEsercizio() + " (" + e.getSerie() + "x" + e.getRipetizioni() + ")");
            }


            tableViewEsercizi.setItems(eserciziObs);
        }
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
