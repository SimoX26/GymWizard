package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class RinnovaAbbonamentoGUIController extends AbstractGUIController{

    @FXML
    private void on10IngressiClick(ActionEvent event) {
        System.out.println("10 INGRESSI button clicked.");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onMensileClick(ActionEvent event) {
        System.out.println("MENSILE button clicked.");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onTrimestraleClick(ActionEvent event) {
        System.out.println("TRIMESTRALE button clicked.");
        this.switchScene("/views/RiepilogoOrdineView.fxml", event);
    }

    @FXML
    private void onAnnualeClick(ActionEvent event) {
        System.out.println("ANNUALE button clicked.");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Rinnova Abbonamento");
        alert.setContentText("\"\"\"\n" +
                "                  Scegli una delle tipologie disponibili:\n" +
                "                  - 10 Ingressi\n" +
                "                  - Mensile\n" +
                "                  - Trimestrale\n" +
                "                  - Annuale\n" +
                "                \"\"\"");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene("/views/DashboardClienteView.fxml", homeEvent);
    }
}
