package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AbbonamentoSenzaLoginTest {

    @Test
    public void testCreazioneAbbonamentoSenzaLogin() {
        // Simula assenza utente loggato
        SessionManager.getInstance().setAttributo("utente", null);

        // Crea istanza del controller
        AbbonamentoController controller = new AbbonamentoController();

        // Verifica che lancia eccezione con metodo non statico
        assertThrows(NullPointerException.class, () -> {
            controller.aggiungiAbbonamento("mensile", "TEST123");
        });
    }
}
