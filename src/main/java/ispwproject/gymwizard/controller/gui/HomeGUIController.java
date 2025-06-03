package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomeGUIController extends AbstractGUIController {

    @FXML
    private ToggleGroup version, memory;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/Sfondo_home.png").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(
                100, 100, true, true, true, false
        );

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        anchorPane.setBackground(new Background(background));
    }

    @FXML
    private void onLoginBtnClick(ActionEvent loginEvent) {
        RadioButton selectVersion = (RadioButton) version.getSelectedToggle();
        RadioButton selectMemory = (RadioButton) memory.getSelectedToggle();

        if (selectVersion != null && selectMemory != null) {
            String version = selectVersion.getText();
            String memory = selectMemory.getText();

            // Combinazioni possibili (4 casi)
            if (version.equals("GUI VERSION") && memory.equals("DBMS")) {
                manageGuiDbms(loginEvent);
            } else if (version.equals("GUI VERSION") && memory.equals("FILE SYSTEM")) {
                manageGuiFileSystem(loginEvent);
            } else if (version.equals("CLI VERSION") && memory.equals("DBMS")) {
                manageCliDbms(loginEvent);
            } else if (version.equals("CLI VERSION") && memory.equals("FILE SYSTEM")) {
                manageCliFileSystem(loginEvent);
            } else {
                showError("Errore!", "Combinazione non supportata.");
            }
        } else {
            this.showError("Errore!", "Seleziona una scelta per entrambi i gruppi.");
        }
    }

    private void manageGuiDbms(ActionEvent loginEvent) {
        System.out.println("GUI VERSION + DBMS");
        this.switchScene("/views/LoginView.fxml", loginEvent);
    }

    private void manageGuiFileSystem(ActionEvent loginEvent) {
        System.out.println("GUI VERSION + FILE SYSTEM");
        this.showError("Warning", "Funzionalità ancora da implementare");
    }

    private void manageCliDbms(ActionEvent loginEvent) {
        System.out.println("CLI VERSION + DBMS");
        this.showError("Warning", "Funzionalità ancora da implementare");
    }

    private void manageCliFileSystem(ActionEvent loginEvent) {
        System.out.println("CLI VERSION + FILE SYSTEM");
        this.showError("Warning", "Funzionalità ancora da implementare");
    }
}
