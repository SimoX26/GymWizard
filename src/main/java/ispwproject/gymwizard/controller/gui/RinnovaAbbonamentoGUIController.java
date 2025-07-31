package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.logger.AppLogger;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.logging.Logger;

public class RinnovaAbbonamentoGUIController extends AbstractGUIController{

    private static final Logger logger = AppLogger.getLogger();
    private static final String TIPO_ABBONAMENTO = "tipoAbbonamento";
    private static final String SCENE = "/views/RiepilogoAbbonamentoView.fxml";

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));

    }

    @FXML
    private void on10IngressiClick(ActionEvent event) {
        logger.info("10 INGRESSI button clicked.");
        SessionManager.getInstance().setAttributo(TIPO_ABBONAMENTO, "10ingressi");
        this.switchScene(SCENE, event);
    }

    @FXML
    private void onMensileClick(ActionEvent event) {
        logger.info("MENSILE button clicked.");
        SessionManager.getInstance().setAttributo(TIPO_ABBONAMENTO, "mensile");
        this.switchScene(SCENE, event);
    }

    @FXML
    private void onTrimestraleClick(ActionEvent event) {
        logger.info("TRIMESTRALE button clicked.");
        SessionManager.getInstance().setAttributo(TIPO_ABBONAMENTO, "trimestrale");
        this.switchScene(SCENE, event);
    }

    @FXML
    private void onAnnualeClick(ActionEvent event) {
        logger.info("ANNUALE button clicked.");
        SessionManager.getInstance().setAttributo(TIPO_ABBONAMENTO, "annuale");
        this.switchScene(SCENE, event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        logger.info("BACK button clicked.");
        switchScene("/views/StatoAbbonamentoView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        logger.info("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Rinnova Abbonamento", """
            Scegli una delle tipologie disponibili:
            - 10 Ingressi
            - Mensile
            - Trimestrale
            - Annuale
            """);
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        logger.info("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
