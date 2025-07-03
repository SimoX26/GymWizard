package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.cli.MainCLI;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class HomeGUIController extends AbstractGUIController {

    @FXML
    private ToggleGroup version;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    private void onLoginBtnClick(ActionEvent event) throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        RadioButton selectVersion = (RadioButton) version.getSelectedToggle();

        if (selectVersion != null) {
            String version = selectVersion.getText();

            // Scelta 3 casi
            if (version.equals("GUI VERSION")) {
                System.out.println("GUI VERSION");
                this.switchScene("/views/LoginView.fxml", event);
            } else if (version.equals("CLI VERSION")) {
                System.out.println("CLI VERSION");
                this.closeWindow(event);
                MainCLI.start();
            } else if (version.equals("DEMO VERSION")) {
                System.out.println("DEMO VERSION");
                this.showError("Warning", "Funzionalità ancora da implementare");
            } else {
                showError("Errore!", "Scelta non supportata.");
            }
        } else {
            this.showError("Errore!", "Seleziona una scelta!");
        }
    }
}
