package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SchedaBoundary {

    // @FXML
    // private Button backBtn, helpBtn, homeBtn;
    @FXML
    private ImageView schedaImage;

    @FXML
    private void initialize() {
        schedaImage.setOnScroll(e -> {
            double zoomFactor = e.getDeltaY() > 0 ? 1.1 : 0.9;
            double newScaleX = schedaImage.getScaleX() * zoomFactor;
            double newScaleY = schedaImage.getScaleY() * zoomFactor;

            // Limiti
            if (newScaleX >= 0.5 && newScaleX <= 3.0) {
                schedaImage.setScaleX(newScaleX);
                schedaImage.setScaleY(newScaleY);
            }
        });


        schedaImage.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                schedaImage.setScaleX(1.0);
                schedaImage.setScaleY(1.0);
            }
        });
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Scheda allenamento");
        alert.setContentText("Puoi visualizzare la tua scheda di allenamento." +
                "Puoi scollare per effettuare uno zoom");
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
