package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AbbonamentoAttivoTest {

    @Before
    public void setupSessioneUtente() {
        // Simula un utente già loggato con ID 1
        Utente utenteLoggato = new Utente();
        utenteLoggato.setId(1);
        SessionManager.getInstance().setAttributo("utente", utenteLoggato);
    }

    @Test
    public void testAggiuntaAbbonamentoQuandoGiaAttivo() {
        // Verifica che venga sollevata IllegalStateException
        assertThrows(IllegalStateException.class, () ->
                AbbonamentoController.aggiungiAbbonamento("mensile", "RIF123456")
        );
    }
}
