package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertThrows;

public class AttivitaPienaTest {

    private AttivitaController attivitaController;

    @Before
    public void setup() {
        // Imposta un utente fake loggato nella sessione
        Utente utente = new Utente();
        utente.setId(1);  // Assicurati che l’utente 1 esista nel DB
        SessionManager.getInstance().setAttributo("utente", utente);

        // Inizializza il controller non statico
        attivitaController = new AttivitaController();
    }

    @Test
    public void testPrenotazioneSuAttivitaPiena() {
        // Crea un'attività con posti = 0
        Attivita attivitaPiena = new Attivita(
                9999,  // id fittizio
                "Yoga",
                "Lezione piena",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                LocalTime.of(19, 0),
                0,  // postiDisponibili = 0
                "Trainer X"
        );

        // Verifica che venga lanciata l’eccezione
        assertThrows(AttivitaPienaException.class, () -> {
            attivitaController.prenotaAttivita(attivitaPiena);
        });
    }
}
