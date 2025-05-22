package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CalendarioAttivitaGUIController {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    private Button addBtn;

    @FXML
    private GridPane attivitaGrid;

    @FXML
    private void initialize() {
        aggiungiAttivita("Yoga", 1, 3);
        aggiungiAttivita("Crossfit", 1, 6);

        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardAdminView.fxml"));
        helpIcon.setOnMouseClicked(event -> handleHelpClick());
        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });
    }

    private void aggiungiAttivita(String nome, int colonna, int riga) {
        Button btn = new Button(nome);
        btn.setPrefSize(100, 40);
        btn.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-background-color: green; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2; -fx-background-radius: 10; -fx-border-radius: 10;");
        btn.setOnAction(e -> apriRiepilogoAttivita(nome));
        attivitaGrid.add(btn, colonna, riga);
    }

    private void apriRiepilogoAttivita(String nomeAttivita) {
        switchScene("/views/RiepilogoAttivitaView.fxml");
    }

    @FXML
    private void handleAddActivity(ActionEvent event) {
        switchScene("/views/CreaAttivitaView.fxml");
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensioni fisse
            stage.setScene(scene);
            stage.setResizable(false);              // blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHelpClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Planner Settimanale");
        alert.setContentText("""
                Questa schermata ti permette di visualizzare e gestire le attività della settimana.

                - Clicca su un'attività per visualizzarne il riepilogo.
                - Usa il pulsante "+" per aggiungere una nuova attività.
                - Usa la freccia ↩ per tornare alla dashboard dell’amministratore.
                - Usa la casetta ⌂ per effettuare il logout.
                """);
        alert.showAndWait();
    }
}
