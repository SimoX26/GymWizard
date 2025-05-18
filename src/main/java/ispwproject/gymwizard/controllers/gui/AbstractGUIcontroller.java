package ispwproject.gymwizard.controllers.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AbstractGUIcontroller {

    protected void switchScene(String path, ActionEvent event) {
        if(path == null || event == null){
            return;
        }
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
/*
    protected void displayFeaturePopup(Event event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        PopupFactory popupFactory = new PopupFactory();
        popupFactory.createBasePopup("Feature non implementata!").show(stage);
    }

 */
}
