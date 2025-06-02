package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

public class AttivitaGUIController extends AbstractGUIController{

    @FXML
    private VBox addBtn;

    @FXML
    private VBox attivitaContainer; // deve essere collegato al VBox dentro lo ScrollPane in FXML

    String homePage = (String) SessionManager.getInstance().getAttributo("homePage");

    @FXML
    public void initialize(){
        if("/views/DashboardAdminView.fxml".equals(homePage)){
            Button btn = new Button("+");
            btn.setStyle("-fx-font-size: 24; -fx-background-color: green; -fx-cursor: hand; -fx-text-fill: white; -fx-background-radius: 100%; -fx-border-color: white; -fx-border-radius: 50; -fx-border-width: 2;");
            btn.setOnAction(event -> handleAddActivity(event));
            addBtn.getChildren().add(btn);
        }

        try{
            // Chiamata coerente con la tua classe AttivitaDAO
            List<Attivita> attivitaList = AttivitaController.getAttivitaDisponibili();

            for (Attivita attivita : attivitaList) {
                Button btn = new Button(attivita.getNome() + " | " + attivita.getData() + " | " + attivita.getOraInizio() +  " - "+attivita.getOraFine());
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-text-fill: black;" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 10 20 10 20;"
                );
                btn.setOnAction(event -> onAttivitaClick(event, attivita));
                attivitaContainer.getChildren().add(btn);
            }

        } catch (DAOException e) {
            showError("Errore caricamento attività", e.getMessage());
        }

    }

    @FXML
    public void handleAddActivity(ActionEvent event) {
        switchScene("/views/CreaAttivitaView.fxml",event);
    }

    @FXML
    public void onAttivitaClick(ActionEvent event, Attivita attivita){
        System.out.println("ATTIVITA button clicked.");
        SessionManager.getInstance().setAttributo("attivitaSelezionata", attivita);
        this.switchScene("/views/RiepilogoAttivitaView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia","Lista delle attività","Puoi visualizzare la lista delle attività disponibili.\n" +
                "Puoi scollare per scorrere la lista e scegliere l'attività a cui vuoi prenotarti");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", homeEvent);
    }
}
