package ispwproject.gymwizard.controllers.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ListaClientiBoundary {

    @FXML
    private VBox clientListVBox;
    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    public void initialize() {
        // Aggiunta clienti di esempio (sostituisci con dati reali)
        for (int i = 1; i <= 4; i++) {
            Label cliente = new Label("⚪ Nome Cliente " + i);
            cliente.setStyle("-fx-text-fill: white; -fx-font-family: 'Comic Sans MS'; -fx-font-size: 16; -fx-cursor: hand;");
            int finalI = i;
            cliente.setOnMouseClicked(event -> apriDettaglioCliente(finalI));
            clientListVBox.getChildren().add(cliente);
        }

        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardTrainerView.fxml"));

        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Lista Clienti");
            alert.setContentText("Clicca su un nome cliente per visualizzarne i dettagli.");
            alert.showAndWait();
        });

        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });
    }

    private void apriDettaglioCliente(int idCliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/VisualizzaClienteView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) clientListVBox.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensione fissa
            stage.setScene(scene);
            stage.setResizable(false);              // blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensione fissa
            stage.setScene(scene);
            stage.setResizable(false);              // blocca ridimensionamento
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
