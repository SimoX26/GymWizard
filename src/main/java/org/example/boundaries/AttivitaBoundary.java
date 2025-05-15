package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AttivitaBoundary {



    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle attività");
        alert.setContentText("Puoi visualizzare la lista delle attività disponibili.\n" +
                "Puoi scollare per scorrere la lista e scegliere l'attività a cui vuoi prenotarti");
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
