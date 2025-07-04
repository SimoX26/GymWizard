package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SchedaControllerTest {

    @BeforeEach
    void setup() {
        // ✅ Reset della "sessione" prima di ogni test
        SessionManager.getInstance().clearAll();

        // ✅ Simulazione di utente loggato
        Utente utenteLoggato = new Utente();
        utenteLoggato.setId(1);
        SessionManager.getInstance().setAttributo("utente", utenteLoggato);
    }

    @Test
    void testAccessoSchedeProprie() {
        assertDoesNotThrow(() -> SchedaController.getSchedeByIdCliente(1));
    }

    @Test
    void testAccessoSchedeAltrui() {
        assertThrows(SecurityException.class, () -> SchedaController.getSchedeByIdCliente(99));
    }
}
