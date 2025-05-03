package org.example.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            // 🔧 Carica Home.fxml dalla cartella /view
            Parent root = loadFXML("Home");
            scene = new Scene(root, 640, 480);
            stage.setTitle("GymWizard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("❌ Errore nel caricamento della schermata iniziale: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
