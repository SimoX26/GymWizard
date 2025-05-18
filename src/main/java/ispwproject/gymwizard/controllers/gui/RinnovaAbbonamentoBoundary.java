package ispwproject.gymwizard.controllers.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RinnovaAbbonamentoBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    public void initialize() {

    }

    private void caricaRiepilogo(ActionEvent event, String tipoAbbonamento) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RiepilogoOrdineView.fxml"));
            Parent root = loader.load();

            // Ottieni il controller e imposta il tipo di abbonamento
            RiepilogoOrdineBoundary controller = loader.getController();
            controller.setTipoAbbonamento(tipoAbbonamento);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleScegli10Ingressi(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento 10 Ingressi");
    }

    @FXML
    private void handleScegliMensile(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Mensile");
    }

    @FXML
    private void handleScegliTrimestrale(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Trimestrale");
    }

    @FXML
    private void handleScegliAnnuale(ActionEvent event) {
        caricaRiepilogo(event, "Abbonamento Annuale");
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/StatoAbbonamentoView.fxml", event);
    }

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

    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    private void switchScene(String path, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
