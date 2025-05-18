package ispwproject.gymwizard.controllers.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

public class SchedaBoundary extends AbstractGUIController{

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

    @FXML
    public void onBackClick(ActionEvent backEvent) {
        System.out.println("BACK button clicked.");
        this.switchScene(backEvent, "/views/DashboardClienteView.fxml");
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Scheda allenamento");
        alert.setContentText("Puoi visualizzare la tua scheda di allenamento.\n" +
                "Puoi scollare per effettuare uno zoom");
        alert.showAndWait();
    }

    @FXML
    public void onHomeClick(ActionEvent homeEvent) {
        System.out.println("HOME button clicked.");
        this.switchScene(homeEvent, "/views/DashboardClienteView.fxml");
    }
}
