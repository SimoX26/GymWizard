package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractGUIController {

    protected void switchScene(String fxmlPath, ActionEvent event) {
        try {
            URL location = getClass().getResource(fxmlPath);
            if (location == null) {
                throw new RuntimeException("FXML non trovato: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(location);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException("Errore durante il cambio scena: " + fxmlPath, e);
        }
    }

}
