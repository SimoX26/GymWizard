package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalTime;

public class RiepilogoAttivitaGUIController extends AbstractGUIController{

    String homePage = (String) SessionManager.getInstance().getAttributo("homePage");

    @FXML
    private Label nome, description, dateTime, startTime, finishTime, placesAvailable, nomeTrainer;

    public void initialize(String nome, String description, String dateTime, LocalTime startTime, LocalTime finishTime, String placesAvailable, String nomeTrainer) {
        this.nome.setText(nome);
        this.description.setText(description);
        this.dateTime.setText(dateTime);
        this.nomeTrainer.setText(nomeTrainer);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/AttivitaView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Riepilogo Attività", "Riepilogo dati dell'attività selezionata.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene(homePage, homeEvent);
    }
}
