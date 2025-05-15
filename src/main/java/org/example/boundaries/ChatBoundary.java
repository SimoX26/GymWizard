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
import java.util.Optional;

public class ChatBoundary {

    @FXML private TextField inputField;
    @FXML private Button sendBtn;
    @FXML private VBox messagesBox;

    private String provenienza;

    public void setProvenienza(String provenienza) {
        this.provenienza = provenienza;
        System.out.println("Schermata chiamata da: " + provenienza);

        // Cambia UI o logica in base alla provenienza
    }


    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        if(provenienza == "DashboardClienteBoundary"){
            switchScene("/views/DashboardClienteView.fxml", event);
        }else if(provenienza == "DashboardTrainerBoundary"){
            switchScene("/views/DashboardTrainerView.fxml", event);
        }
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Chat");
        alert.setContentText("Scrivi un messaggio nel campo in basso e premi INVIA.");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        if(provenienza == "DashboardClienteBoundary"){
            switchScene("/views/DashboardClienteView.fxml", event);
        }else if(provenienza == "DashboardTrainerBoundary"){
            switchScene("/views/DashboardTrainerView.fxml", event);
        }
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
