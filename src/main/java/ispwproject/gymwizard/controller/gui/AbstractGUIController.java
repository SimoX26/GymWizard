package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.exception.SceneLoadingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractGUIController {

    protected void switchScene(String fxmlPath, ActionEvent event) {
        try {
            URL location = getClass().getResource(fxmlPath);
            if (location == null) {
                throw new SceneLoadingException("FXML non trovato: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new SceneLoadingException("Errore durante il cambio scena: " + fxmlPath, e);
        }
    }

    protected void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    protected void showError(String titolo, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    protected void showPopup(String titolo, String intestazione, String messaggio) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(intestazione);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    protected boolean logout() {
        SessionManager.getInstance().clearAll(); // Pulire tutti i dati della sessione precedente
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    protected BackgroundImage background() {
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResource("/images/Sfondo_home.png")).toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        return new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    }
}
