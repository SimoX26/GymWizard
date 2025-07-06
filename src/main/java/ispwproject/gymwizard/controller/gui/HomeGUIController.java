package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.controller.cli.MainCLI;
import ispwproject.gymwizard.util.Config;
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
    private ToggleGroup version; // GUI o CLI

    @FXML
    private RadioButton demoMode; // DEMO on/off

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    private void onLoginBtnClick(ActionEvent event) throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        RadioButton selectedVersion = (RadioButton) version.getSelectedToggle();

        if (selectedVersion == null) {
            this.showError("Errore!", "Seleziona una versione (GUI o CLI).");
            return;
        }

        boolean isDemo = demoMode.isSelected();
        String versionText = selectedVersion.getText();

        // Imposta modalità demo dinamicamente
        Config.setDemoMode(isDemo);


        // Esegui in base alla combinazione
        switch (versionText) {
            case "GUI VERSION" -> this.switchScene("/views/LoginView.fxml", event);
            case "CLI VERSION" -> {
                this.closeWindow(event);
                MainCLI.start();
            }
            default -> showError("Errore!", "Scelta non supportata.");
        }
    }
}
