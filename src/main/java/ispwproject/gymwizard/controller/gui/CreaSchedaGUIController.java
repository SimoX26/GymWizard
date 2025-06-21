package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

public class CreaSchedaGUIController extends AbstractGUIController{

    @FXML
    private VBox esercizioContaier;

    @FXML
    public void initialize(){
        try{
            List<EsercizioScheda> eserciziList = SchedaController.getEserciziSchedaByIdCliente((Integer) SessionManager.getInstance().getAttributo("idCliente"));

            for (EsercizioScheda esercizio : eserciziList) {
                Button btn = new Button();
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: black;" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 10 20 10 20;"
                );
                btn.setOnAction(event -> onEsercizioInfoCclik(event, esercizio));
                esercizioContaier.getChildren().add(btn);
            }

        } catch (DAOException e) {
            showError("Errore caricamento attività", e.getMessage());
        }
    }

    @FXML
    public void onEsercizioInfoCclik(ActionEvent event, EsercizioScheda esercizio) {
        System.out.println("AGGIUNGI ESERCIZIO button clicked.");
        this.switchScene("", event);
    }

    @FXML
    public void onAddEsercizioCclik(ActionEvent event) {
        System.out.println("AGGIUNGI ESERCIZIO button clicked.");
        this.switchScene("/views/AggiungiEsercizioView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/ListaClientiView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia","Creazione scheda di allenamento","Schermata di creazione della scheda di allenamento\n" +
                "Puoi aggiungere esercizi alla scheda associata al cliente.");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
