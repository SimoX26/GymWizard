package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.bean.AttivitaBean;
import ispwproject.gymwizard.util.dao.DAOFactory;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AttivitaController {

    public void getAttivitaDisponibili(AttivitaBean bean) throws DAOException {
        List<Attivita> attivitaList = DAOFactory.getAttivitaDAO().getAllDisponibili(); // Recupero della lista delle attività dal DB
        bean.setAttivita(attivitaList); // Impostazione della bean
    }

    public void creaAttivita(String nome, String descrizione, LocalDate data, LocalTime oraInizio, LocalTime oraFine, int posti, String trainerName)
            throws DAOException, AttivitaDuplicataException {

        if (DAOFactory.getAttivitaDAO().existsAttivita(nome, data, oraInizio)) {
            throw new AttivitaDuplicataException(nome);
        }

        Attivita nuova = new Attivita(0, nome, descrizione, data, oraInizio, oraFine, posti, trainerName);

        DAOFactory.getAttivitaDAO().inserisciAttivita(nuova);
    }

    public void prenotaAttivita(Attivita attivita) throws DAOException, AttivitaPienaException {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (utente != null) {
            if (attivita.getPostiDisponibili() <= 0) {
                throw new AttivitaPienaException(attivita.getNome());
            }

            int idUtente = utente.getId();
            DAOFactory.getPrenotazioneDAO().add(attivita.getId(), idUtente);
        }
    }
}
