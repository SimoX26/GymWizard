package ispwproject.gymwizard.controller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class DashboardTrainerBoundary {

    @FXML
    public void onChatListBtnClick(ActionEvent event) {
        System.out.println("CHAT button clicked.");
        switchScene("/views/ListaChatView.fxml", event, "DashboardTrainerBoundary");
    }

    @FXML
    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Coach");
        alert.setContentText("Puoi accedere alla lista dei clienti oppure alla chat con loro.");
        alert.showAndWait();
    }

    @FXML
    public void onLogoutClick(ActionEvent event) {
        System.out.println("LOGOUT button clicked.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            switchScene("/views/HomeView.fxml", event, "DashboardClienteBoundary");
        }
    }

    @FXML
    private void switchScene(String path, ActionEvent event, String context) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            Object controller = loader.getController();
            // Controllo generico: se il controller ha un metodo chiamato "setContext"
            try {
                try {
                    controller.getClass()
                            .getMethod("setContext", String.class)
                            .invoke(controller, context);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException ignored) {
                System.out.println("Controller " + controller.getClass().getSimpleName() + " non ha setContext()");
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
