package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import ispwproject.gymwizard.util.logger.AppLogger;

public class VisualizzaAttivitaGUIController extends AbstractGUIController {

    private final AttivitaController controller = DemoFactory.getAttivitaController(); // Controller dinamico

    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private Label dateTime;
    @FXML
    private Label startTime;
    @FXML
    private Label finishTime;
    @FXML
    private Label placesAvailable;
    @FXML
    private Label nomeTrainer;

    @FXML
    private HBox hBoxBtn;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        Object obj = SessionManager.getInstance().getAttributo("attivitaSelezionata");

        if (obj instanceof Attivita attivita) {
            name.setText(attivita.getNome());
            description.setText(attivita.getDescrizione());
            dateTime.setText(attivita.getData().toString());
            startTime.setText(attivita.getOraInizio().toString());
            finishTime.setText(attivita.getOraFine().toString());
            placesAvailable.setText(String.valueOf(attivita.getPostiDisponibili()));
            nomeTrainer.setText(attivita.getTrainerName());
        } else {
            showError("ERRORE!", "Nessuna attività selezionata trovata nella sessione.");
        }

        if ("/views/DashboardClienteView.fxml".equals(SessionManager.getInstance().getAttributo("homePage"))) {
            Button contatta = new Button("CONTATTA TRAINER");
            Button prenota = new Button("PRENOTA");

            contatta.getStyleClass().add("button-custom");
            prenota.getStyleClass().add("button-custom");

            contatta.setOnAction(this::handleContatta);
            prenota.setOnAction(this::handlePrenota);

            hBoxBtn.getChildren().add(contatta);
            hBoxBtn.getChildren().add(prenota);
        }
    }

    @FXML
    public void handleContatta(ActionEvent event) {
        AppLogger.getLogger().info("CONTATTA button clicked.");
        this.switchScene("/views/ListaChatView.fxml", event);
    }

    @FXML
    public void handlePrenota(ActionEvent event) {
        AppLogger.getLogger().info("PRENOTA button clicked.");
        try {
            Attivita attivita = (Attivita) SessionManager.getInstance().getAttributo("attivitaSelezionata");
            controller.prenotaAttivita(attivita);
            this.showPopup("Attività prenotata", "Attività prenotata", "L'attività è stata prenotata con successo!");
            this.switchScene("/views/ListinoAttivitaView.fxml", event);
        } catch (DAOException | AttivitaPienaException e) {
            this.showError("Errore prenotazione", e.getMessage());
        }
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        AppLogger.getLogger().info("BACK button clicked.");
        this.switchScene("/views/ListinoAttivitaView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        AppLogger.getLogger().info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Riepilogo Attività", "Riepilogo dati dell'attività selezionata.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        AppLogger.getLogger().info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
