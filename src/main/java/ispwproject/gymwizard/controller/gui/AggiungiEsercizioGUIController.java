package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class AggiungiEsercizioGUIController extends AbstractGUIController{

    @FXML
    private AnchorPane anchorPane;

    @FXML private TextField nomeEsercizioField;
    @FXML private TextField numeroSerieField;
    @FXML private TextField numeroRepField;
    @FXML private TextArea noteField;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    public void onAggiungiEsercizio(ActionEvent event) throws DAOException, SchedaController.EsercizioDuplicatoException {

        System.out.println("AGGIUNGI ESERCIZIO button clicked.");

        String nome = nomeEsercizioField.getText();
        int serie = Integer.parseInt(numeroSerieField.getText());
        int rep = Integer.parseInt(numeroRepField.getText());
        String note = noteField.getText();

        new SchedaController().aggiungiEsercizio( 1 ,nome, serie, rep, note);

    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        if(SessionManager.getInstance().getAttributo("homePage").equals("/views/DashboardTrainerView.fxml")){
            this.switchScene("/views/ListaClientiView.fxml", backEvent);

        }else{
            this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), backEvent);
        }
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
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }


}
