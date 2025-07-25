package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.dao.AttivitaDAO;
import ispwproject.gymwizard.util.dao.DAOFactory;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.DAOException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;


public class AttivitaDuplicataTest {

    private final AttivitaController controller = new AttivitaController();
    private final String nome = "Pilates Test";
    private final LocalDate data = LocalDate.now().plusDays(1);
    private final LocalTime oraInizio = LocalTime.of(9, 0);
    private final LocalTime oraFine = LocalTime.of(10, 0);

    @Before
    public void inserisciAttivitaDiTest() throws DAOException {
        // Se non esiste già, inseriamo un'attività per testare il duplicato
        if (!DAOFactory.getAttivitaDAO().existsAttivita(nome, data, oraInizio)) {
            Attivita attivita = new Attivita(
                    0,
                    nome,
                    "Sessione di prova",
                    data,
                    oraInizio,
                    oraFine,
                    10,
                    "Trainer Test"
            );
            DAOFactory.getAttivitaDAO().inserisciAttivita(attivita);
        }
    }

    @Test(expected = AttivitaDuplicataException.class)
    public void testCreazioneAttivitaDuplicata() throws DAOException, AttivitaDuplicataException {
        // Tenta di creare la stessa attività => dovrebbe lanciare AttivitaDuplicataException
        controller.creaAttivita(
                nome,
                "Sessione di prova duplicata",
                data,
                oraInizio,
                oraFine,
                10,
                "Trainer Test"
        );
    }
}
