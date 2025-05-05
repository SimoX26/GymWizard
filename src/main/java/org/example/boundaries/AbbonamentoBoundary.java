package org.example.boundaries;

import org.example.controllers.AbbonamentoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Date;

public class AbbonamentoBoundary {
    @FXML
    private Label tipoAbbonamentoLabel;

    @FXML
    private Label statoAbbonamentoLabel;

    @FXML
    private Label scadenzaAbbonamentoLabel;

    @FXML
    private Button rinnovaButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button aiutoButton;

    @FXML
    private Button homeButton;

    private AbbonamentoController abbonamentoController;

    private String clienteId; // impostato dall’esterno (es. dopo login)

    public AbbonamentoBoundary() {
        abbonamentoController = new AbbonamentoController();
    }

    @FXML
    public void initialize() {
        // Qui puoi fare inizializzazioni al caricamento dell’FXML
        aggiornaDatiAbbonamento();
    }

    public void mostraDatiAbbonamento(String tipo, String stato, Date scadenza) {
        tipoAbbonamentoLabel.setText(tipo);
        statoAbbonamentoLabel.setText(stato);
        scadenzaAbbonamentoLabel.setText(scadenza.toString());
    }

    @FXML
    private void richiediRinnovoAbbonamento() {
        abbonamentoController.rinnovaAbbonamento(clienteId);
        aggiornaDatiAbbonamento();
    }

    @FXML
    private void logout() {
        System.out.println("Logout effettuato.");
        // Qui puoi aggiungere la logica per cambiare schermata
    }

    @FXML
    private void richiediAiuto() {
        System.out.println("Richiesta di aiuto inviata.");
        // Potresti aprire una finestra di dialogo
    }

    @FXML
    private void homeUtente() {
        System.out.println("Ritorno alla home utente.");
        // Cambio schermata verso la home
    }

    private void aggiornaDatiAbbonamento() {
        // Esempio: chiamata al controller per aggiornare i dati mostrati
        String stato = abbonamentoController.verificaStatoAbbonamento(clienteId);
        String tipo = "Mensile";  // Placeholder
        Date scadenza = new Date(); // Placeholder
        mostraDatiAbbonamento(tipo, stato, scadenza);
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}
