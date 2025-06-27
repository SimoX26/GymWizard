package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreaAttivitaGUIController extends AbstractGUIController {

    @FXML
    private AnchorPane anchorPane;

    @FXML private TextField nomeField;
    @FXML private TextArea descrizioneField;
    @FXML private DatePicker dataPicker;
    @FXML private TextField oraInizioField;
    @FXML private TextField oraFineField;
    @FXML private TextField postiDisponibiliField;
    @FXML private TextField trainerNameField;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onCreaAttivita() {
        System.out.println("SAVE button clicked.");
        try {
            String nome = nomeField.getText();
            String descrizione = descrizioneField.getText();
            LocalDate data = dataPicker.getValue();
            LocalTime oraInizio = LocalTime.parse(oraInizioField.getText());
            LocalTime oraFine = LocalTime.parse(oraFineField.getText());
            int posti = Integer.parseInt(postiDisponibiliField.getText());
            String trainerName = trainerNameField.getText();

            new AttivitaController().creaAttivita(nome, descrizione, data, oraInizio, oraFine, posti, trainerName);

            showPopup("Successo", "Attività creata", "Attività creata con successo!");
            switchScene("/views/ListinoAttivitaView.fxml", null);

        } catch (AttivitaDuplicataException e) {
            showPopup("Attività Duplicata", "Errore", e.getMessage());

        } catch (DAOException e) {
            showError("Errore durante il salvataggio:", e.getMessage());

        } catch (Exception e) {
            showError("Dati non validi:", e.getMessage());
        }
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/ListinoAttivitaView.fxml", event);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Crea Attività", "Compila tutti i campi per creare una nuova attività. " +
                "Assicurati di inserire un nome, selezionare un giorno e un'ora, e fornire una descrizione.");
    }

    @FXML
    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), event);
    }
}
