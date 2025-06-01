package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.DAO.AttivitaDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreaAttivitaGUIController extends AbstractGUIController{

    @FXML private TextField nomeField;
    @FXML private TextArea descrizioneField;
    @FXML private DatePicker dataPicker;
    @FXML private TextField oraInizioField;
    @FXML private TextField oraFineField;
    @FXML private TextField postiDisponibiliField;
    @FXML private TextField trainerNameField;

    @FXML
    private void onCreaAttivita() {
        try {
            String nome = nomeField.getText();
            String descrizione = descrizioneField.getText();
            LocalDate data = dataPicker.getValue();
            LocalTime oraInizio = LocalTime.parse(oraInizioField.getText());
            LocalTime oraFine = LocalTime.parse(oraFineField.getText());
            int posti = Integer.parseInt(postiDisponibiliField.getText());
            String trainerName = trainerNameField.getText();

            Attivita nuova = new Attivita(0, nome, descrizione, data, oraInizio, oraFine, posti, trainerName);

            AttivitaDAO.getInstance().inserisciAttivita(nuova);

            showSuccess("Attività creata con successo!");

        } catch (DAOException e) {
            showError("Errore durante il salvataggio: " + e.getMessage());
        } catch (Exception e) {
            showError("Dati non validi: " + e.getMessage());
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) {
        switchScene("/views/CalendarioAttivitaView.fxml",event);
    }

    @FXML
    private void onHelpClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Guida");
        alert.setHeaderText(null);
        alert.setContentText("Compila tutti i campi per creare una nuova attività. " +
                "Assicurati di inserire un nome, selezionare un giorno e un'ora, e fornire una descrizione.");
        alert.showAndWait();
    }

    @FXML
    private void onHomeClick(ActionEvent event) {
        switchScene("/views/DashboardAdminView.fxml", event);
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Impossibile creare l’attività");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showSuccess(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText("Attività creata");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
