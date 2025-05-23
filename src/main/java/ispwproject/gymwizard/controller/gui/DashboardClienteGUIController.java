package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.Optional;

public class DashboardClienteGUIController extends AbstractGUIController{

    @FXML
    private Label welcomeLabel;

    @FXML
    private void initialize() {
        System.out.println("** INIT EXEC **");
        SessionManager.getInstance().setAttributo("homePage", "/views/DashboardClienteView.fxml");

        welcomeLabel.setText("Benvenuto " + "NOME UTENTE");
    }

    @FXML
    public void onSchedaBtnClick(ActionEvent trainingCardEvent) {
        System.out.println("TRAINING CARD button clicked.");
        this.switchScene("/views/SchedaView.fxml", trainingCardEvent);
    }

    @FXML
    public void onAttivitaBtnClick(ActionEvent coursesEvent) {
        System.out.println("COURSES button clicked.");
        this.switchScene("/views/AttivitaView.fxml", coursesEvent);
    }

    @FXML
    public void onStatoAbbonamentoBtnClick(ActionEvent event) {
        System.out.println("STATO ABBONAMENTO button clicked.");
        this.switchScene("/views/StatoAbbonamentoView.fxml", event);
    }

    @FXML
    public void onChatListBtnClick(ActionEvent event) {
        System.out.println("CHAT button clicked.");
        this.switchScene("/views/ListaChatView.fxml", event);
    }

    @FXML
    public void onCodiceAccessoBtnClick(ActionEvent event) {
        System.out.println("ACCESS CODE button clicked.");
        this.switchScene("/views/CodiceAccessoView.fxml", event);
    }

    @FXML
    public void onHelpClick(ActionEvent helpEvent) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Dashboard Coach");
        alert.setContentText("Puoi accedere alla lista dei clienti oppure alla chat con loro.");
        alert.showAndWait();
    }

    @FXML
    public void onLogoutClick(ActionEvent logoutEvent) {
        System.out.println("LOGOUT button clicked.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Logout");
        alert.setHeaderText("Vuoi effettuare il logout?");
        alert.setContentText("Verrai riportato alla schermata iniziale.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            SessionManager.getInstance().clearAll();
            switchScene("/views/HomeView.fxml", logoutEvent);
        }
    }
/*
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

 */
}
