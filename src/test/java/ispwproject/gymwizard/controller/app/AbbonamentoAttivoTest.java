package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AbbonamentoAttivoTest {

    private final AbbonamentoController abbonamentoController = new AbbonamentoController();

    @BeforeEach
    void setupSessioneUtente() {
        // Simula un utente già loggato con ID 1
        Utente utenteLoggato = new Utente();
        utenteLoggato.setId(1);
        SessionManager.getInstance().setAttributo("utente", utenteLoggato);
    }

    @Test
    void testAggiuntaAbbonamentoQuandoGiaAttivo() {
        // Verifica che venga sollevata IllegalStateException
        assertThrows(IllegalStateException.class, () ->
                abbonamentoController.aggiungiAbbonamento("mensile", "RIF123456")
        );
    }
}
