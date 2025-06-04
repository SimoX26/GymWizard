package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.cli.LoginCLIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class HomeGUIController extends AbstractGUIController {

    @FXML
    private ToggleGroup version, memory;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    private void onLoginBtnClick(ActionEvent event) {
        RadioButton selectVersion = (RadioButton) version.getSelectedToggle();
        RadioButton selectMemory = (RadioButton) memory.getSelectedToggle();

        if (selectVersion != null && selectMemory != null) {
            String version = selectVersion.getText();
            String memory = selectMemory.getText();

            // Combinazioni possibili (4 casi)
            if (version.equals("GUI VERSION") && memory.equals("DBMS")) {
                System.out.println("GUI VERSION + DBMS");
                this.switchScene("/views/LoginView.fxml", event);
            } else if (version.equals("GUI VERSION") && memory.equals("FILE SYSTEM")) {
                System.out.println("GUI VERSION + FILE SYSTEM");
                this.showError("Warning", "Funzionalità ancora da implementare");
            } else if (version.equals("CLI VERSION") && memory.equals("DBMS")) {
                System.out.println("CLI VERSION + DBMS");
                this.closeWindow(event);
                LoginCLIController.start();
            } else if (version.equals("CLI VERSION") && memory.equals("FILE SYSTEM")) {
                System.out.println("CLI VERSION + FILE SYSTEM");
                this.showError("Warning", "Funzionalità ancora da implementare");
            } else {
                showError("Errore!", "Combinazione non supportata.");
            }
        } else {
            this.showError("Errore!", "Seleziona una scelta per entrambi i gruppi.");
        }
    }
}
