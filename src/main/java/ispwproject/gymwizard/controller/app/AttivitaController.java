package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.AttivitaDAO;
import ispwproject.gymwizard.util.DAO.PrenotazioneDAO;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AttivitaController {

    public AttivitaController() {
        // costruttore vuoto, nessuna istanziazione diretta della DAO
    }

    public static List<Attivita> getAttivitaDisponibili() throws DAOException {
        return AttivitaDAO.getInstance().getAllDisponibili();
    }

    public static void creaAttivita(String nome, String descrizione, LocalDate data, LocalTime oraInizio, LocalTime oraFine, int posti, String trainerName)
            throws DAOException, AttivitaDuplicataException {

        // Controllo duplicato
        if (AttivitaDAO.getInstance().existsAttivita(nome, data, oraInizio)) {
            throw new AttivitaDuplicataException(nome);
        }

        // Costruzione della nuova attività
        Attivita nuova = new Attivita(0, nome, descrizione, data, oraInizio, oraFine, posti, trainerName);

        // Inserimento dell'attività nel DB
        AttivitaDAO.getInstance().inserisciAttivita(nuova);
    }

    public static void prenotaAttivita(Attivita attivita) throws DAOException, AttivitaPienaException {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (utente != null) {
            if (attivita.getPostiDisponibili() <= 0) {
                throw new AttivitaPienaException(attivita.getNome());
            }

            int idUtente = utente.getId();
            System.out.println("ID utente loggato: " + idUtente);
            PrenotazioneDAO.getInstance().add(attivita.getId(), idUtente);
        } else {
            System.out.println("Nessun utente loggato.");
        }
    }

}

