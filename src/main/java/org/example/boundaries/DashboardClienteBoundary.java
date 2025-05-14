package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Optional;

public class DashboardClienteBoundary {

    // @FXML
    // private Button schedaBtn, attivitaBtn, statoAbbonamentoBtn, chatBtn, codiceAccessoBtn, helpBtn, logoutBtn;
    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
        System.out.println("Inizializzazione eseguita.");

        welcomeLabel.setText("Benvenuto " + "NOME UTENTE");
    }

    public void onSchedaBtnClick(ActionEvent event) {
        System.out.println("SCHEDA button clicked.");
        switchScene("/views/SchedaView.fxml", event);
    }

    public void onAttivitaBtnClick(ActionEvent event) {
        System.out.println("ATTIVITA button clicked.");
        switchScene("/views/AttivitaView.fxml", event);
    }

    public void onStatoAbbonamentoBtnClick(ActionEvent event) {
        System.out.println("STATO ABBONAMENTO button clicked.");
        switchScene("/views/StatoAbbonamentoView.fxml", event);
    }

    public void onChatBtnClick(ActionEvent event) {
        System.out.println("CHAT button clicked.");
        switchScene("/views/ChatView.fxml", event);
    }

    public void onCodiceAccessoBtnClick(ActionEvent event) {
        System.out.println("CODICE ACCESSO button clicked.");
        switchScene("/views/CodiceAccessoView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Coach");
        alert.setContentText("Puoi accedere alla lista dei clienti oppure alla chat con loro.");
        alert.showAndWait();
    }

    public void onLogoutClick(ActionEvent event) {
        System.out.println("LOGOUT button clicked.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            switchScene("/views/HomeView.fxml", event);
        }
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
