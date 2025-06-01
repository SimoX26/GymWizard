package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.DAO.AttivitaDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

public class AttivitaGUIController extends AbstractGUIController{

    @FXML
    private VBox attivitaContainer; // deve essere collegato al VBox dentro lo ScrollPane in FXML

    @FXML
    public void initialize() throws DAOException {
        // Chiamata coerente con la tua classe AttivitaDAO
        List<Attivita> attivitaList = AttivitaDAO.getInstance().getAllDisponibili();

        for (Attivita attivita : attivitaList) {
            Button btn = new Button(attivita.getNome() + " - " + attivita.getData() + " " + attivita.getOraInizio());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(event -> onAttivitaClick(attivita));
            attivitaContainer.getChildren().add(btn);
        }
    }

    @FXML
    public void onAttivitaClick(Attivita attivita){
        System.out.println("ATTIVITA button clicked.");
        SessionManager.getInstance().setAttributo("attivitaSelezionata", attivita);
        this.switchScene("/views/RiepilogoAttivitaView.fxml", null);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle attività");
        alert.setContentText("Puoi visualizzare la lista delle attività disponibili.\n" +
                "Puoi scollare per scorrere la lista e scegliere l'attività a cui vuoi prenotarti");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", homeEvent);
    }
}
