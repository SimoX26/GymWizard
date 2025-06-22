package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class VisualizzaAttivitaGUIController extends AbstractGUIController{

    @FXML
    private Label name, description, dateTime, startTime, finishTime, placesAvailable, nomeTrainer;

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
    }

    @FXML
    public void handlePrenota() throws DAOException {
        System.out.println("PRENOTA button clicked.");
        AttivitaController.prenotaAttivita((Attivita) SessionManager.getInstance().getAttributo("attivitaSelezionata"));
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
