package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EserciziDuplicatiControllerTest {

    @BeforeEach
    void setup() {
        // 🔁 Reset della "sessione" prima di ogni test
        SessionManager.getInstance().clearAll();

        // ✅ Simula la selezione di una scheda con ID reale dal DB
        Scheda scheda = new Scheda(1, "Scheda Test");  // idCliente = 1, nomeScheda = "Scheda Test"
        scheda.setId(1); // <-- L'ID della scheda deve corrispondere a quella esistente nel DB
        SessionManager.getInstance().setAttributo("scheda", scheda);
    }

    @Test
    void testEsercizioDuplicato() {
        SchedaController controller = new SchedaController();

        // ✅ Verifica che venga lanciata l’eccezione se l'esercizio esiste già
        assertThrows(SchedaController.EsercizioDuplicatoException.class, () -> {
            controller.aggiungiEsercizio("Panca Piana", 4, 10, "nota qualsiasi");
        });
    }
}
