package ispwproject.gymwizard.controller.gui;

import com.google.zxing.WriterException;
import ispwproject.gymwizard.util.singleton.QRCodeGenerator;
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
import java.util.logging.Logger;

public class CodiceAccessoGUIController extends AbstractGUIController implements Initializable {

    private static final Logger logger = Logger.getLogger(CodiceAccessoGUIController.class.getName());

    @FXML
    private ImageView qrCodeImage;

    @FXML
    private Label codiceAlfanumericoLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String codice = QRCodeGenerator.getInstance().generaCodiceAccesso();
            Image qrImage = QRCodeUtils.generaQRCodeImage(codice, 200, 200);
            qrCodeImage.setImage(qrImage);
            codiceAlfanumericoLabel.setText(codice);
        } catch (WriterException | IOException e) {
            logger.severe("Errore durante la generazione del QR code: " + e.getMessage());
        }
    }

    public void onBackClick(ActionEvent event) {
        logger.info("BACK button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }

    public void onHelpClick(ActionEvent event) {
        logger.info("HELP button clicked.");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("QRCode");
        alert.setContentText("Scansiona il QRCode o visualizza il tuo codice d'ingresso");
        alert.showAndWait();
    }

    public void onHomeClick(ActionEvent event) {
        logger.info("HOME button clicked.");
        switchScene("/views/DashboardClienteView.fxml", event);
    }
}
