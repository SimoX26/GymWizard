package ispwproject.gymwizard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/HomeView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root); // Dimensione della finestra

            primaryStage.setTitle("GymWizard");   // Titolo della finestra
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);     // Blocca ridimensionamento
            primaryStage.centerOnScreen();        // Centra la finestra
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // e.getMessage();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}