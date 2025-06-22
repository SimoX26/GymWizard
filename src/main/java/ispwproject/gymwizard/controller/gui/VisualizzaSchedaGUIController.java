package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

public class VisualizzaSchedaGUIController extends AbstractGUIController{

    @FXML
    private HBox HBoxBtn;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));

        if("/views/DashboardTrainerView.fxml".equals(SessionManager.getInstance().getAttributo("homePage"))){
            Button add = new Button("AGGIUNGI ESERCIZIO");
            Button flush = new Button("SVUOTA SCHEDA");

            add.setStyle("-fx-cursor: hand; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");
            flush.setStyle("-fx-cursor: hand; -fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 3; -fx-background-radius: 10; -fx-border-radius: 10;");

            add.setOnAction(this::handleAddExercise);
            flush.setOnAction(this::handleFlushTrainingCard);

            HBoxBtn.getChildren().add(add);
            HBoxBtn.getChildren().add(flush);
        }
    }

    @FXML
    public void handleFlushTrainingCard(ActionEvent backEvent) {
        System.out.println("SVUOTA SCHEDA button clicked.");
    }

    @FXML
    public void handleAddExercise(ActionEvent backEvent) {
        System.out.println("AGGIUNGI ESERCIZIO button clicked.");
        this.switchScene("/views/AggiungiEsercizioView.fxml", backEvent);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", backEvent);
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
        this.switchScene("/views/DashboardClienteView.fxml", homeEvent);
    }
}
