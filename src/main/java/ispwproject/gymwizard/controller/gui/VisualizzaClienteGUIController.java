package ispwproject.gymwizard.controller.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

public class VisualizzaClienteGUIController {

    @FXML private Label clienteLabel, backIcon, helpIcon, homeIcon;
    @FXML private Button schedaBtn, schedaBtn1;

    // Percorso dove salvare temporaneamente il PDF (puoi personalizzarlo)
    private final Path schedaSalvata = Paths.get("schede_cliente/scheda_cliente.pdf");

    @FXML
    public void initialize() {

        // BACK
        backIcon.setOnMouseClicked(event -> switchScene("/views/ListaClientiView.fxml"));

        // HELP
        helpIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guida Interfaccia");
            alert.setHeaderText("Dettaglio Cliente");
            alert.setContentText("Puoi visualizzare i dettagli del cliente e allegare una scheda in formato PDF.");
            alert.showAndWait();
        });

        // LOGOUT
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

        // Aggiungi Scheda
        schedaBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Carica la scheda del cliente");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));

            File file = fileChooser.showOpenDialog(schedaBtn.getScene().getWindow());

            if (file != null) {
                try {
                    Files.createDirectories(schedaSalvata.getParent());
                    Files.copy(file.toPath(), schedaSalvata, StandardCopyOption.REPLACE_EXISTING);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Caricamento completato");
                    alert.setHeaderText(null);
                    alert.setContentText("Scheda salvata: " + file.getName());
                    alert.showAndWait();
                } catch (IOException e) {
                    showError("Errore nel salvataggio della scheda.");
                    e.printStackTrace();
                }
            }
        });

        // Modifica Scheda
        schedaBtn1.setOnAction(event -> {
            if (Files.exists(schedaSalvata)) {
                try {
                    // Apre il file PDF con il visualizzatore di default
                    java.awt.Desktop.getDesktop().open(schedaSalvata.toFile());
                } catch (IOException e) {
                    showError("Impossibile aprire la scheda.");
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Scheda non disponibile");
                alert.setHeaderText(null);
                alert.setContentText("Nessuna scheda trovata per questo cliente.");
                alert.showAndWait();
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            stage.setScene(new Scene(root, 900, 600));
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
