package ispwproject.gymwizard.controllers.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class XDashboardAdminController {

    @FXML
    private Label logoutArrow, helpIcon, homeIcon;

    @FXML
    private void initialize() {
        logoutArrow.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HomeView.fxml"));
                    Parent homeRoot = loader.load();
                    Scene scene = new Scene(homeRoot);

                    Stage stage = (Stage) logoutArrow.getScene().getWindow();
                    stage.setScene(scene);

                    stage.setWidth(1050);
                    stage.setHeight(700);
                    stage.setResizable(true);
                    stage.centerOnScreen();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        helpIcon.setOnMouseClicked(event -> {
            Alert help = new Alert(Alert.AlertType.INFORMATION);
            help.setTitle("Aiuto");
            help.setHeaderText("Guida Interfaccia Admin");
            help.setContentText("""
                    • Usa il menu a sinistra per navigare:
                    - Invia Comunicazione Globale: invia un messaggio a tutti gli utenti.
                    - Visualizza Report e Statistiche: consulta dati e analisi della palestra.
                    - Gestisci Listino Attività: aggiorna l’elenco delle attività disponibili.
                    """);
            help.showAndWait();
        });

        homeIcon.setOnMouseClicked(event -> {
            System.out.println("Icona Home cliccata");
        });
    }
}
