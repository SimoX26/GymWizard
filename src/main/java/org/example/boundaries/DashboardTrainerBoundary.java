package org.example.boundaries;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashboardTrainerBoundary {

    @FXML
    private Button logoutArrow, helpIcon, homeIcon;

    @FXML
    private void handleLogoutClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
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
    }

    @FXML
    private void handleHelpClick() {
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("Aiuto");
        help.setHeaderText("Guida Interfaccia Personal Trainer");
        help.setContentText("""
                • Usa il menu centrale per navigare:
                - Lista Clienti: consulta l’elenco dei tuoi clienti.
                - Gestisci Schede: crea o modifica i piani dei clienti.
                - Vai alla Chat: comunica con i tuoi clienti.
                """);
        help.showAndWait();
    }

    @FXML
    private void handleHomeClick() {
        System.out.println("Icona Home cliccata");
        // Qui puoi aggiungere la logica per tornare a una dashboard principale, se desiderato
    }
}
