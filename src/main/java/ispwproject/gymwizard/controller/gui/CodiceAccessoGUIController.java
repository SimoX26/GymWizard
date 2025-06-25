package ispwproject.gymwizard.controller.gui;

import com.google.zxing.WriterException;
import ispwproject.gymwizard.util.singleton2.QRCodeGenerator;
import ispwproject.gymwizard.util.QRCodeUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CodiceAccessoGUIController extends AbstractGUIController implements Initializable {

    @FXML
    private ImageView qrCodeImage;

    @FXML
    private Label codiceAlfanumericoLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String username = "cliente"; // sostituiscilo con l'utente loggato, se hai sessione
            String codice = QRCodeGenerator.getInstance().generaCodiceAccesso(username);
            Image qrImage = QRCodeUtils.generaQRCodeImage(codice, 200, 200);
            qrCodeImage.setImage(qrImage);
            codiceAlfanumericoLabel.setText(codice);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        System.out.println("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("QRCode");
        alert.setContentText("Scansiona il QRCode o visualizza il tuo codice d'ingresso");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }
}

