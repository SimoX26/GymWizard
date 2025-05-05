package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
        Scene scene = new Scene(loader.load());

        // Configurazione finestra
        primaryStage.setTitle("GymWizard");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        // primaryStage.setResizable(false); // Impedisci ridimensionamento
        primaryStage.centerOnScreen(); // Centra la finestra sullo schermo
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
