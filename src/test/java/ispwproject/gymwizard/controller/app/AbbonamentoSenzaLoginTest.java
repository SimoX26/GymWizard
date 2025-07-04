package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AbbonamentoSenzaLoginTest {

    @Test
    public void testCreazioneAbbonamentoSenzaLogin() {
        // Simula assenza utente loggato
        SessionManager.getInstance().setAttributo("utente", null);

        // Verifica che lancia eccezione
        assertThrows(NullPointerException.class, () -> {
            AbbonamentoController.aggiungiAbbonamento("mensile", "TEST123");
        });
    }
}
