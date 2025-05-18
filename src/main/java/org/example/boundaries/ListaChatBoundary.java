package org.example.boundaries;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ListaChatBoundary {

    @FXML private VBox chatListVBox;
    private String context;

    @FXML
    public void initialize() {
        /*
        // Esempio mock utenti
        for (int i = 1; i <= 5; i++) {
            Label utente = new Label("🗨️ Chat con Utente " + i);
            utente.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-family: 'Comic Sans MS'; -fx-cursor: hand; -fx-padding: 8 10; -fx-background-color: #444; -fx-background-radius: 8;");
            int finalI = i;
            utente.setOnMouseClicked(event -> onChatClick(finalI, event));
            chatListVBox.getChildren().add(utente);
        }
        */
    }

    // Metodo che imposta il contesto di esecuzione in base alla schermata da cui è stata chiamata questa
    public void setContext(String context) {
        this.context = context;
        System.out.println("Schermata chiamata da: " + context);
    }

    public void onChatBtnClick(ActionEvent event) {
        System.out.println("CHAT button clicked.");
        switchScene("/views/ChatView.fxml", event, context);
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        if (context.equals("DashboardClienteBoundary")) {
            switchScene("/views/DashboardClienteView.fxml", event, context);
        } else if (context.equals("DashboardTrainerBoundary")) {
            switchScene("/views/DashboardTrainerView.fxml", event, context);
        }
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Lista delle chat");
        alert.setContentText("Puoi visualizzare la lista delle tue chat.\n" +
                "Puoi scollare per scorrere la lista e scegliere la chat che vuoi aprire.");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event, context);
    }

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
