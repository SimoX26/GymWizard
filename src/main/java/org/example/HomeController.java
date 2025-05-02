package org.example;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.image.Image;

public class HomeController {

    @FXML
    private StackPane stackPane;

    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/Sfondo_home.jpg").toExternalForm());

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
}

