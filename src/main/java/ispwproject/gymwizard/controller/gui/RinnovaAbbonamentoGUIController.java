package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

public class RinnovaAbbonamentoGUIController extends AbstractGUIController{
    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize(){
        anchorPane.setBackground(new Background(this.background()));
    }

    @FXML
    private void on10IngressiClick(ActionEvent event) {
        System.out.println("10 INGRESSI button clicked.");
        SessionManager.getInstance().setAttributo("tipoAbbonamento", "10ingressi");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onMensileClick(ActionEvent event) {
        System.out.println("MENSILE button clicked.");
        SessionManager.getInstance().setAttributo("tipoAbbonamento", "mensile");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onTrimestraleClick(ActionEvent event) {
        System.out.println("TRIMESTRALE button clicked.");
        SessionManager.getInstance().setAttributo("tipoAbbonamento", "trimestrale");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onAnnualeClick(ActionEvent event) {
        System.out.println("ANNUALE button clicked.");
        SessionManager.getInstance().setAttributo("tipoAbbonamento", "annuale");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        switchScene("/views/StatoAbbonamentoView.fxml", backEvent);
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Rinnova Abbonamento", "\"\"\"\n" +
                "                  Scegli una delle tipologie disponibili:\n" +
                "                  - 10 Ingressi\n" +
                "                  - Mensile\n" +
                "                  - Trimestrale\n" +
                "                  - Annuale\n" +
                "                \"\"\"");
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene((String) SessionManager.getInstance().getAttributo("homePage"), homeEvent);
    }
}
