package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class XHomeController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/Sfondo_home.png").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(
                100, 100, true, true, true, false
        );

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );

        anchorPane.setBackground(new Background(background));
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            Parent loginRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setWidth(1050);
            stage.setHeight(700);
            stage.setResizable(true);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
