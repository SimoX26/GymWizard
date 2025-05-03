package org.example.controller.gui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HomeController {

    @FXML
    private StackPane stackPane;

    @FXML
    private Rectangle fadeOverlay;

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

        stackPane.setBackground(new Background(background));
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        fadeOverlay.setOpacity(0);

        FadeTransition fadeToBlack = new FadeTransition(Duration.millis(500), fadeOverlay);
        fadeToBlack.setFromValue(0.0);
        fadeToBlack.setToValue(1.0);

        fadeToBlack.setOnFinished(e -> {
            try {
                // ✅ Path corretto in base alla tua struttura: src/main/resources/view/Login.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
                Parent loginRoot = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double width = stage.getWidth();
                double height = stage.getHeight();

                Scene loginScene = new Scene(loginRoot, width, height);

                loginRoot.setOpacity(0);
                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), loginRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                stage.setScene(loginScene);
                stage.show();
                fadeIn.play();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        fadeToBlack.play();
    }
}
