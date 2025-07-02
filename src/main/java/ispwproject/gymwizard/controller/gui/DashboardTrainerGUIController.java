package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardTrainerGUIController extends AbstractGUIController{

    @FXML
    private Label welcomeLabel;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        System.out.println("** INIT EXEC - Dashboard Trainer**");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardTrainerView.fxml");

        Utente u = (Utente) SessionManager.getInstance().getAttributo("utente");
        welcomeLabel.setText("Benvenuto " + u.getUsername());
    }

    @FXML
    private void switchToListaClienti(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ListaClientiView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onChatListBtnClick(ActionEvent chatListEvent) {
        System.out.println("CHAT button clicked.");
        this.switchScene("/views/ListaChatView.fxml", chatListEvent);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Interfaccia", "Dashboard Trainer", "Puoi accedere alla lista dei clienti oppure alla chat con loro.");
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        System.out.println("LOGOUT button clicked.");
        if (this.logout()) {
            SessionManager.getInstance().clearAll();
            switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
}
