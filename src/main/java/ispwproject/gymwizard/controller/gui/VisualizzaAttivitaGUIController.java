package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class VisualizzaAttivitaGUIController extends AbstractGUIController{

    @FXML
    private Label name, description, dateTime, startTime, finishTime, placesAvailable, nomeTrainer;

    @FXML
    private HBox HBoxBtn;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
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

        if("/views/DashboardClienteView.fxml".equals(SessionManager.getInstance().getAttributo("homePage"))){
            Button contatta = new Button("CONTATTA TRAINER");
            Button prenota = new Button("PRENOTA");

            contatta.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");
            prenota.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");

            contatta.setOnAction(this::handleContatta);
            prenota.setOnAction(this::handlePrenota);

            HBoxBtn.getChildren().add(contatta);
            HBoxBtn.getChildren().add(prenota);
        }
    }

    @FXML
    public void handleContatta(ActionEvent event) {
        System.out.println("CONTATTA button clicked.");
        this.switchScene("/views/ContattaTrainerView.fxml", event);
    }

    @FXML
    public void handlePrenota(ActionEvent event){
        System.out.println("PRENOTA button clicked.");
        try {
            AttivitaController.prenotaAttivita((Attivita) SessionManager.getInstance().getAttributo("attivitaSelezionata"));
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        this.showPopup("Attivita prenotata", "Attività prenotata", "L'attività è stata prenotata con successo!");
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/ListinoAttivitaView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Riepilogo Attività", "Riepilogo dati dell'attività selezionata.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
