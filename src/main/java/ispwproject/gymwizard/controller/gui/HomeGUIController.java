package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class HomeGUIController extends AbstractGUIController {

    @FXML
    private RadioButton select;

    @FXML
    private RadioButton radioInsegnante;

    @FXML
    private RadioButton radioContanti;

    @FXML
    private RadioButton radioCarta;

    @FXML
    private ToggleGroup version;

    @FXML
    private ToggleGroup memory;

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

            System.out.println("Versione: " + version + " - Memorizzazione: " + memory);

            // Puoi anche passare questi valori al Model o Controller
            // esempioController.salvaScelta(tipoUtente, tipoPagamento);
        } else {
            this.showError("Errore!", "Seleziona una scelta per entrambi i gruppi.");
        }

        this.switchScene("/views/LoginView.fxml", loginEvent);
    }
}
